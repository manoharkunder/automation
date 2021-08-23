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
import com.pages.SortPage;
import com.pages.SubmitterHomePage;

public class SortTest extends TestBase {
	
	
	LoginPage loginPage;
	AlertPage alert;
	ChooseRolePage chooseRole;
	SubmitterHomePage homePage;
	FilterPage filter;
	
	SortPage sortPage;

	public SortTest() 
	{
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
		
		filter.isFilterActive("All Chats");
		sortPage=filter.retrnClassObject();
	}
	
	@Test
	public void sortSectionIsEnabledTest()
	{
		homePage.clickOnFilterSort("sort");
		Assert.assertTrue(sortPage.sortOptionsIsEnable("New"),"Sort option is not visible...!!!");
		Assert.assertTrue(sortPage.sortOptionsIsEnable("Oldest"),"Sort option is not visible...!!!");

	}
	@Test
	public void getSortOptionsTest()
	{
		homePage.clickOnFilterSort("sort");

		String newsort = sortPage.isSortVisible("New");
		Assert.assertEquals(newsort, "Newest",""+newsort+" option not found in sort...!!!...");
		String oldsort = sortPage.isSortVisible("Oldest");
		Assert.assertEquals(oldsort, "Oldest",""+oldsort+" option not found in sort...!!!...");
	}
	
	@Test
	public void verifySortTest()
	{
		String[] timeStamp = sortPage.clickonSort();
		for(int i=0;i<=timeStamp.length-1;i++)
		{
			Assert.assertEquals(timeStamp[0], timeStamp[1],"Sort Test fails...!!!!....");
		}
	}
	
	@Test
	public void closeSortTest()
	{
		sortPage.closeSort();
	}
	
	


	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
