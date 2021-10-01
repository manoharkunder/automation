package com.niveus.afya.testcase;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pages.AlertPage;
import com.pages.LoginPage;
import com.util.TestUtil;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	AlertPage alertPage;
	ExtentReports extent;
	ExtentTest logger;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		Reporter.log("loginPageTitleTest method is running", true);
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, prop.getProperty("title"));
	}

	@Test(priority = 2)
	public void loginPagelabelTest() {
		Reporter.log("loginPagelabelTest method is running", true);

		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}

	@DataProvider
	public Object[][] getLoginTestData() {
		// Object[][] data = TestUtil.getTestData("logindata");
		return TestUtil.getTestData("logindata");
		// return data;
	}

	@Test(priority = -1, dataProvider = "getLoginTestData", enabled = false)
	public void loginTest(String username, String password) {
		Reporter.log("loginTest method is running", true);

		// alertPage = loginPage.login(prop.getProperty("username"),
		// prop.getProperty("password"));
		alertPage = loginPage.login(username, password);

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
