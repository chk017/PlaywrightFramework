package com.Diyar.Playwright.BrowserOptions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;

public class BrowserOptions {

	
	public static NewContextOptions options() {
		
		
		// Add ignoreHTTPSErrors here
		Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
		    .setIgnoreHTTPSErrors(true)
//		    .setRecordVideoDir(Paths.get(BaseTest.sRecordingsPath))
		    ;
		
		
		
		
		return contextOptions;
	}
	
}
