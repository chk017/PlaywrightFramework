package com.Diyar.Playwright.Elements;


import java.io.ByteArrayInputStream;

import org.testng.Assert;

import com.Diyar.Playwright.BaseTest.BaseTest;
import com.Diyar.Playwright.Reporting.Reporting;
import com.Diyar.Playwright.Util.Util;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;


public class Elements {

	private String sElement;
//	private int defaultTime = 30;
	public int iTimeout = BaseTest.iTimeout;
	
	
	public Elements(String sXpath) {
		this.sElement = sXpath;
	}
	
	/**
	 * waitforElement method is used to wait for the element before performing any operation on it
	 * @param name - name of the field
	 * @author - ChennakesavaRao Bachu  
	 */
	private void waitforElement(String name) {
//		System.out.println("waiting for element "+name);
		try {
			BaseTest.logger.info("waiting for element : "+name);
			BaseTest.page.waitForSelector(sElement);
			BaseTest.lib.highlight(sElement);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Reporting.fail("Element <b>"+name+"</b> timed out after waiting ", true);
			Allure.step("Element timed out after waiting ", () -> Allure.attachment("Element "+name + " Screenshot ", new ByteArrayInputStream(BaseTest.page.screenshot())));
			e.printStackTrace();
		}
		
	}
	
	/**
	 * click method is used to click on element. Before clicking, system wait for element, highlights the element and clicks on it
	 * @param name - name of the field
	 * @author - ChennakesavaRao Bachu  
	 */
	public void click(String name) {
		waitforElement(name);
//		BaseTest.page.locator(sElement).highlight();
		BaseTest.page.click(sElement);
		Reporting.pass("System successfuly clicked the element : <b>"+name+"</b>");
		Allure.step("System successfuly clicked the element : "+name);
		BaseTest.logger.info("System successfuly clicked the element : "+name);
	}

	
	/**
	 * fill method is used to enter a value in the field. Before filling, system wait for the element, highlights the element, fills the value in the field and finally verifies the value. 
	 * @param value - value to enter in the field
	 * @param name - name of the field 
	 * 
	 * @author - kaja ChennnakesavaRao Bachu
	 */
	public void fill(String value, String name) {
//		BaseTest.page.waitForSelector(sElement);
		waitforElement(name);
		BaseTest.page.fill(sElement, value);
//		BaseTest.page.locator(sElement).highlight();
		BaseTest.logger.info("System successfully enters the "+value+" in the field : "+name);
//		Allure.step("System successfully enters the "+value+" in the field : "+name);
		verifyInputText(value, name);
		
		Assert.assertEquals(value, value);
	}
	
	
	/**
	 * verifyElementPresent is a method to verify the visibility of element and prints in the reporting
	 * @param name - It is just name of the element 
	 * @author - ChennakesavaRao Bachu
	 */
	public void verifyElementPresent(String name) {
		/*if(isElementPresent()) {*/
		if(isElementVisible()) {
			Reporting.pass("System successfully displays the Element: <b>"+name+"</b>");
			Allure.step("System successfully displays the Element: "+name);
			BaseTest.logger.info("System successfully displays the Element: "+name);
//			Allure.step("Element present in time", () -> Allure.attachment("Element "+name + " Screenshot ", new ByteArrayInputStream(BaseTest.page.screenshot())));
		}else {
			Reporting.fail("System failed to display the Element: <b>"+name+"</b>", true);
			Allure.step("System failed to display the Element: "+name, Status.FAILED);
			BaseTest.logger.info("System failed to display the Element: "+name);
			Allure.step("Element not present ", () -> Allure.attachment("Element "+name + " Screenshot ", new ByteArrayInputStream(BaseTest.page.screenshot())));
		}
	}
	
	
	

	/**
	 * verifyElementAbsent is a method to verify the absence of element and prints in the reporting. This method waits for 3 seconds to confirm the absence of element
	 * @param name - It is just name of the element 
	 * @author - ChennakesavaRao Bachu
	 */
	public void verifyElementAbsent(String name) {
		/*if(isElementPresent()) {*/
		if(isElementAbsent()) {
			Reporting.pass("The system successfully hides the display of the element : <b>"+name+"</b>");
	
			Allure.step("The system successfully hides the display of the element : "+name);
			BaseTest.logger.info("The system successfully hides the display of the element : "+name);
//			Allure.step("Element present in time", () -> Allure.attachment("Element "+name + " Screenshot ", new ByteArrayInputStream(BaseTest.page.screenshot())));
		}else {
			Reporting.fail("The system failed to hide the element : <b>"+name+"</b>", true);
			Allure.step("The system failed to  hide the element : "+name, Status.FAILED);
			BaseTest.logger.info("The system failed to hide the element :"+name);
			Allure.step("Element does not hide ", () -> Allure.attachment("Element "+name + " Screenshot ", new ByteArrayInputStream(BaseTest.page.screenshot())));
		}
	}
	
	
	
