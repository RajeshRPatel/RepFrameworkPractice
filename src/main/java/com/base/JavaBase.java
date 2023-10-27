package com.base;

public class JavaBase 

{
	 
	static public  WebDriver driver;
	private static int timeout=50;
	private static boolean acceptNextAlert;
 	private String BROWSER;
	private Object url;
	public SoftAssert softAssert;
	
	@BeforeSuite(alwaysRun=true)
	public void setUp() 
	{

		BROWSER= PropertyReader.getConfigProperty("BROWSER");
		
		switch (BROWSER) 
		{
		    case "firefox":
		    {
 			   driver = new FirefoxDriver();
		    }
		    
			break;
        
		    case "edge":
		    { 
	 		     driver= new EdgeDriver();
		    }
		    
			break;
			
          case "chrome":
          {
        
		  
		    	driver= new ChromeDriver();
          }
		    	break;
					
		default:
		
			
		    	driver= new ChromeDriver();
			break;
		} 
	  
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));	    
	        driver.manage().window().maximize();  
	        driver.manage().deleteAllCookies();
	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));   	
	}
	
	
	 @BeforeMethod(alwaysRun = true)
	public void initSoftAssert() {
		softAssert = new SoftAssert();
	}
	 
	 
	 	 
	 public static  WebElement FindWebElementByTag(String exData) {
	        WebElement webElement =driver.findElement(By.tagName(exData));
	        return webElement;
	    }
	
	 
	// Return web driver object
		public static WebDriver getWebDriver() 
		{
			if(driver!=null)
			return driver;
			else
			{
	
		
		    	driver= new ChromeDriver();
      
			}
			return driver;
				
		}

		
 
	
		
	public static String getSystemTime()
	{
	     DateFormat dateFormat = new SimpleDateFormat("yyyyddMMHHmmssSSS");
		 Date date = new Date();
	 	 String currentdate=dateFormat.format(date);
	 	 String news=currentdate.substring(currentdate.lastIndexOf(" ")+1);
	     return news.substring(10,15);
	}
	 
	
	 
	// Handle send keys action
	 
	// Handle send keys action
		public static void sendKeys(String locator, String text) 
		{
			try
			{
			WaitForElementPresent(locator, timeout);
			WebElement el = driver.findElement(ByLocator(locator));
			el.sendKeys(text);
			}
			catch (Exception e) 
			{
			e.printStackTrace();
			}
		}
			public static WebElement WaitForElementPresent(String locator, int timeout) {

				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(ByLocator(locator)));
				return element;
			}
			
			// Handle click action
	 		public static void clickOn(String locator) {
	 			try
	 			{
	 			WaitForElementPresent(locator,timeout);
	 //			WebDriverWait wait = new WebDriverWait(driver, timeout);
	 			WebElement el = driver.findElement(ByLocator(locator));
	 			waitForElementToBeClickable(driver,el);
	 			el.click();
	 			}
	 			catch (Exception e) {
					e.printStackTrace();
				}
	 		}
	 		
	 	// Handle click action
	 		public static void clickOnByIndex(String locator,int index) {
	 			try
	 			{
	 			WaitForElementPresent(locator,timeout);
	 //			WebDriverWait wait = new WebDriverWait(driver, timeout);
	 			WebElement el=driver.findElements(ByLocator(locator)).get(index);
	 			
	 			//WebElement el = driver.findElements(ByLocator(locator));
	 			waitForElementToBeClickable(driver,el);
	 			el.click();
	 			}
	 			catch (Exception e) {
					e.printStackTrace();
				}
	 		}
	 		
	 		  public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
	 		        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	 		        wait.until(ExpectedConditions.elementToBeClickable(element));
	 		    }
	 		   
	 		    public static void waitForElementToBeClickableListType(WebDriver driver, List<WebElement> element) {
	 		        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	 		        
	 		       // wait.until(ExpectedConditions.elementToBeClickabl(element));
	 		    }
	 		  
			// Handle locator type
	 		public static By ByLocator(String locator)
            {
                By result = null;
                if (locator.startsWith(".//")) {
                    result = By.xpath(locator);
                }
                else if (locator.startsWith("//")) {
                    result = By.xpath(locator);
                } else if (locator.startsWith("css=")) {
                    result = By.cssSelector(locator.replace("css=", ""));
                } else if (locator.startsWith("name=")) {
                    result = By.name(locator.replace("name=", ""));
                } else if (locator.startsWith("link=")) {
                    result = By.linkText(locator.replace("link=", ""));
                } else if (locator.startsWith("id="))  {
                    result = By.id(locator.replace("id=", ""));
                }
                else {
                    result = By.id(locator);
                }
                return result;
            }


	
		 
		// Handle send keys action
			public static void sendKeysByIndex(List<WebElement> elements, String text, int index) 
			{
				try
				{

				WebElement el=elements.get(index);
				el.sendKeys(text);
				}
				catch (Exception e) {
				e.printStackTrace();
				}
				}
				
			
			
		
		public static void sendKeysWhenCanNotFocus(WebElement element, String text) 
		{
			try
			{
			WebElement element2=WaitForElementPresent(element, timeout);
			Actions actions = new Actions(driver);
			actions.moveToElement(element2);
			actions.click();
			actions.sendKeys(text);
			 actions.perform();
		 		}
			catch (Exception e) {
			e.printStackTrace();
			}
			}
		
		
		
	  
		public static void sendKeysWhenElementNotVisible(WebElement elementName, String text)
		{
		 WebElement el=WaitForVisibilityOfElementLocated(elementName,timeout);
		 el.sendKeys(text);	
		}
	
		
		
		public static WebElement WaitForVisibilityOfElementLocated(WebElement element, int timeout) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated((By)element));
			return element1;
		}
		
		 
		
		
		public static void ClickByXpath(String id) 
		{
	        driver.findElement(By.xpath(id)).click();
	    }
		
		
		public static boolean retryingFindClick(WebElement element) {
		    boolean result = false;
		    int attempts = 0;
		    while(attempts < 2) {
		        try {
		        	element.click();
		            result = true;
		            break;
		        } 
		        catch(StaleElementReferenceException e) 
		        {
		        }
		        attempts++;
		    }
		    return result;
		}
	
	 
	
	// Handle Tab

		public static void tabhandle() {

			Set<String> window = driver.getWindowHandles();
			Iterator<String> it = window.iterator();
			String mainpage = it.next();
			String nextpage = it.next();
			driver.switchTo().window(nextpage);
		     System.out.println(mainpage);
			System.out.println(driver.getTitle());
		}
	
	 
	public static void doubleClick(WebElement element) 
	{ 
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().perform();
	}
	
	
	
	    public static void AutoITUpload() throws InterruptedException, IOException {
	      Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\Uploads Files\\FIleU_Flatfile.exe");
		}


	    
