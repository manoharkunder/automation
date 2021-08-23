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

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	AlertPage alert;
	ChooseRolePage chooseRole;
	SubmitterHomePage homePage;

	public HomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		alert=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		chooseRole=alert.acceptAlert();
		homePage=chooseRole.clickOnRole(prop.getProperty("subrole"));
	}
	
	@Test
	public void verifySubmitterPageTest()
	{
		Assert.assertTrue(homePage.isSubmitterPageIsDisplayed(),"Submitter landing Page is not found....!!!...");
	}
	@Test
	public void chatFilterTest()
	{
		Assert.assertTrue(homePage.unrespondedFilterIsUnable(),"Unresponded filter is not displayed in the lnadingPage..!!!..");
	}
	@Test
	public void sortFilterBtnTest()
	{
		Assert.assertTrue(homePage.filterSortBtnIsEnabled("Filter"),"Filter button not found....!!!!..");
		Assert.assertTrue(homePage.filterSortBtnIsEnabled("Sort"),"Sort button not found....!!!!..");
	}
	@Test
	public void searchIsEnabled()
	{
		Assert.assertTrue(homePage.isSearchIsEnabled(),"Search Bar not found in he landing Page");
	}
	
	@Test
	public void getHeaderTest()
	{
		String role=homePage.getHeaderText("Switch Roles");
		Assert.assertEquals(role, prop.getProperty("switch"),"switch role headder is not found...!!!");
		
		String chat=homePage.getHeaderText("Chats");
		Assert.assertEquals(chat, prop.getProperty("chat"),"Chats headder is not found...!!!");
		
		
		String profile=homePage.getHeaderText("profile");
		Assert.assertEquals(profile, prop.getProperty("profile"),"MyProfile headder is not found...!!!");
		
	}
	@Test
	public void loginUSerNameTest()
	{
		String username=homePage.userNameIsDisplayed();
		boolean name = prop.getProperty("uname").equalsIgnoreCase(username);
		Assert.assertTrue(name,"user name is not matching....!!!!...");
	}
	@Test
	public void verifyAfyaLogoTest()
	{
		Assert.assertTrue(homePage.isAfyaLogoIsDisplayed(),"Afya logo is not found. in the landing page..!!!..");
	}
	
	public void clickAction()
	{
		homePage.clickOnFilterSort("filter");
	}
	
	@Test
	public void userLandingpageFilterTest()
	{
		String filterName=homePage.isChatPageInUnrespondedFilter();
		Assert.assertEquals(filterName, "Unresponded","Landing Page filter is not unresponed.....!!!!!!");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