	/**
	 * select method is used to select the value from the drop down
	 * @param value - value in the drop down
	 * @param name - name of the field 
	 */
	public void select(String value, String name) {
		
	BaseTest.page.locator(sElement).selectOption(value);
	Reporting.pass("System successfully selected the item : <b>"+value+" </b> in the drop down  <b>"+name+" </b>");
	Allure.step("System successfully selected the item : "+value+" in the drop down  "+name);
	BaseTest.logger.info("System successfully selected the item : "+value+" in the drop down "+name);
	
	}
	
	
	/**
	 * This method verifies the tile of the current web page is matching with title provided as parameter and prints in the report 
	 * @param sTitle - Expected title of the page
	 * @author - ChennakesavaRao Bachu
	 */
	public void verifyTitle(String sTitle) {
		
		if(sTitle.contains(BaseTest.page.title())){
			Reporting.pass("System successfully validates the Expected title : <b>"+sTitle+"</b>");
			BaseTest.logger.info("System successfully validates the Expected title : "+sTitle);
		}else {
			Reporting.fail("System failed to match the Expected title : <b>"+sTitle+"</b> whereas Actual title : <b>"+BaseTest.page.title()+"</b>", true);
			BaseTest.logger.info("System failed to match the Expected title : "+sTitle+" whereas Actual title : "+BaseTest.page.title());
			Allure.step("Title mismatch ", () -> Allure.attachment(sTitle + " Screenshot ", new ByteArrayInputStream(BaseTest.page.screenshot())));
		}
		
	}
	
	/**
	 * This method verifies the value in the field  is matching with the parameter value and prints in the report
	 * @param value - Expected value 
	 * @param name - name of the field
	 * @author - ChennakesavaRao Bachu
	 */
	public void verifyInputText(String value, String name) {

		if(BaseTest.page.locator(sElement).inputValue().contains(value)) {
			
			Reporting.pass("Value <b>"+value+"</b> is entered properly in the field <b>"+name);
			Allure.step("Value "+value+" is entered properly in the field "+name);
//			Allure.step("value matches", () -> Allure.attachment(name + "Screenshot", new ByteArrayInputStream(BaseTest.page.screenshot())));
		}else {
			
			Reporting.info("seems to be expected value : <b>"+value+"</b> is not entered properly in the field "+name+" , Actual : <b>" + BaseTest.page.locator(sElement).inputValue()+"</b>");
			Allure.step("seems to be expected value : "+value+" is not entered properly in the field "+name+" , Actual : " + BaseTest.page.locator(sElement).inputValue()+"", Status.FAILED);
			Allure.step("value mismatch", () -> Allure.attachment(name + "Screenshot", new ByteArrayInputStream(BaseTest.page.screenshot())));
		}
	}
	
	
	

	/**
	 * this method used to get the text from the element
	 * @return - string
	 * @author - ChennakesavaRao Bachu
	 */
	public String textContent() {
		return BaseTest.page.textContent(sElement);
	}
	
	
	

	/*
	 * private void waitforLocator(String name) { //
	 * System.out.println("waiting for element "+name);
	 * BaseTest.page.locator(sElement).waitFor(); BaseTest.lib.highlight(sElement);
	 * 
	 * }
	 */
	
	/**
	 * getElement method returns the xpath of element 
	 * @return - XPath of element
	 * @author - ChennakesavaRao Bachu
	 */
	public String getElement() {
		return sElement;
	}
	
	/**
	 * isElementPresent is a method to verify the visibility of element and returns true for element presence and returns false for element absence
	 * @return - boolean true or false
	 * @author - ChennakesavaRao Bachu
	 */
	public boolean isElementPresent() {
		boolean tOrf = false;
		waitforElement(sElement);
		tOrf = BaseTest.page.isVisible(sElement);
		return tOrf;
	}
	
	
	

