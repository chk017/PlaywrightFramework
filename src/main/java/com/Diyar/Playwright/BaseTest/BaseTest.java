package com.Diyar.Playwright.BaseTest;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Diyar.Playwright.Browser.WebBrowser;
import com.Diyar.Playwright.Data.ReadExcel;
import com.Diyar.Playwright.Lib.FrameLib;
import com.Diyar.Playwright.Reporting.Reporting;
import com.Diyar.Playwright.Util.Util;
import com.browserstack.local.Local;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import ch.qos.logback.classic.Logger;

//import atu.testrecorder.exceptions.ATUTestRecorderException;
//import atu.testrecorder.ATUTestRecorder;

/**
 * BaseTest class
 * @author ChennakesavaRao Bachu
<<<<<<< HEAD
 * 14-July-2025
=======
 * 08-July-2025
>>>>>>> dcfd118120013ded9a697274720b2f6a26b2e189
 */
public class BaseTest {
	protected static Playwright playwright; //= Playwright.create();
	protected static String sProjectDirectory = System.getProperty("user.dir");
	protected static String sTestdataPath = sProjectDirectory + "/Testdata/";
	protected static String sReportsPath = sProjectDirectory + "/Reports/";
	protected static String sRecordingsPath = sProjectDirectory + "/Recording/";
	protected static String sTracingPath = sProjectDirectory + "/Tracing/";
	
	protected ReadExcel readexcel = new ReadExcel();

	public static Logger logger; // ch.qos.logback.classic.
	protected String sThisMethod;
	private Properties p = new Properties();
	private FileReader reader;
	private String sTestdataFile;
	public static String sURL;
	protected boolean booleanBrowserStack;
	protected boolean boolVideoRecording = false;
	protected static String sBrowserStackUsername ;
	protected static String sBrowserStackAccesskey ;
	public static int iTimeout = 30;
	protected static int iPageLoadTimeout = 30;
	public static Page page;
	
	protected HashMap<String, String> LocalBSArgs = new HashMap<String, String>();
	protected Local localBrowserStack = new Local();
	public ThreadLocal< Hashtable<String, String>> dataMap;
	
	public String sBrowser;
	protected WebBrowser browser;
	public static FrameLib lib;
//	public static ATUTestRecorder recorder;
	public static BrowserContext context;
	
	public BaseTest() {
//		playwright = Playwright.create();
		
		
		try {
			reader = new FileReader("Config.properties");
			p.load(reader);
			reader.close();

			sURL = getproperty("url");
			sTestdataFile = getproperty("testdatafile");
			sBrowserStackUsername = getproperty("browserStackUser");
			sBrowserStackAccesskey = getproperty("browserStackkey");
			booleanBrowserStack = Boolean.parseBoolean(getproperty("browserstack"));
			boolVideoRecording = Boolean.parseBoolean(getproperty("VideoRecording"));

			try {
//				iTimeout = Duration.ofMinutes(Integer.parseInt(p.getProperty("waitSeconds_BeforeException")));
				iTimeout = (Integer.parseInt(p.getProperty("waitSeconds_BeforeException")));
				iPageLoadTimeout = Integer.parseInt(p.getProperty("PageLoadTimeout"));
			}catch(java.lang.NumberFormatException e) {
				System.out.println("NumberFormatException : may be \"waitSeconds_BeforeException\" is not initialized in Config, it is considering the default value of 30 seconds for Timedout.");
			}

		}catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
	/**
	 * This method will get the property values from Config.properties
	 * @param key - this is the name provided in the Config.properties
	 * @return - This will return the value corresponding to the name provided as key
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public String getproperty(String sProperty) {
		return p.getProperty(sProperty);
	}
	
	
	/**
	 * This method will configure the Log4j, Checks the report path and creates if absent and creates reporting object. Automation tester is not required to call this method intentionally.
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	@BeforeSuite
	protected void initialize() {
		Util.createFolder(sReportsPath);
		Util.createFolder(sTracingPath);
		Reporting.startReporting(this.getClass().getSimpleName());

		if(booleanBrowserStack) {
			LocalBSArgs.put("key", sBrowserStackAccesskey);
			try {

				localBrowserStack.start(LocalBSArgs);
				System.out.println("Local Browser stack : " + localBrowserStack.isRunning());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	/*	
		if(boolVideoRecording) {
			
			
			  try { Util.createFolder(sRecordingsPath); 
			  recorder = new ATUTestRecorder(sRecordingsPath, this.getClass().getSimpleName() +
			  Util.getCurrentDatenTime(Util.getformat()), false); } catch
			  (ATUTestRecorderException e) { // TODO Auto-generated catch block
			  e.printStackTrace(); }
			 		
		}
*/
	}


