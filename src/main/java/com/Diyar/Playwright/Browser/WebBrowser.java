package com.Diyar.Playwright.Browser;

import java.util.List;
import java.util.function.Consumer;

import com.Diyar.Playwright.BaseTest.BaseTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.CDPSession;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class WebBrowser extends BaseTest implements Browser{

	private boolean BoolBrowserExtensionRequired = false;
	private static boolean Headless = false;
	
	
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
		playwright = Playwright.create();
		
		BoolBrowserExtensionRequired = Boolean.parseBoolean(getproperty("BrowserExtensionRequired"));
		System.out.println(" BoolBrowserExtensionRequired : " + BoolBrowserExtensionRequired);
		Headless = Boolean.parseBoolean(getproperty("Headless"));
		

		if(sBrowser == null) {
			sBrowser = getproperty("browser");
		}else { System.out.println("Choosen Browser : " + sBrowser); }

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
	
	/**
	 * chromeSetup is a utility that launches the Chrome browser using Playwright
	 * in either headless or headed mode based on the configuration provided in `config.properties`.
	 * It also starts tracing to capture screenshots, snapshots, and source code for debugging purposes.
	 * 
	 * @author kaja ChennnakesavaRao Bachu
	 */
	public static void chromeSetup() {
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Headless).setChannel("chrome"));
		
		context = browser.newContext();
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
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Headless).setChannel("msedge"));
		context = browser.newContext();
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
		Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(Headless));
		context = browser.newContext();
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
		Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(Headless));
		context = browser.newContext();
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