	/**
	 * isElementVisible method is used to wait for visibility of element for number of seconds mentioned in parameter and returns true on presence or wait to return False for absence
	 * @param sec - number of seconds to wait before return false
	 * @return boolean
	 * @author - kaja ChennnakesavaRao Bachu
	 */
	public boolean isElementVisible(int iTimeout) {
		try {
			BaseTest.page.waitForSelector(sElement, new Page.WaitForSelectorOptions().setTimeout(iTimeout*1000).setState(WaitForSelectorState.VISIBLE));
			BaseTest.logger.info("Element is visible before the timeout");
			BaseTest.lib.highlight(sElement);
			return true;
		} catch (PlaywrightException e) {
			Allure.step("Element not visible ", () -> Allure.attachment("Element Screenshot ", new ByteArrayInputStream(BaseTest.page.screenshot())));
			return false;
		}
	}

	/**
	 * isElementAbsent method is used to wait for absence of element for number of seconds mentioned in parameter and returns true on absence or wait to return False for presence
	 * @param waitForAbsenceInSeconds - number of seconds to wait before return false
	 * @return boolean
	 * @author - kaja ChennnakesavaRao Bachu
	 */
	public boolean isElementAbsent(int waitForAbsenceInSeconds) {
			
			if(BaseTest.page.locator(sElement).count() <=0) {
				BaseTest.logger.info("Element is absent in the DOM");
				return true;
			}else {
				BaseTest.logger.info("Element is present in the DOM, checking for visibility");
				Util.sleepforseconds(waitForAbsenceInSeconds);
				
				if(BaseTest.page.locator(sElement).count() <= 0)
				return true;
				else
					return false;
			}
			
	}
	
	
	/**
	 * isElementAbsent method is used to wait for absence of element for default timeout and returns true on absence or wait to return False for presence
	 * @return boolean
	 * @author - kaja ChennnakesavaRao Bachu
	 */
	public boolean isElementAbsent() {
		return isElementAbsent(3);
	}
	
	/**
	 * isElementPresent method is used to wait for presence of element for number of seconds mentioned in parameter and returns true on presence or wait to return False for absence
	 * @param waitForPresenceInSeconds - number of seconds to wait before return false
	 * @return boolean
	 * @author - kaja ChennnakesavaRao Bachu
	 */
	public boolean isElementPresent(int waitForPresenceInSeconds) {
		System.out.println("Checking for presence of element in newly created method");
		
		if(BaseTest.page.locator(sElement).count() >0) {
			BaseTest.logger.info("Element is present in the DOM");
			return true;
		}else {
			BaseTest.logger.info("Element is absent in the DOM, checking for presence");
			Util.sleepforseconds(waitForPresenceInSeconds);
			
			if(BaseTest.page.locator(sElement).count() > 0)
			return true;
			else
				return false;
		}
	}
	
	
	/**
	 * isElementVisible method is used to wait for visibility of element 
	 * @return boolean
	 * @author - kaja ChennnakesavaRao Bachu
	 */
	public boolean isElementVisible() {
		try {
			BaseTest.page.waitForSelector(sElement, new Page.WaitForSelectorOptions().setTimeout(iTimeout*1000).setState(WaitForSelectorState.VISIBLE));
			BaseTest.logger.info("Element is visible before timeout");
			BaseTest.lib.highlight(sElement);
			return true;
		} catch (PlaywrightException e) {
			Reporting.fail("Element not visible till timeout", true);
			Allure.step("Element not visible ", () -> Allure.attachment("Element Screenshot ", new ByteArrayInputStream(BaseTest.page.screenshot())));
			return false;
		}
		
//		return isElementVisible(iTimeout);
	}
	
	

	
	/**
	 * pressKey method is used to type the keys into the focused field.
	 * F1 - F12, Digit0- Digit9, KeyA- KeyZ, Back quote, Minus, Equal, Backslash, Backspace, Tab, Delete, Escape, ArrowDown, End, Enter, Home, Insert, PageDown, PageUp, ArrowRight, ArrowUp, etc.
	 * @param key 
	 * @author - kaja ChennnakesavaRao Bachu
	 * 
	 */
	public void pressKey(String key) {
		
		BaseTest.page.press(sElement, key);
		BaseTest.logger.info("Pressing the key : "+ key);
		
	}
	
