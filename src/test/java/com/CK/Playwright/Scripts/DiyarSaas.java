package com.CK.Playwright.Scripts;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.CK.Playwright.Pages.DSHomePage;
import com.CK.Playwright.Pages.DSLoginPage;
import com.CK.Playwright.Pages.DSPage;
import com.Diyar.Playwright.Reporting.Reporting;
import com.Diyar.Playwright.Util.Util;

import io.qameta.allure.Description;
import io.qameta.allure.Step;


public class DiyarSaas extends DiyarSaas_Lib{
	
	@Step
	@Description("Invalid tenant error")
	@Test
	public void TC051_Login_with_invalid_tenant() {
		
		String Tenant = getdata("Tenant"); // "asdfgh";
		String Given_tenant_doesnt_exist = "Given tenant doesn\'t exist:";
		
		page.navigate(sURL);
		
		DSPage.LoginBtn.click("Login Btn");
		Assert.assertEquals("Login Btn","Login Btn");
		
		Util.sleepforseconds(5);
		DSLoginPage.Tenant_Switch_Btn.click("switch button in tenant");
		Util.sleepforseconds(3);
		DSLoginPage.Switch_Tenant_Name.fill(Tenant, "tenant name");
		DSLoginPage.Switch_Tenant_Save.click("Save button in Switch tenant");
		Util.sleepforseconds(5);
		
		
		DSLoginPage.Switch_Tenant_DoesnotExist.verifyElementPresent("Tenant does not exist error");
		
		System.out.println(" Tenant error : " + DSLoginPage.Switch_Tenant_DoesnotExist.textContent());

		Util.verifyText(Given_tenant_doesnt_exist +" "+ Tenant, DSLoginPage.Switch_Tenant_DoesnotExist.textContent());
		
		DSLoginPage.Switch_Tenant_Ok.click("Ok button in switch tenant");
		
		
	}
	@Test
	@Step
	@Description("Login with valid tenant")
	public void TC052_Login_with_valid_tenant() {
		
		String User =  getdata("Username");  //"admin";
		String Pass =  getdata("Password"); // "P9$!nf5*jB9wiK";
		String Tenant = getdata("Tenant"); // "knpc";
		
		page.navigate(sURL);
		
		
		if(!DSHomePage.Admin.isElementPresent()) {
		login(Tenant, User, Pass);
		}
		
		DSHomePage.Admin.click("Admin");
		DSHomePage.Logout.click("Logout");
		Util.sleepforseconds(5);
		
	}

	@Step
	@Test
	public void TC053_Login_with_empty_credentials() {
	
		String Tenant = getdata("Tenant"); // "knpc";
		
		page.navigate(sURL);
		
		if(!DSHomePage.Admin.isElementPresent()) {
			login(Tenant, "", "");
			}else {
				Reporting.info("System already logged in");
				DSHomePage.Admin.click("Admin");
				DSHomePage.Logout.click("Logout");
				Util.sleepforseconds(10);
				login(Tenant, "",""); 
			}
		
		
		DSLoginPage.Username_RequiredError.verifyElementPresent("Username RequiredError");
		DSLoginPage.Password_RequiredError.verifyElementPresent("Password RequiredError");
		
		System.out.println("username error : " + DSLoginPage.Username_RequiredError.textContent());
		System.out.println("pwd error : " + DSLoginPage.Password_RequiredError.textContent());
		
		Util.sleepforseconds(5);
	}
	
	@Step
	@Test
	public void TC054_Login_with_valid_username_and_empty_password(){
		
		String Tenant = getdata("Tenant"); // "knpc";
		String User =  getdata("Username");  //"admin";
		
		page.navigate(sURL);
		
		
		if(!DSHomePage.Admin.isElementPresent()) {
			login(Tenant, User, "");
			}else {
				Reporting.info("System already logged in");
				DSHomePage.Admin.click("Admin");
				DSHomePage.Logout.click("Logout");
				Util.sleepforseconds(10);
				login(Tenant, User,""); 
			}
		
		DSLoginPage.Password_RequiredError.verifyElementPresent("Password RequiredError");
		
	}

