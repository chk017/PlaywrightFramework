package com.Diyar.Playwright.Browser;

import java.util.List;

import java.util.function.Consumer;

import com.Diyar.Playwright.BaseTest.BaseTest;
import com.Diyar.Playwright.BrowserOptions.BrowserOptions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.CDPSession;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
//import java.util.Map;
//import com.microsoft.playwright.APIRequest;
//import com.microsoft.playwright.APIRequestContext;


public class WebBrowser extends BaseTest implements Browser{

	private boolean BoolBrowserExtensionRequired = false;
	private static boolean Headless = false;
//	private static boolean API = false;
	
	
	/**
	 * openBrowser is a utility that launches a browser using Playwright
	 * based on the configuration provided in `config.properties`. Supported browsers include:
	 * <ul>
	 *     <li>Chrome (default)</li>
	 *     <li>Firefox</li>
	 *     <li>WebKit (used as a placeholder for IE, since IE is not supported by Playwright)</li>
	 * </ul>
	 *
	 * <p>If no browser is specified, Chrome is used by default.</p>
	 *
	 * <p>Features:
	 * <ul>
	 *     <li>Reads browser type and execution mode from a properties file</li>
	 *     <li>Launches the specified browser in headless or headed mode</li>
	 *     <li>Starts and stops tracing, saving output to trace.zip</li>
	 * </ul>
*/
	
	public void openBrowser() { 
		System.out.println("Execution in BrowserStack : "+ booleanBrowserStack);
//		playwright = Playwright.create();
		
//		Load configuration flags
		BoolBrowserExtensionRequired = Boolean.parseBoolean(getproperty("BrowserExtensionRequired"));
		Headless = Boolean.parseBoolean(getproperty("Headless"));
//		API = Boolean.parseBoolean(getproperty("API"));
		
		System.out.println(" BoolBrowserExtensionRequired : " + BoolBrowserExtensionRequired);
	
		/*
		 * if(API) { setupApiEnvironment(); }
		 */
		

		if(sBrowser == null || sBrowser.trim().isEmpty()) {
			sBrowser = getproperty("browser");
		}else { 
			
			System.out.println("Choosen Browser : " + sBrowser); }

		if(sBrowser.equalsIgnoreCase("Chrome") || sBrowser.equalsIgnoreCase("")) {
//			chromeSetup();
			WebBrowser.chromeSetup();
		}else if(sBrowser.equalsIgnoreCase("FF") || sBrowser.equalsIgnoreCase("firefox")){
			firefoxSetup();
		}else if(sBrowser.equalsIgnoreCase("EDGE")) {
			edgeSetup();
			
		}else if(sBrowser.equalsIgnoreCase("WEBKIT")) {
			webkitSetup();
		}else {	

			System.out.println("Choosing the default Browser as : Chrome " );
			WebBrowser.chromeSetup();
			 
		}
		
		
		page.setDefaultTimeout(iTimeout*1000);
		
		
	}
	
	
	
	/*
	 * private void setupApiEnvironment() {
	 * System.out.println("API mode enabled. Setting up API environment...");
	 * 
	 * // Initialize playwright // playwright = Playwright.create(); APIRequest
	 * apiRequest = playwright.request(); // APIRequestContext apiRequestContext =
	 * apiRequest.newContext();
	 * 
	 * 
	 * 
	 * String baseUrl = getproperty("api.baseUrl"); String token =
	 * getproperty("api.token"); // Or get from secrets manager
	 * 
	 * if (baseUrl == null || baseUrl.isEmpty()) { throw new
	 * IllegalArgumentException("API base URL is not configured."); }
	 * 
	 * // Example: Create API context using Playwright (if you're using Playwright
	 * API testing) APIRequest.NewContextOptions options = new
	 * APIRequest.NewContextOptions() .setBaseURL(baseUrl)
	 * .setExtraHTTPHeaders(Map.of( "Authorization", "Bearer " + token,
	 * "Content-Type", "application/json" ));
	 * 
	 * apiRequestContext = apiRequest.newContext(options);
	 * System.out.println("API request context created with base URL: " + baseUrl);
	 * }
	 */

	
	/*
	 * APIResponse response = apiRequestContext.get("/users/123");
	 * System.out.println("Status: " + response.status());
	 * System.out.println("Body: " + response.text());
	 * 
	 */	
	
	
		
	
	/**
	 * chromeSetup is a utility that launches the Chrome browser using Playwright
	 * in either headless or headed mode based on the configuration provided in `config.properties`.
	 * It also starts tracing to capture screenshots, snapshots, and source code for debugging purposes.
	 * 
	 * @author kaja ChennnakesavaRao Bachu
	 */
	public static void chromeSetup() {
//		if(API) Headless = API;
		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Headless).setChannel("chrome"));
		
		
		// Add ignoreHTTPSErrors here
	/*	Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
		    .setIgnoreHTTPSErrors(true);
		*/
		
		NewContextOptions newContextOptions = BrowserOptions.options();
		
//		System.out.println("a : "+ a);
//		context = browser.newContext();
		context = browser.newContext(newContextOptions);
		
		// Start tracing
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
		page = context.newPage();

		
		
	}
	
	/**
	 * edgeSetup is a utility that launches the Chrome browser using Playwright
	 * in either headless or headed mode based on the configuration provided in `config.properties`.
	 * It also starts tracing to capture screenshots, snapshots, and source code for debugging purposes.
	 * 
	 * @author kaja ChennnakesavaRao Bachu
	 */
	private static void edgeSetup() {
//		if(API) Headless = API;
		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Headless).setChannel("msedge"));
//		context = browser.newContext();
		
	NewContextOptions newContextOptions = BrowserOptions.options();
		
		context = browser.newContext(newContextOptions);
		
		// Start tracing
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
		page = context.newPage();
	}
	
	/**
	 * firefoxSetup is a utility that launches the Chrome browser using Playwright
	 * in either headless or headed mode based on the configuration provided in `config.properties`.
	 * It also starts tracing to capture screenshots, snapshots, and source code for debugging purposes.
	 * 
	 * @author kaja ChennnakesavaRao Bachu
	 */
	private static void firefoxSetup() {
//		if(API) Headless = API;
		
		playwright = Playwright.create();
		Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(Headless));
//		context = browser.newContext();
		
	NewContextOptions newContextOptions = BrowserOptions.options();
		
//		System.out.println("a : "+ a);
//		context = browser.newContext();
		context = browser.newContext(newContextOptions);
		
		// Start tracing
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
		page = context.newPage();
	}
	
	/**
	 * WebKitLauncher is a utility class that launches the WebKit browser using Playwright
	 * in either headless or headed mode based on the configuration provided in `config.properties`.
	 * It also starts tracing to capture screenshots, snapshots, and source code for debugging purposes.
	 *
	 *	@author kaja ChennnakesavaRao Bachu
	 */
	private static void webkitSetup() {
//		if(API) Headless = API;
		
		playwright = Playwright.create();
		Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(Headless));
//		context = browser.newContext();
		
	NewContextOptions newContextOptions = BrowserOptions.options();
		
//		context = browser.newContext();
		context = browser.newContext(newContextOptions);
		// Start tracing
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
		page = context.newPage();
	}

	@Override
	public void onDisconnected(Consumer<Browser> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void offDisconnected(Consumer<Browser> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BrowserType browserType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close(CloseOptions options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BrowserContext> contexts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CDPSession newBrowserCDPSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrowserContext newContext(NewContextOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page newPage(NewPageOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startTracing(Page page, StartTracingOptions options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] stopTracing() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String version() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
