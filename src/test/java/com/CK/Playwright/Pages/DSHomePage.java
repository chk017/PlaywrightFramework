package com.CK.Playwright.Pages;

import com.Diyar.Playwright.Elements.Elements;

public class DSHomePage {

	public static Elements Admin = new Elements("//span[@class='lpx-menu-item-text' and contains(text(),'admin')]");
	public static Elements Logout = new Elements("//span[contains(text(),'Log out')]");
	
	
	
}