	/**
	 * getAttribute method is used to fetch the attribute value from the element
	 * @param attributeName - name of the attribute
	 * @param name - name of the element
	 * @return - String attribute value
	 * @author - kaja ChennnakesavaRao Bachu
	 * 
	 */
	public String getAttribute(String attributeName, String name) {
		String attributeValue = BaseTest.page.locator(sElement).getAttribute(attributeName);
		
//		Reporting.pass("System successfully fetched the attribute : <b>"+attributeName+" </b> having value <b>"+attributeValue+" </b> from the element  <b>"+name+" </b>");
//		Allure.step("System successfully fetched the attribute : "+attributeName+" having value "+attributeValue+" from the element  "+name);
		BaseTest.logger.info("System successfully fetched the attribute : "+attributeName+" having value "+attributeValue+" from the element "+name);
		
		return attributeValue;
	}
	
	/**
	 * isChecked method is used to verify whether the checkbox is checked or not and prints in the report
	 * @param name - name of the checkbox
	 * @return - boolean true or false
	 * @author - kaja ChennnakesavaRao Bachu
	 * 
	 */
	public boolean isChecked(String name) {
		boolean isChecked = BaseTest.page.locator(sElement).isChecked();
		if(isChecked) {
			Reporting.pass("The checkbox : <b>"+name+"</b> is checked");
			Allure.step("The checkbox : "+name+" is checked");
			BaseTest.logger.info("The checkbox : "+name+" is checked");
			return isChecked;
		}else {
			Reporting.fail("The checkbox : <b>"+name+"</b> is not checked", true);
			Allure.step("The checkbox : "+name+" is not checked", Status.FAILED);
			BaseTest.logger.info("The checkbox : "+name+" is not checked");
			Allure.step("Checkbox not checked ", () -> Allure.attachment("Checkbox "+name + " Screenshot ", new ByteArrayInputStream(BaseTest.page.screenshot())));
			return isChecked;
		}
	}
	
