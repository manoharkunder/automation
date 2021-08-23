package com.niveus.afya.testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Base.TestBase;
import com.pages.AlertPage;
import com.pages.ChooseRolePage;
import com.pages.LoginPage;

public class ChooseRoleTest extends TestBase{
	
	LoginPage loginPage;
	AlertPage alert;
	ChooseRolePage chooseRole;
	
	public ChooseRoleTest()
	{
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		alert=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		chooseRole=alert.acceptAlert();
	}
	
	@Test
	public void verifyChooseRoleLabel()
	{
		String lable = chooseRole.chooseRoleText(prop.getProperty("role"));
		
		Assert.assertEquals(lable, prop.getProperty("role"),"choose role  lable  is not found ..!!.");

	}
	@Test
	public void submittrRoleIsEnable()
	{
		Assert.assertTrue(chooseRole.isRoleDisplayed(prop.getProperty("subrole")),"submitter role not found ..!!");
	}
	
	@Test
	public void responderRoleIsEnabled()
	{
		Assert.assertTrue(chooseRole.isRoleDisplayed(prop.getProperty("resrole")),"responder   role not found ..!!");
	}
	
	@Test
	public void clickOnRole()
	{
		chooseRole.clickOnRole(prop.getProperty("subrole"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
