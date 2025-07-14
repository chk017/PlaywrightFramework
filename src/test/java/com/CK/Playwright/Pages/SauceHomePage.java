package com.CK.Playwright.Pages;

import com.Diyar.Playwright.Elements.Elements;

public class SauceHomePage {

	public static Elements ProductsLabel = new Elements("//span[@class='title'][contains(text(),'Products')]");
	public static Elements BurgerMenu = new Elements("//button[@id='react-burger-menu-btn']");
	
	
	public static Elements AllItems = new Elements("//a[@id='inventory_sidebar_link']");
	public static Elements About = new Elements("//a[@id='about_sidebar_link']");
	public static Elements Logout = new Elements("//a[@id='logout_sidebar_link']");
	public static Elements ResetAppState = new Elements("//a[@id='reset_sidebar_link']");
	public static Elements Close_BurgerMenu = new Elements("//button[@id='react-burger-cross-btn']");
	public static Elements Num_of_AddToCart_Btns = new Elements("//button[starts-with(@id,'add-to-cart-sauce-labs')]");
	
	public static Elements sort = new Elements("//select[@class='product_sort_container']");
	
}
