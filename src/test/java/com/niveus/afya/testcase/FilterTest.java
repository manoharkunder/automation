	package com.niveus.afya.testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Base.TestBase;
import com.pages.AlertPage;
import com.pages.ChooseRolePage;
import com.pages.FilterPage;
import com.pages.LoginPage;
import com.pages.SubmitterHomePage;

public class FilterTest extends TestBase {

	LoginPage loginPage;
	AlertPage alert;
	ChooseRolePage chooseRole;
	SubmitterHomePage homePage;
	FilterPage filter;

	public FilterTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		alert = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		chooseRole = alert.acceptAlert();
		homePage = chooseRole.clickOnRole(prop.getProperty("subrole"));
		try {
			filter = homePage.clickOnFilterSort("filter");
		} catch (Exception e) {
		}
	}

	@Test(priority = 1)
	public void allFilterTest() {
		String[] filterName = { "unread", "all", "closed", "live", "unresponded" };
		for (int i = 0; i <= filterName.length - 1; i++) {
			Assert.assertTrue(filter.isFilterEnabled(filterName[i]),
					"" + filterName[i] + " chat filter is not enabled...!!!..");

		}
	}

	@Test(priority = 2)
	public void selectFilter() {

		String[] filterBtn = { "All Chats", "Unread Chats", "Closed", "Live Chats", "Unresponded" };

		for (int i = 0; i <= filterBtn.length - 1; i++) {
			String allChatText = filter.isFilterActive(filterBtn[i]);
			Assert.assertEquals(allChatText, filterBtn[i], "" + filterBtn[i] + " filter is not clicked...!!!..");
			homePage.clickOnFilterSort("filter");

		}
	}

	@Test(priority = 3)
	public void closeFilterTest() {
		filter.closeFilter();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
