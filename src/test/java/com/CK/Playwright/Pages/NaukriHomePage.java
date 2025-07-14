package com.CK.Playwright.Pages;

import com.Diyar.Playwright.Elements.Elements;

public class NaukriHomePage {
	
	
//	static String search = "//input[@placeholder='Enter skills / designations / companies']";
	public static String head1 = "//h1[@class='qsb-title']";
	public static String experience = "//input[@id='expereinceDD']";
//	static String location = "//input[@placeholder='Enter location']";
	public static String searchBtn = "//div[@class='qsbSubmit']";
	public static String dropdown = "//span[@class='dropArrowDD']";
	public static String years2 = "//li/div/span[text()='2 years']";
	
	public static Elements location = new Elements("//input[@placeholder='Enter location']");
	public static Elements search = new Elements("//input[@placeholder='Enter skills / designations / companies']");

}
