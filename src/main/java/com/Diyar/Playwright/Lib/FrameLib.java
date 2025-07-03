package com.Diyar.Playwright.Lib;

import com.Diyar.Playwright.BaseTest.BaseTest;
import com.Diyar.Playwright.Reporting.Reporting;
import com.Diyar.Playwright.Util.Util;
import com.microsoft.playwright.Locator;

public class FrameLib {

	
	public void highlight(String sElement) {
		
		// Locate the element you want to highlight
        Locator elementToHighlight = BaseTest.page.locator(sElement); 
        elementToHighlight.evaluate("(element) => {element.style.backgroundColor = '#FFFF00'; element.style.border = '3px solid green'; }");
        Util.sleepforseconds(1);
        elementToHighlight.evaluate("(element) => {element.style.backgroundColor = ''; element.style.border = ''; }");
	}
	
	
	
	public void click(String sElement) {
		
		
		
		BaseTest.page.locator(sElement).highlight();
		BaseTest.page.click(sElement);
		Reporting.pass("System successfuly clicked the element. ");
	}
	
	
}