	/**
	 * This method will execute for every class and defines the objects for reporting, browser and frameLib.
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	@BeforeClass
	protected void beforeClass(ITestContext context) {

		lib = new FrameLib();
		browser = new WebBrowser();
//		browser.openBrowser(context.getCurrentXmlTest().getParameter("browsername"), booleanBrowserStack);
		browser.openBrowser();
		
	/*	
		if(boolVideoRecording) {
			
			try {
				recorder.start();
			} catch (ATUTestRecorderException e) {
				e.printStackTrace();
			}
			 
			}
		*/
		
	}


	/**
	 * This method is used to get the value from the excel workbook provided as parameter sTestdataFile and gets the value from the column provided as parameter rowheading. 
	 * Also it retrieves the script name from the Test method that retrieves by using the TestNG before method
	 * @param sTestdataFile - Excel work book in which test data exists which is declared in config.properties
	 * @param rowheading - column where test data exists for the row test script name
	 * @return - String value
	 */
	private Hashtable<String, String> getDataWOSheet(String sTestdataFile, String rowheading){
		sTestdataFile = sTestdataPath + sTestdataFile ;
		return readexcel.getDataWOSheet(sTestdataFile, rowheading);
	}

	/**
	 * This method initializes reporting object, Logger object, gets the test data for each @Test 
	 * @param m : This focuses on the @Test method and we are using to get the name of the execution method
	 * @author  kaja ChennnakesavaRao Bachu 
	 */
	@BeforeMethod
	protected void beforeMethod(Method m) {

		Hashtable<String, String> data = new Hashtable<String,String>();

		sThisMethod = m.getName();
		Reporting.startTest(sThisMethod);
		 logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(sThisMethod);
		 
		logger.info(" \n\n <<<<<<<<<<<<<<<<<<<<<<   starting Test  :  "+sThisMethod+"  >>>>>>>>>>>>>>>>>>>>>>>");

		dataMap = new ThreadLocal<Hashtable<String, String>>();
		data = getDataWOSheet(sTestdataFile, sThisMethod);
		dataMap.set(data);

		System.out.println("Test data Keys and Values for script : " + sThisMethod + " : \n " + dataMap.get().entrySet());

//		Capabilities cap = ((RemoteWebDriver) getDriver()).getCapabilities();
//		Reporting.info(sThisMethod+" is running on Browser : " + cap.getBrowserName().toLowerCase());

		
	}

	/**
	 * This method gets the sTestdataFile, sTestdataSheet from the Config.properties 
	 * If Automation Engineer specify the Test data files as below, it will get the values from it
	 * 	private String sTestdataFile = "DSP_Testdata.xlsx";
	 *	private String sTestdataSheet = "TestSheet";
	 * @param sColumnName Name of the heading provided in the column
	 * @return String
	 * @author  Kaja ChennnakesavaRao Bachu
	 */
	public String getdata(String sColumnName) {
		return dataMap.get().get(sColumnName);
	}

	/**
	 * This method executes after each @Test method and collects the Test status and prints the result in Report. Incase of failure it prints the Exception. User is not required to call explicitly.
	 * It also ends the Test Object of reporting. 
	 * @param result : This will be collected from Test status.
	 * @author  kaja ChennnakesavaRao Bachu 
	 */
	@AfterMethod
	protected void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			Reporting.fail("Test Failed  : "+ result.getThrowable(), true);
//			Reporting.fail("Test status : "+result.getStatus(), true);
//			Reporting.fail("Test : "+result.getName()+" is Failed  with the instance : " + result.getInstanceName());
		}
		logger.info("\n \n xxxxxxxxxxxxxxxx   -   End of Test  -  xxxxxxxxxxxxxxxx \n ");
		
	}

	@AfterClass
	protected void closeActiveBrowser() {
		
		/*
		if (boolVideoRecording) {

			try {
				recorder.stop();
			} catch (ATUTestRecorderException e) {
				// TODO Auto-generated catch block e.printStackTrace();
			}

		}
		
		*/
		System.out.println("after class execution in closing ActiveBrowser :");
		
		// Stop tracing and save to file
		context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(sTracingPath+"/trace_"+this.getClass().getSimpleName()+".zip")));
		
//		getDriver().quit();
	}

	/**
	 * This method executes after all the tests execution has been completed. User do not required to call this method explicitly.
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	@AfterSuite
	protected void teardown() {
		logger.info(" \n \n =============================$$$$$$$$$$$$$$$$$$$$  -  End of Report  -  $$$$$$$$$$$$$$$$$$$$===================================================");

		Reporting.reportflush();
//		Reporting.reportclose();
		
		try {

			if(localBrowserStack.isRunning()) 
				localBrowserStack.stop();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Util.deleteFolder(sReportsPath + "/Images");
		Util.deleteFolder(sProjectDirectory + "/jxl.log");
		Util.deleteFolder(sProjectDirectory + "/debug.log");
		
	}
	
	
//	private static void chromeSetup(String url) {
	
//	private static void edgeSetup(String url) {
	
//	private static void firefoxSetup(String url) {

	
	public Page getPage() {
		return page;
		
//		return pageThreadLocal.set(page);
	}

}
