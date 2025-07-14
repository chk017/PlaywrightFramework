package com.Diyar.Playwright.Lib;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.ByteArrayInputStream;

import com.Diyar.Playwright.BaseTest.BaseTest;
import com.Diyar.Playwright.Reporting.Reporting;
import com.Diyar.Playwright.Util.Util;
import com.microsoft.playwright.Locator;

import io.qameta.allure.Allure;

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
	
	/**
	 * This method will maximize the window through Keys
	 * 
	 * @author (chk017) kaja ChennnakesavaRao Bachu
	 */
	public void Maximizethewindow() throws AWTException {
		Robot rb = new Robot();
		rb.keyPress(java.awt.event.KeyEvent.VK_ALT);
		rb.keyPress(java.awt.event.KeyEvent.VK_SPACE);
		rb.keyPress(java.awt.event.KeyEvent.VK_X);

		rb.keyRelease(java.awt.event.KeyEvent.VK_X);
		rb.keyRelease(java.awt.event.KeyEvent.VK_SPACE);
		rb.keyRelease(java.awt.event.KeyEvent.VK_ALT);
	}
	
	/**
	 * This method will attach the screenshot to the allure report with the names mentioned in parameters 
	 * @param stepName - name of the step with a triangle icon that can be expanded to see the screenshot
	 * @param screenshotName - file name with the image
	 * @author ChennakesavaRao
	 */
	public void screenshotToAllure(String stepName, String screenshotName) {
		
//		Allure.step("Attach failure screenshot 69", () -> Allure.attachment("Screenshot", new ByteArrayInputStream(page.screenshot())));
		
		Allure.step(stepName, () -> Allure.attachment(screenshotName, new ByteArrayInputStream(BaseTest.page.screenshot())));
	}
}
