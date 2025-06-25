package com.CK.Playwright.Browser;

import java.util.List;
import java.util.function.Consumer;

import com.CK.Playwright.BaseTest.BaseTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.CDPSession;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class WebBrowser extends BaseTest implements Browser{

	private boolean BoolBrowserExtensionRequired = false;
	private static boolean Headless = false;
	
	
	
	
	/**
	 * Opens the browser provided in the properties file like Chrome, ff, IE. it will pick up the Chrome, if no value provided
	 * @author (chk017) kaja ChennnakesavaRao Bachu
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
			WebBrowser.chromeSetup();
//			chromeSetup();
		}else if(sBrowser.equalsIgnoreCase("FF") || sBrowser.equalsIgnoreCase("firefox")){
			firefoxSetup();
		}else if(sBrowser.equalsIgnoreCase("EDGE")) {
			edgeSetup();
			
		}else if(sBrowser.equalsIgnoreCase("WEBKIT")) {
			webkitSetup();
		}else {	

			System.out.println("Choosing the default Browser as : Chrome " );
//			chromeSetup();
			WebBrowser.chromeSetup();
			 
		}
//		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofMinutes(iPageLoadTimeout));
		page.setDefaultTimeout(iTimeout*1000);
		
		
	}
	
	public static void chromeSetup() {
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Headless).setChannel("chrome"));
		page = browser.newPage();
		
		
	}
	
	private static void edgeSetup() {
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Headless).setChannel("msedge"));
		page = browser.newPage();
	}
	
	private static void firefoxSetup() {
		Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(Headless));
		page = browser.newPage();
	}
	
	private static void webkitSetup() {
		Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(Headless));
		page = browser.newPage();
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
