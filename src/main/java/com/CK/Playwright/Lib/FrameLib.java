package com.CK.Playwright.Lib;

import com.CK.Playwright.BaseTest.BaseTest;
import com.CK.Playwright.Reporting.Reporting;
import com.CK.Playwright.Util.Util;
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