	/**
	 * hover method is used to hover on the element. Before hovering, system wait for element, highlights the element and hovers on it
	 * @param name - name of the field
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void hover(String name) {
		waitforElement(name);
		BaseTest.page.hover(sElement);
		Reporting.pass("System successfully hovered on the element : <b>"+name+"</b>");
		Allure.step("System successfully hovered on the element : "+name);
		BaseTest.logger.info("System successfully hovered on the element : "+name);
	}
	
	/**
	 * getValue method is used to get the value from the input field.
	 * @return - String value from the input field
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public String getValue() {
		return BaseTest.page.locator(sElement).inputValue();
	}
	
	/**
	 * clear method is used to clear the value from the input field.
	 * @param name - name of the field
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void clear(String name) {
		waitforElement(name);
		BaseTest.page.fill(sElement, "");
		Reporting.pass("System successfully cleared the field : <b>"+name+"</b>");
		Allure.step("System successfully cleared the field : "+name);
		BaseTest.logger.info("System successfully cleared the field : "+name);
	}
	
	/**
	 * doubleClick method is used to double click on element. Before double clicking, system wait for element, highlights the element and double clicks on it
	 * @param name - name of the field
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void doubleClick(String name) {
		waitforElement(name);
		BaseTest.page.dblclick(sElement);
		Reporting.pass("System successfully double clicked the element : <b>"+name+"</b>");
		Allure.step("System successfully double clicked the element : "+name);
		BaseTest.logger.info("System successfully double clicked the element : "+name);
	}
	
	/**
	 * rightClick method is used to right click on element. Before right clicking, system wait for element, highlights the element and right clicks on it
	 * @param name - name of the field
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void rightClick(String name) {
		waitforElement(name);
		BaseTest.page.click(sElement, new Page.ClickOptions().setButton(com.microsoft.playwright.options.MouseButton.RIGHT));
		Reporting.pass("System successfully right clicked the element : <b>"+name+"</b>");
		Allure.step("System successfully right clicked the element : "+name);
		BaseTest.logger.info("System successfully right clicked the element : "+name);
	}
	
	/**
	 * scrollIntoViewIfNeeded method is used to scroll to the element. Before scrolling, system wait for element, highlights the element and scrolls to it
	 * @param name - name of the field
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void scrollIntoViewIfNeeded(String name) {
		waitforElement(name);
		BaseTest.page.locator(sElement).scrollIntoViewIfNeeded();
		Reporting.pass("System successfully scrolled to the element : <b>"+name+"</b>");
		Allure.step("System successfully scrolled to the element : "+name);
		BaseTest.logger.info("System successfully scrolled to the element : "+name);
}

/**
	 * focus method is used to set focus on the element. Before performing operation, system wait for element, highlights the element and performs the operation
	 * @param name - name of the field
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void focus(String name) {
		waitforElement(name);
		BaseTest.page.locator(sElement).focus();
		Reporting.pass("System successfully focused on the element : <b>"+name+"</b>");
		Allure.step("System successfully focused on the element : "+name);
		BaseTest.logger.info("System successfully focused on the element : "+name);
	}
	
	/**
	 * blur method is used to remove focus from the element. Before performing operation, system wait for element, highlights the element and performs the operation
	 * @param name - name of the field
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void blur(String name) {
		waitforElement(name);
		BaseTest.page.locator(sElement).blur();
		Reporting.pass("System successfully removed focus from the element : <b>"+name+"</b>");
		Allure.step("System successfully removed focus from the element : "+name);
		BaseTest.logger.info("System successfully removed focus from the element : "+name);
	}
	
	
	/**
	 * check method is used to check the checkbox. Before performing operation, system wait for element, highlights the element and performs the operation
	 * @param name - name of the checkbox
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void check(String name) {
		waitforElement(name);
		BaseTest.page.locator(sElement).check();
		Reporting.pass("System successfully checked the checkbox : <b>"+name+"</b>");
		Allure.step("System successfully checked the checkbox : "+name);
		BaseTest.logger.info("System successfully checked the checkbox : "+name);
	}
	
	/**
	 * uncheck method is used to uncheck the checkbox. Before performing operation, system wait for element, highlights the element and performs the operation
	 * @param name - name of the checkbox
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void uncheck(String name) {
		waitforElement(name);
		BaseTest.page.locator(sElement).uncheck();
		Reporting.pass("System successfully unchecked the checkbox : <b>"+name+"</b>");
		Allure.step("System successfully unchecked the checkbox : "+name);
		BaseTest.logger.info("System successfully unchecked the checkbox : "+name);
	}
	
	
	/**
	 * waitForElementToBeEnabled method is used to wait for the element to be enabled for default timeout
	 * @param name - name of the element
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void waitForElementToBeEnabled(String name) {
		try {
			BaseTest.page.waitForSelector(sElement, new Page.WaitForSelectorOptions().setTimeout(iTimeout*1000).setState(WaitForSelectorState.ATTACHED));
			BaseTest.logger.info("Element is enabled before timeout");
			BaseTest.lib.highlight(sElement);
		} catch (PlaywrightException e) {
			Reporting.fail("Element not enabled till timeout", true);
			Allure.step("Element not enabled ", () -> Allure.attachment("Element Screenshot ", new ByteArrayInputStream(BaseTest.page.screenshot())));
		}
	}
	
	/**
	 * setchecked method is used to check or uncheck the checkbox based on the boolean value provided as parameter. Before performing operation, system wait for element, highlights the element and performs the operation
	 * @param value - boolean value true or false
	 * @param name - name of the checkbox
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void setchecked(boolean value, String name) {
		waitforElement(name);
		if(value) {
			BaseTest.page.locator(sElement).setChecked(value);
			Reporting.pass("System successfully checked the checkbox : <b>"+name+"</b>");
			Allure.step("System successfully checked the checkbox : "+name);
			BaseTest.logger.info("System successfully checked the checkbox : "+name);
		}else {
			BaseTest.page.locator(sElement).setChecked(value);
			Reporting.pass("System successfully unchecked the checkbox : <b>"+name+"</b>");
			Allure.step("System successfully unchecked the checkbox : "+name);
			BaseTest.logger.info("System successfully unchecked the checkbox : "+name);
		}
	}
	
	/**
	 * tap method is used to tap on element. Before tapping, system wait for element, highlights the element and taps on it
	 * @param name - name of the field
	 * @author - kaja ChennnakesavaRao Bachu  
	 */
	public void tap(String name) {
		waitforElement(name);
		BaseTest.page.tap(sElement);
		Reporting.pass("System successfully tapped the element : <b>"+name+"</b>");
		Allure.step("System successfully tapped the element : "+name);
		BaseTest.logger.info("System successfully tapped the element : "+name);
	}
	
	/**
	 * clearAndFill method is used to clear existing value and enter a new value in the field. Before filling, system wait for the element, highlights the element, clears existing value, fills the new value in the field and finally verifies the value. 
	 * @param value - value to enter in the field
	 * @param name - name of the field 
	 * 
	 * @author - kaja ChennnakesavaRao Bachu
	 */
	public void clearAndFill(String value, String name) {
		waitforElement(name);
		BaseTest.page.fill(sElement, "");
		BaseTest.page.fill(sElement, value);
		BaseTest.logger.info("System successfully enters the "+value+" in the field : "+name);
		verifyInputText(value, name);
		
	}
	
	
	
	
}