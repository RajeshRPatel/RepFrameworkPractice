package com.testcases;

import org.testng.annotations.Test;

import com.base.JavaBase;

import org.testng.annotations.Test;
import com.ews.base.JavaBase;
import com.ews.pages.LoginPage;
import com.ews.utils.PropertyReader;

public class LoginTestCase extends JavaBase
{

	@Test(priority = 1)
public void openUrl()
{
		driver.get(PropertyReader.getConfigProperty("server_url"));
		
}
	
	@Test(priority = 2)
public void login()
{
		LoginPage.enterLoginDetails();
		
}	
}

