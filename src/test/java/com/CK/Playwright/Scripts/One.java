package com.CK.Playwright.Scripts;


import java.util.List;

import org.testng.annotations.Test;

import com.Diyar.Playwright.BaseTest.BaseTest;
import com.CK.Playwright.Pages.NaukriHomePage;
import com.CK.Playwright.Pages.SauceHomePage;
import com.CK.Playwright.Pages.SauceLoginPage;
import com.Diyar.Playwright.Reporting.Reporting;
import com.Diyar.Playwright.Util.Util;
import com.microsoft.playwright.ElementHandle;

public class One extends BaseTest{
	
	
	
	@Test
	public void sauce_Login() {
		page.navigate(sURL);
		
		String User = "standard_user";
		String Pass = "secret_sauce";
		
		SauceLoginPage.Username.fill(User, "Username");
		
		SauceLoginPage.Username.verifyTitle("one");
		
		
//		lib.highlight(SaucePage.Username.getElement());
		SauceLoginPage.password.fill(Pass, "Password");
		System.out.println("login context: "+SauceLoginPage.LoginBtn.textContent());
		
		Reporting.fail("test screenshot", true);
		
		System.out.println("title check : " + page.title());
		
//		SaucePage.LoginBtn.click("Login Btn");
		lib.click(SauceLoginPage.LoginBtn.getElement());
		Util.verifyText("abc", "abc");
//		page.click(SaucePage.LoginBtn.getElement());
		
		System.out.println("ProductsLabel text content : " + SauceHomePage.ProductsLabel.textContent());
		
		
		page.locator(SauceHomePage.sort.getElement()).selectOption("Price (high to low)");
		
		
		
		
		SauceHomePage.ProductsLabel.verifyElementPresent("Products heading");
		SauceHomePage.BurgerMenu.click("Burger Menu");
		SauceHomePage.AllItems.verifyElementPresent("AllItems");
		SauceHomePage.About.verifyElementPresent("About");
		SauceHomePage.Logout.verifyElementPresent("Logout");
		SauceHomePage.ResetAppState.verifyElementPresent("ResetAppState");
		
//		page.querySelector
//		page.locator("").waitFor(); 
		
		// Get all elements matching the XPath expression
//        List<ElementHandle> elements = page.querySelectorAll("xpath=//*[starts-with(@id, 'example')]");
		List<ElementHandle> elements = page.querySelectorAll(SauceHomePage.Num_of_AddToCart_Btns.getElement());
		System.out.println("Num of Add to Cart buttons : " +elements.size() );
        
	
	}
	



//	@Test
	public void registration_TS02() {
	
	page.navigate(sURL);
	
	
	// Do something with the page 
	System.out.println("Page title: " + page.title());
	
System.out.println("heading 2 : "+ page.locator(NaukriHomePage.head1).textContent());

	NaukriHomePage.search.fill("playwright", "Search");
	NaukriHomePage.location.fill("Hyderabad","location");
	page.click(NaukriHomePage.dropdown);
	page.click(NaukriHomePage.years2);
	page.click(NaukriHomePage.searchBtn);
//	page.selectOption(experience, "2 years");
	System.out.println("title later : " + page.title());
	
//	byte[] a = page.screenshot();
	
	
	
	
}
	
	
}
