package com.niveus.afya.testcase;


import java.io.File;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Base.TestBase;
import com.pages.AlertPage;
import com.pages.ChooseRolePage;
import com.pages.LoginPage;
import com.pages.MyProfilePage;
import com.pages.SubmitterHomePage;
import com.pages.SwitchRolePage;

public class ProfileTest extends TestBase {

	LoginPage loginPage;
	AlertPage alert;
	ChooseRolePage chooseRole;
	SubmitterHomePage homePage;
	SwitchRolePage switchRole;
	MyProfilePage profile;

	public ProfileTest() {
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
		profile = switchRole.getProfileObj();
	}

	@Test(priority = 1)
	public void myProfileVisibilityTest() {
		Assert.assertTrue(profile.myProfileLinkIsEnabled(), "My Profile link is not found....!!!..");
	}	

	@Test(priority = 2)
	public void clikcOnProfileTest() {	
		profile.clickOnWebElement("My Profile");

	}	

	@Test(priority = 3)
	public void profileHeaderTest() {
		String headerText = profile.ProfileHeaderIsDisplayed();
		boolean flag;

		String[] headerValue = headerText.split("split");
		for (int i = 0; i <= headerValue.length - 1; i++) {
			if (headerValue[i].equals("Personal Details")) {
				flag = headerValue[i].equals("Personal Details");
				Assert.assertTrue(flag);
			} else
				flag = headerValue[i].contains("Contact Details");
			Assert.assertTrue(flag);

		}

	}

	@Test(priority = 4)
	public void LoggedInUserNameTest() {
		profile.ProfileHeaderIsDisplayed();
		String UserName = profile.getLogedInUserName();
		boolean name = prop.getProperty("uname").equalsIgnoreCase(UserName);
		Assert.assertTrue(name,"user name is not matching....!!!!...");
	}

	@Test(priority = 5)
	public void userRoleInProfileTest() {
		profile.ProfileHeaderIsDisplayed();

		String role = profile.getUserRoleInProfile();

		Assert.assertEquals(role, prop.getProperty("subrole"), "role not found in profile...!!!..");
	}

	@Test(priority = 6)
	public void updateUserInformationTest() {
		profile.ProfileHeaderIsDisplayed();

		String confirmInfo = profile.updateUserProfile("prasanna", "achar", "female",
				"very good doctor in the dist and he can handle multiple problems and he will give proper solution");
		Assert.assertEquals(confirmInfo, "information successfully Updated");
	}

	@Test(priority = 7)
	public void uploadProfileImageTest() {
		File f = new File("Report");

		File fs = new File(f, "ReportPic.Png");
		
		profile.ProfileHeaderIsDisplayed();
		String sucessMsg = profile.profileImageUpdate(fs.getAbsolutePath());
		Assert.assertEquals(sucessMsg, "your profile image has been updated successfully");
	}

	@Test(priority = 8)
	public void userProfileTest() {
		String userName = profile.userProfile();
		boolean Uname = userName.equalsIgnoreCase(prop.getProperty("uname"));
		Assert.assertTrue(Uname,"user name is not matching...!!!...");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
