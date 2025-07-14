package com.CK.Playwright.Pages;

import com.Diyar.Playwright.Elements.Elements;

public class DSLoginPage {

	public static Elements Tenant_Switch_Btn = new Elements("//a[@id='AbpTenantSwitchLink']");
	public static Elements Switch_Tenant_Name = new Elements("//input[@id='Input_Name']");
	public static Elements Switch_Tenant_Cancel = new Elements("//button[contains(text(),'Cancel')]");
	public static Elements Switch_Tenant_Save = new Elements("//button/span[contains(text(),'Save')]");
	public static Elements Switch_Tenant_Ok = new Elements("//button[@class='swal2-confirm btn btn-primary']");
	public static Elements Switch_Tenant_DoesnotExist = new Elements("//div[@id='swal2-html-container']");
	
	public static Elements Username = new Elements("//input[@id='LoginInput_UserNameOrEmailAddress']");
	public static Elements Password = new Elements("//input[@id='password-input']");
	public static Elements Login_Btn = new Elements("//button[@value='Login']");
	
	
	public static Elements Username_RequiredError = new Elements("//span[@id='LoginInput_UserNameOrEmailAddress-error']"); // The User name or email address field is required.
	public static Elements Password_RequiredError = new Elements("//span[@id='password-input-error']"); // The Password field is required.
	
	public static Elements Invalid_Username_Or_Password_Error = new Elements("//div[@id='AbpPageAlerts']/div");  // Invalid username or password!
    
	
}
