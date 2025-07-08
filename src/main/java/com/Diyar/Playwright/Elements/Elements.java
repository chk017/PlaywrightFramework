package com.Diyar.Playwright.Elements;


import org.testng.Assert;

import com.Diyar.Playwright.BaseTest.BaseTest;
import com.Diyar.Playwright.Reporting.Reporting;
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
	 * fill method is used to enter a value in the field. Before filling, system wait for the element, highlights the element, fills the value in the field and finally verifies the value. 
	 * @param value - value to enter in the field
	 * @param name - name of the field 
	 * @author chk017 - ChennakesavaRao Bachu 
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
	 * click method is used to click on element. Before clicking, system wait for element, highlights the element and clicks on it
	 * @param name - name of the field
	 * @author chk017 - ChennakesavaRao Bachu  
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
	 * This method verifies the tile of the current web page is matching with title provided as parameter and prints in the report 
	 * @param sTitle - Expected title of the page
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public void verifyTitle(String sTitle) {
		
		if(sTitle.contains(BaseTest.page.title())){
			Reporting.pass("System successfully validates the Expected title : <b>"+sTitle+"</b>");
			BaseTest.logger.info("System successfully validates the Expected title : "+sTitle);
		}else {
			Reporting.fail("System failed to match the Expected title : <b>"+sTitle+"</b> whereas Actual title : <b>"+BaseTest.page.title()+"</b>", true);
			BaseTest.logger.info("System failed to match the Expected title : "+sTitle+" whereas Actual title : "+BaseTest.page.title());
		}
		
	}
	
	/**
	 * This method verifies the value in the field  is matching with the parameter value and prints in the report
	 * @param value - Expected value 
	 * @param name - name of the field
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public void verifyInputText(String value, String name) {

		if(BaseTest.page.locator(sElement).inputValue().contains(value)) {
			
			Reporting.pass("Value <b>"+value+"</b> is entered properly in the field <b>"+name);
			Allure.step("Value "+value+" is entered properly in the field "+name);
		}else {
			
			Reporting.info("seems to be expected value : <b>"+value+"</b> is not entered properly in the field "+name+" , Actual : <b>" + BaseTest.page.locator(sElement).inputValue()+"</b>");
			Allure.step("seems to be expected value : "+value+" is not entered properly in the field "+name+" , Actual : " + BaseTest.page.locator(sElement).inputValue()+"", Status.FAILED);
		}
	}
	

	/**
	 * this method used to get the text from the element
	 * @return - string
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public String textContent() {
		return BaseTest.page.textContent(sElement);
	}
	
	
	private void waitforElement(String name) {
//		System.out.println("waiting for element "+name);
		try {
			BaseTest.logger.info("waiting for element : "+name);
			BaseTest.page.waitForSelector(sElement);
			BaseTest.lib.highlight(sElement);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public String getElement() {
		return sElement;
	}
	
	/**
	 * isElementPresent is a method to verify the visibility of element and returns true for element presence and returns false for element absence
	 * @return - boolean true or false
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public boolean isElementPresent() {
		boolean tOrf = false;
		waitforElement(sElement);
		tOrf = BaseTest.page.isVisible(sElement);
		return tOrf;
	}
	
	/**
	 * verifyElementPresent is a method to verify the visibility of element and prints in the reporting
	 * @param name - It is just name of the element 
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public void verifyElementPresent(String name) {
		/*if(isElementPresent()) {*/
		if(isElementVisible()) {
			Reporting.pass("System successfully displays the Element: <b>"+name+"</b>");
			Allure.step("System successfully displays the Element: "+name);
			BaseTest.logger.info("System successfully displays the Element: "+name);
		}else {
			Reporting.fail("System failed to display the Element: <b>"+name+"</b>", true);
			Allure.step("System failed to display the Element: "+name, Status.FAILED);
			BaseTest.logger.info("System failed to display the Element: "+name);
		}
	}
	

	/**
	 * isElementVisible method is used to wait for visibility of element for number of seconds mentioned in parameter and returns true on presence or wait to return False for absence
	 * @param sec - number of seconds to wait before return false
	 * @return boolean
	 * @author ChennakesavaRao Bachu
	 */
	public boolean isElementVisible(int iTimeout) {
		try {
			BaseTest.page.waitForSelector(sElement, new Page.WaitForSelectorOptions().setTimeout(iTimeout*1000).setState(WaitForSelectorState.VISIBLE));
			BaseTest.logger.info("Element is visible before the timeout");
			BaseTest.lib.highlight(sElement);
			return true;
		} catch (PlaywrightException e) {
			return false;
		}
	}

	
	/**
	 * isElementVisible method is used to wait for visibility of element 
	 * @param sec - number of seconds to wait to return false
	 * @return boolean
	 * @author ChennakesavaRao Bachu
	 */
	public boolean isElementVisible() {
		try {
			BaseTest.page.waitForSelector(sElement, new Page.WaitForSelectorOptions().setTimeout(iTimeout*1000).setState(WaitForSelectorState.VISIBLE));
			BaseTest.logger.info("Element is visible before timeout");
			BaseTest.lib.highlight(sElement);
			return true;
		} catch (PlaywrightException e) {
			return false;
		}
		
//		return isElementVisible(iTimeout);
	}
	
	/**
	 * pressKey method is used to type the keys into the focused field.
	 * F1 - F12, Digit0- Digit9, KeyA- KeyZ, Backquote, Minus, Equal, Backslash, Backspace, Tab, Delete, Escape, ArrowDown, End, Enter, Home, Insert, PageDown, PageUp, ArrowRight, ArrowUp, etc.
	 * @param key 
	 * 
	 */
	public void pressKey(String key) {
		
		BaseTest.page.press(sElement, key);
		BaseTest.logger.info("Pressing the key : "+ key);
		
	}
}


