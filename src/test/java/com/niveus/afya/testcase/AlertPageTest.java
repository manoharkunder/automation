package com.niveus.afya.testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Base.TestBase;
import com.pages.AlertPage;
import com.pages.LoginPage;

public class AlertPageTest extends TestBase {
	 AlertPage alert;
	 LoginPage loginPage;
	 
	public AlertPageTest()
	 {
		 super();
	 }
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		alert=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test(priority = 1)
	public void alertImageTest() 
	{
		Assert.assertTrue(alert.isWarnIconIsEnable(),"alert image not found...!!!...");
	
	}
	@Test(priority = 2)
	public void verifyAlertTest()
	{
		boolean flag=alert.titleOfAlertPopup().contains("another device with this account.");
		Assert.assertTrue(flag,"alert text is not found");
	}
	
	@Test(priority =3 )
	public void clickOnAlert()
	{
		alert.acceptAlert();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	 
}