public static String closeAlertAndGetItsText() 
{

    try 
    {
      new WebDriverWait(driver, Duration.ofSeconds(timeout)).ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) 
      {
        alert.accept();
      } else 
      {
        alert.dismiss();
      }
      return alertText;
    } 
    finally 
    {
      acceptNextAlert = true;
    }
  }


public static void loadicon(String loadingiconcontainer)
{
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
//    wait.until(ExpectedConditions.invisibilityOfElementLocated(ByLocator(loadingiconcontainer)));          
}

public static  void waitforelement(String loadingiconname) {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loadingiconname))); 
	}
public static  void LoadingIcondissapear(String loadingiconname) {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
//	 wait.until(ExpectedConditions.invisibilityOfElementLocated(ByLocator(loadingiconname)));	 
	}

	

public static String GetFieldValue(WebElement element) {
	int timeout=0;
	WebElement el=WaitForElementPresent(element, timeout); 
	return el.getText();
}	
 

	
public static List<WebElement>  GetAllValuesOfDroupDown(WebElement element) {
	int timeout=10;
	WebElement element2=WaitForElementPresent(element, timeout); 
	Select se = new Select(element2);
	List<WebElement> options = se.getOptions();
	return options;
}

 

	
	public static void Clear(WebElement element) {
		WebElement el=WaitForElementPresent(element, timeout);
		el.clear();
	}
	
	
	
	
	
	
	public static void ClearByIndex(List<WebElement> locator,int index) {
		 
		 WebElement el=locator.get(index);
		el.clear();
	}
	
	
	
	public static String Fun_UniqueName(String StrPrefixName) 
	{
		String strUQ;
		//strUQ = Fun_DateTime1();
		strUQ="";
		StrPrefixName = StrPrefixName+strUQ;
		return StrPrefixName;
	}
	
	
	
	public static String Fun_RemoveSpace(String s) {
		String withoutspaces = "";
		char ch = 'a';
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if (ch != 32)
				withoutspaces += s.charAt(i);
		}
		return withoutspaces;
	}

	
	public static WebElement WaitForElementPresent(WebElement element, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element1 = wait.until(ExpectedConditions.presenceOfElementLocated((By)element));
		return element1;
	}
	
	