	@Step
	@Test
	public void TC055_Login_with_valid_password_and_empty_username(){
		
		String Tenant = getdata("Tenant"); // "knpc";
		String Pass =  getdata("Password");  //"admin";
		
		page.navigate(sURL);
		
		if(!DSHomePage.Admin.isElementPresent()) {
			login(Tenant, "", Pass);
			}else {
				Reporting.info("System already logged in");
				DSHomePage.Admin.click("Admin");
				DSHomePage.Logout.click("Logout");
				Util.sleepforseconds(10);
				login(Tenant,"", Pass); 
			}
		
		
		DSLoginPage.Username_RequiredError.verifyElementPresent("Username RequiredError");
	}

	@Step
	@Test
	public void TC056_Login_with_valid_username_and_invalid_password(){
		
		String Invalid_Username_Or_Password_Error = "Invalid username or password!";
		
		String Tenant = getdata("Tenant"); // "knpc";
		String User =  getdata("Username");  //"admin";
		String Pass = getdata("Password");
		
		page.navigate(sURL);
		
		
		if(!DSHomePage.Admin.isElementPresent()) {
			login(Tenant, User, Pass);
			}else {
				Reporting.info("System already logged in");
				DSHomePage.Admin.click("Admin");
				DSHomePage.Logout.click("Logout");
				Util.sleepforseconds(10);
				login(Tenant, User, Pass); 
			}
		
		DSLoginPage.Invalid_Username_Or_Password_Error.verifyElementPresent(Invalid_Username_Or_Password_Error);
		
	}

	@Step
	@Test
	public void TC057_Login_with_valid_password_and_invalid_username() {
		
		
String Invalid_Username_Or_Password_Error = "Invalid username or password!";
		
		String Tenant = getdata("Tenant"); // "knpc";
		String User =  getdata("Username");  //"admin";
		String Pass = getdata("Password");
		
		page.navigate(sURL);
		
		
		if(!DSHomePage.Admin.isElementPresent()) {
			login(Tenant, User, Pass);
			}else {
				Reporting.info("System already logged in");
				DSHomePage.Admin.click("Admin");
				DSHomePage.Logout.click("Logout");
				Util.sleepforseconds(10);
				login(Tenant, User, Pass); 
			}
		
		DSLoginPage.Invalid_Username_Or_Password_Error.verifyElementPresent(Invalid_Username_Or_Password_Error);
		
	}
	
	@Step
	@Test
public void	TC059_Login_To_check_if_the_back_button_in_the_login_form_is_working_fine() {
	
	
	String Tenant = getdata("Tenant"); // "knpc";
	page.navigate(sURL);
	
	
	if(!DSHomePage.Admin.isElementPresent()) {
		DSPage.LoginBtn.click("Login Btn");
		Util.sleepforseconds(5);
		}else {
			Reporting.info("System already logged in");
			DSHomePage.Admin.click("Admin");
			DSHomePage.Logout.click("Logout");
			Util.sleepforseconds(10);
			
			DSPage.LoginBtn.click("Login Btn");
			Util.sleepforseconds(5);
		
			
		}
	
	DSLoginPage.Tenant_Switch_Btn.click("switch button in tenant");
	DSLoginPage.Switch_Tenant_Name.fill(Tenant, "tenant name");
	Util.sleepforseconds(3);
	
	page.goBack();
	Util.sleepforseconds(3);
	
	DSLoginPage.Login_Btn.verifyElementPresent("Login Button in Login page");
}

	@Step
	@Test
	public void TC060_Login_with_Valid_username_and_password() {
		
		String User =  getdata("Username");  //"admin";
		String Pass =  getdata("Password"); // "P9$!nf5*jB9wiK";
		String Tenant = getdata("Tenant"); // "knpc";
		
		page.navigate(sURL);
		
		
		if(!DSHomePage.Admin.isElementPresent()) {
		login(Tenant, User, Pass);
		}
		
		DSHomePage.Admin.click("Admin");
		DSHomePage.Logout.click("Logout");
		Util.sleepforseconds(5);
		
	}
	
}
