package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader 

{

    private static Properties envProperties = new Properties();
    private static Properties configProperties = new Properties();
    private static Properties dataProperties = new Properties();

    static {
        try {
           
            envProperties.load(new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//ews//properties//env.properties"));                        
            configProperties.load(new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//ews//properties//"+ envProperties.getProperty("env")+ "-config.properties"));
            dataProperties.load(new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//ews//properties//"+ envProperties.getProperty("env") +"-data.properties"));    
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public static String getConfigProperty(String key) {
        return configProperties.getProperty(key);
    }

    public static String getDataProperty(String key) {
        return dataProperties.getProperty(key);
    }
}