//	
//    	// Handle click action
//	 		public static void clickOn(String locator) {
//	 			try
//	 			{
//	 			WaitForElementPresent(locator,timeout);
//	 //			WebDriverWait wait = new WebDriverWait(driver, timeout);
//	 			WebElement el = driver.findElement(ByLocator(locator));
//	 			waitForElementToBeClickable(driver,el);
//	 			el.click();
//	 			}
//	 			catch (Exception e) {
//					e.printStackTrace();
//				}
//	 		}
//	 		
	 		
//	 		
//	 	// Handle click action
//	 		public static void clickOnByIndex(String locator,int index) {
//	 			try
//	 			{
//	 			WaitForElementPresent(locator,timeout);
//	 //			WebDriverWait wait = new WebDriverWait(driver, timeout);
//	 			WebElement el=driver.findElements(ByLocator(locator)).get(index);
//	 			
//	 			//WebElement el = driver.findElements(ByLocator(locator));
//	 			waitForElementToBeClickable(driver,el);
//	 			el.click();
//	 			}
//	 			catch (Exception e) {
//					e.printStackTrace();
//				}
//	 		}
//	 		
//
//	    	// Handle click action
//		 		public static void clickOnWaitTime(String locator,int timeout) {
//		 			try
//		 			{
//		 			WaitForElementPresent(locator,timeout);
//		  			WebDriverWait wait = new WebDriverWait(driver, timeout);
//		 			WebElement el = driver.findElement(ByLocator(locator));
//		 			waitForElementToBeClickable(driver,el);
//		 			el.click();
//		 			}
//		 			catch (Exception e) {
//						e.printStackTrace();
//					}
//		 		}
//	 		
//	 		
//	 	// Handle click action
//	 		public static void clickByIndex(String locator,int position) 
//	 		{
//	 			try
//	 			{
//	 			WaitForElementPresent(locator,timeout);
//	 //			WebDriverWait wait = new WebDriverWait(driver, timeout);	
//	 			List<WebElement> el = driver.findElements(ByLocator(locator));
//	 			waitForElementToBeClickableListType(driver,el);
//	 			el.get(position).click();
//	 			}
//	 			catch (Exception e) 
//	 			{
//					e.printStackTrace();
//				}
//	 		}
//	 		
//	 		public static void scrollByXPath(String xpath)
//	 		{
//				JavascriptExecutor je = (JavascriptExecutor)driver;
//				//Identify the WebElement which will appear after scrolling down
//				WebElement element = driver.findElement(By.xpath(xpath));
//				// now execute query which actually will scroll until that element is not appeared on page.
//				je.executeScript("arguments[0].scrollIntoView(true);",element);
//				// Extract the text and verify
//				System.out.println(element.getText());
//	 		}
//	 		
//	 		public static void scrollByXPathWithIndexPos(String xpath,int pos)
//	 		{
//				JavascriptExecutor je = (JavascriptExecutor)driver;
//		
//				//Identify the WebElement which will appear after scrolling down
//				WebElement element = driver.findElements(By.xpath(xpath)).get(pos);
//				// now execute query which actually will scroll until that element is not appeared on page.
//				je.executeScript("arguments[0].scrollIntoView(true);",element);
//				// Extract the text and verify
//				System.out.println(element.getText());
//	 		}
//	 		public static void scrollLetToRight()
//	 		{
//				JavascriptExecutor je = (JavascriptExecutor)driver;
//				//Identify the WebElement which will appear after scrolling down
//				//WebElement element = driver.findElement(By.xpath(xpath));
//				// now execute query which actually will scroll until that element is not appeared on page.				
//				je.executeScript("window.scrollBy(1000,0)");
//
//				// Extract the text and verify
//				//System.out.println(element.getText());
//	 		}
//	 		 
//	 		
//	 		
//	 		public static void clickOn1(String locator) {
//	 			//WaitForElementPresent(locator, timeout);
//	 			//WaitForElementVisible(locator, timeout);
//	 			WebElement el = driver.findElement(By.linkText(locator));
//	 			el.click();
//	 		}
//	 		
//	 		public static void clicksave() {
//	 			
//	 		 WebElement element1 = driver.findElement(By.xpath(".//*[@id='submitone']"));
//	     	Actions action1 = new Actions(driver);
//	     	action1.moveToElement(element1).click().perform();
//	 		}
//	 		
//	 		
//	 		
//	 		public static void ClickSaveData() {
//	 			
//		 		 WebElement element2 = driver.findElement(By.xpath(".//*[@id='btnsave']"));
//		     	Actions action2 = new Actions(driver);
//		     	action2.moveToElement(element2).click().perform();
//		 		}
//	 		
//	 		
//	 		public static void ClickSaveandSend() {
//	 			
//		 		 WebElement ele = driver.findElement(By.xpath("//*[@id='winsumsaveandsend']"));
//		     	Actions act = new Actions(driver);
//		     	act.moveToElement(ele).click().perform();
//		 		}
//	 		
//	 		
//	 		public static void ClickPopUP_ok() throws InterruptedException {
//	 			Thread.sleep(2000);
//	 		//int size=driver.findElements(By.xpath("//*[@id='btnOk']")).size();
//	 		int size=driver.findElements(By.xpath("//*[@id='btnNtfOptOk']")).size();
//	 		 driver.findElements(By.xpath("//*[@id='btnNtfOptOk']")).get(size-1).click();
//	 		}
//	 		
//	 		
//	 		
//	 		public static void mouseclick(String locator) {
//				JavaBase.WaitForElementVisible(locator, timeout);
//				WebElement el = driver.findElement(ByLocator(locator));
//				Actions builder = new Actions(driver);
//				builder.moveToElement(el).click().perform();
//			}
//	 		
//	 		public static void dragNDrop(String Xpath) 
//	           {
//			@SuppressWarnings("unused")
//			WebElement element = driver.findElement(ByLocator(Xpath));
//			Actions action = new Actions(driver);
//	                WebElement sourcelocator = driver.findElement(ByLocator(Xpath));
//	                WebElement DestinationLocator = driver.findElement(ByLocator(Xpath));
//	                action.dragAndDrop(sourcelocator,DestinationLocator).build().perform(); 
//
//	           }        		
//	 		public static WebElement WaitForElementVisible(String locator, int timeout) {
//
//				WebDriverWait wait = new WebDriverWait(driver, timeout);
//				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByLocator(locator)));
//				return element;
//			}
//	 		
//	 		public static WebElement WaitForElemePresent(String locator, int timeout) {
//
//				WebDriverWait wait = new WebDriverWait(driver, timeout);
//				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(ByLocator(locator)));
//				return element;
//			}
//
//	 		
//	 		public  static  boolean isTextPresent(String text){
//	 	        try{
//	 	            boolean b = driver.getPageSource().contains(text);
//	 	            return b;
//	 	        }
//	 	        catch(Exception e){
//	 	            return false;
//	 	        }
//	 	  }	
//	 		
//	 		
//	 		public static String getCurrenturl(){
//				return driver.getCurrentUrl();
//				
//				
//			}
//	 		public static String ChangeCurrenturl_TestRecord(){
//	 			String CURL = driver.getCurrentUrl();
//	 			driver.get(CURL+"&tstrcd=1");
//				return CURL;
//			}
//	 		
//	 		
//	 		
//	 		public static void selectDropDownByIndex(String locator, int index) {
//				Assert.assertTrue(isElementPresent(locator), "Element Locator :"
//						+ locator + " Not found");
//				JavaBase.WaitForElementPresent(locator, 60);
//				new Select(driver.findElement(ByLocator(locator)))
//				.selectByIndex(index);
//
//			}
//
//	 		public static void selectDropDownByIndexAndClickPosition(String locator, int index,int pos) {
//				Assert.assertTrue(isElementPresent(locator), "Element Locator :"
//						+ locator + " Not found");
//				JavaBase.WaitForElementPresent(locator, 60);
//				new Select(driver.findElements(ByLocator(locator)).get(pos))
//				.selectByIndex(index);
//
//			}
//	 		
//
//	 		
//	 		public static void selectDropDownByIndexWebElement(WebElement webElement, int index) {
//				new Select(webElement)
//				.selectByIndex(index);
//			}
//	 		
//	 		
//	 		
//	 		public static void selectDropDownByvalue(WebElement element, String value) {
//				 WebElement locator=WaitForElementPresent(element, 60);
//				new Select(locator).selectByValue(value);
//			}
//	 		
//	 			
//	 		public static String Fun_DateTime1() {
//	 	        {
//	 	        DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
//	 	        Date date = new Date();
//	 	        String Str_Date = dateFormat.format(date);
//	 	         return Str_Date;
//	 	        }
//	 		}
//	  		
//	 		
//	 		 
//	 		public static String getValueByWebElement(WebElement webElement)
//	 		{
//	 			//driver.switchTo().alert().accept();
//	 			String strMessageLang="";
//	 			try
//	 			{
//	 				//*[@id="sfMessage"]
//	 			  //WebElement webElement =driver.findElement(By.xpath(xpath));
//	 	           strMessageLang = webElement.getText();
//	 			}
//	 			catch(Exception ex)
//	 			{
//	 				System.out.println(ex);
//	 				ex.printStackTrace();
//	 			}
//			   return strMessageLang;
//	 			
//	 		}
//	 		
//	 		public static String getReadOnlyFieldValue(String xpath)
//	 		{
//	 			
//	 			WebElement input = driver.findElement(By.xpath(xpath));
//	 			String value=input.getAttribute("value");
//				return value;
//	 			
//	 		}
//	 		
//	 		
//	 		
//	 		
//	 		
//	 		public static String getAttributeByXpath(String xpath,String attributeName)
//	 		{
//	 			 String value=null;
//	 			try
//	 			{
//	 				WebElement el = driver.findElement(ByLocator(xpath));
//	 				value=el.getAttribute(attributeName);
//	 			}
//	 			catch(Exception e)
//	 			{
//	 				e.printStackTrace();
//	 			}
//				return value;
//	 		}
//	 		
//	 		
//	 		public static String getalertValue() {
//	 			String text=null;
//	 			/*WebDriverWait wait = new WebDriverWait(driver,0);
//	 			
//	 			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//	 			driver.switchTo().alert().accept();
//	 			String s = alert.getText();
//	 			System.out.println(s);*/
//	 			WebDriverWait wait = new WebDriverWait(driver, 10 /*timeout in seconds*/);
//                if(wait.until(ExpectedConditions.alertIsPresent())==null){
//                      System.out.println("alert was not present");
//                }
//                else
//                {
//                 Alert alert = driver.switchTo().alert();
//                alert.accept();
//                text=alert.getText();
//                
//                System.out.println("alert was present and accepted");
//                }
//	 			
//	 			return text;
//	 		}
//	 		
//	 		public static void selectSE(){
//	 			WebElement element = driver.findElement(By.xpath("//*[@id='MyTr_pim2mleadsaseassigned']/span[4]/div/button"));
//	 			Actions action = new Actions(driver);
//	 			action.moveToElement(element).click().perform();
//	 		      driver.findElement(By.linkText("Plasma SuperUser (plasmasuperuser)")).click();
//	 		}
//	 		
//	 		
//	 		
//	 		public static  void uploadDataByAutoIt(String locator,String path)
//	 		{
//	 			
//	 			 try {
//	 				 Thread.sleep(3000);
//	 				 JavaBase.clickOn(locator);
//	 				Runtime.getRuntime().exec(path);
//	 			} catch (Exception e1) {
//	 				// TODO Auto-generated catch block
//	 				e1.printStackTrace();
//	 			}
//	 		}
//	 		
//	 		public static void checkradio(String strxpath){
//	 			
//	 			WebElement ele=JavaBase.driver.findElement(By.xpath(strxpath));
//	 			ele.click();
//	 			
//	 		}
//	 		
//	 		public static void Close_CurrentTab(){
//	 			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"w");
//	 			 		}
//	 		
//	 		public static Boolean isElementPresent(String locator) {
//				Boolean result = false;
//				try {
//					driver.findElement(ByLocator(locator));
//					result = true;
//				} catch (Exception ex) {
//				}
//				return result;
//			}
//	 		
//	 		public static  String randomString() throws InterruptedException {
//				Random random = new Random(System.nanoTime() % 100000);
//
//				  int randomInt = random.nextInt(1000000000);
//					
//				 return String.valueOf(randomInt);		
//				    
//			}	
//	 		
//	 		
//	 		public static int createRandomNumber()
//	 		{
//	 			Random rand = new Random();
//	 			int n = rand.nextInt(50) + 1;
//	 			return n;
//	 		}
//	 		
//	 		public static String Fun_DateTimeValue(String Str_Date) throws ParseException, java.text.ParseException {
//				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//				Date date = new Date();
//				String Str_Date1 = dateFormat.format(date);
//				
//				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//				Calendar c = Calendar.getInstance();
//				c.setTime(sdf.parse(Str_Date1));
//				c.add(Calendar.DATE, 4);  // number of days to add
//				Str_Date1 = sdf.format(c.getTime());  
//				return Str_Date1;
//			}
//	
//	 		
//	 		
//	 		
//	 		public static String getCurrentDay (){
//
//	 		    //Create a Calendar Object
//	 		    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
//
//	 		    //Get Current Day as a number
//	 		    int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
//	 		   // System.out.println("Today Int: " + todayInt +"\n");
//
//	 		    //Integer to String Conversion
//	 		    String todayStr = Integer.toString(todayInt);
//	 		    //System.out.println("Today Str: " + todayStr + "\n");
//
//	 		    return todayStr;
//	 		}
//
//	 		
//	 		
//	 		public static String Fun_CurrentDate(String str_Date) throws ParseException, java.text.ParseException {
//	 			DateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
//	 			Date date1 = new Date();
//	 			String str_Date2 = dateFormat1.format(date1);
//	 			
//	 			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
//	 			Calendar c = Calendar.getInstance();
//	 			c.setTime(sdf1.parse(str_Date2));
//	 			c.add(Calendar.DATE, 2);  // number of days to add
//	 			str_Date2 = sdf1.format(c.getTime());  
//	 			return str_Date2;
//	 					}
//	 		
//	 		
//	 		
//	 		public static void DynamicWait(String StrXpath) {
//				WebDriverWait wait = new WebDriverWait(JavaBase.driver,120);
//				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(StrXpath)));
//			}
//	
//	 		
//	 		public static void Scrollelement(String string)
//	 		{
//	 		 WebElement StoreApph = driver.findElement(By.xpath(string));
//	 		 JavascriptExecutor je = (JavascriptExecutor)driver;
//	 		 je.executeScript("arguments[0].scrollIntoView(true);",StoreApph);
//	 		 //System.out.println("Scroll executed");
//	 		//
