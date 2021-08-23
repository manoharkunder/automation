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
import com.pages.SearchPage;
import com.pages.SortPage;
import com.pages.SubmitterHomePage;

public class SearchTest extends TestBase {

	LoginPage loginPage;
	AlertPage alert;
	ChooseRolePage chooseRole;
	SubmitterHomePage homePage;
	FilterPage filter;
	SortPage sortPage;
	SearchPage search;

	public SearchTest() {
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
			filter.isFilterActive("All Chats");
			sortPage = filter.retrnClassObject();
		} catch (Exception e) {
		}
		search = sortPage.getSearchPageObj();
	}

	@Test
	public void clearSearchEnabledTest() {
		search.doSearch("prasanna");
		Assert.assertTrue(search.clearBtnEnabled(), "Clear search button is not found...!!!..");
	}

	@Test
	public void doSearchTest() {
		String searchResText = search.doSearch("prasanna");
		boolean res = searchResText.contains("RE: Prasanna Consumer");
		Assert.assertTrue(res, "Search data not found....!!!....");
	}

	@Test
	public void clearSearchData() {
		String searchResText = search.doSearch("prasanna");
		boolean res = searchResText.contains("RE: Prasanna Consumer");
		Assert.assertTrue(res, "Search data not found....!!!....");

		search.ClearSearch();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
