package com.niveus.afya.testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Base.TestBase;
import com.pages.AlertPage;
import com.pages.ChooseRolePage;
import com.pages.LoginPage;
import com.pages.SubmitterHomePage;
import com.pages.SwitchRolePage;

public class SwitchRoleTest extends TestBase {

	LoginPage loginPage;
	AlertPage alert;
	ChooseRolePage chooseRole;
	SubmitterHomePage homePage;
	SwitchRolePage switchRole;

	public SwitchRoleTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		alert = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		chooseRole = alert.acceptAlert();
		homePage = chooseRole.clickOnRole(prop.getProperty("subrole"));
		switchRole = homePage.getSwitchRoleObj();
	}

	@Test(priority = 1)
	public void switcgRoleIsEnabledTest() {
		String roleText = switchRole.getSwitchRoleText();
		Assert.assertEquals(roleText, "Switch Roles", "Switch role text nt found...!!!!!....");
	}

	@Test(priority = 2)
	public void clickOnSwitchRoleTest() {
		switchRole.performClickAction("Switch Roles");
	}

	@Test(priority = 3)
	public void isRolesDisplayedTest() {
		switchRole.performClickAction("Switch Roles");

		String rolesName = switchRole.roleIsDisplayed("Responder Provider", "Submitter Provider");
		String[] roleSet = rolesName.split("provider");
		for (int i = 0; i <= roleSet.length - 1; i++) {
			boolean responder = roleSet[i].contains("Responder Provide");
			Assert.assertTrue(responder, "Responder role is not found...!!!..");

			boolean submitter = roleSet[i].contains("Submitter Provider");
			Assert.assertTrue(submitter, "Submitter Provider role is not found...!!!..");
		}
	}

	@Test(priority = 4)
	public void clickOnRolesTest() {
		switchRole.performClickAction("Switch Roles");

		switchRole.performClickAction("Responder Provider");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
