package com.CK.Playwright.Scripts;

import com.CK.Playwright.Pages.DSHomePage;
import com.CK.Playwright.Pages.DSLoginPage;
import com.CK.Playwright.Pages.DSPage;
import com.Diyar.Playwright.BaseTest.BaseTest;
import com.Diyar.Playwright.Reporting.Reporting;
import com.Diyar.Playwright.Util.Util;

public class DiyarSaas_Lib extends BaseTest{

	/**
	 * Login method with 3 parameters Tenant, Username, Password
	 * @param Tenant
	 * @param User
	 * @param Pass
	 */
	public void login(String Tenant, String User, String Pass) {
		DSPage.LoginBtn.click("Login Btn");
		
		Util.sleepforseconds(5);
		DSLoginPage.Tenant_Switch_Btn.click("switch button in tenant");
		DSLoginPage.Switch_Tenant_Name.fill(Tenant, "tenant name");
		DSLoginPage.Switch_Tenant_Save.click("Save button in Switch tenant");
		Util.sleepforseconds(5);
		
		DSLoginPage.Username.fill(User, "Username");
		DSLoginPage.Password.fill(Pass, "Password");
		DSLoginPage.Login_Btn.click("Login Button");
		Util.sleepforseconds(5);
		if(DSHomePage.Admin.isElementPresent()) {
			Reporting.pass("System successfully logged in");
		}
}


}