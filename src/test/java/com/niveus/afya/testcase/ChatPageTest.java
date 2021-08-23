	package com.niveus.afya.testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Base.TestBase;
import com.pages.AlertPage;
import com.pages.ChatPage;
import com.pages.ChooseRolePage;
import com.pages.FilterPage;
import com.pages.LoginPage;
import com.pages.SubmitterHomePage;

public class ChatPageTest extends TestBase {

	LoginPage loginPage;
	AlertPage alert;
	ChooseRolePage chooseRole;
	SubmitterHomePage homePage;
	FilterPage filter;
	ChatPage chat;	

	public ChatPageTest() {
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
		chat = filter.chatPageObj();

	}

	@Test(priority = 1)
	public void clikcOnChatSessionTest() {
		chat.clickOnChat();
	}

	@Test(priority = 2)
	public void chatHeaderVisibiltyTest() {
		chat.clickOnChat();

		String chatOwnerName = chat.chatHeaderIsDisplayed("OwnerName");
		Assert.assertFalse(chatOwnerName.isBlank(), "chat header not found");

		String patientBanner = chat.chatHeaderIsDisplayed("patientBanner");
		boolean flag = patientBanner.contains("RE:");
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void chatAreaElementTest() {
		chat.clickOnChat();

		Assert.assertTrue(chat.elementIsEnabled("video call"), "video call icon is not found in chat area...!!..");
		Assert.assertTrue(chat.elementIsEnabled("attachment"), "attachment icon is not found in chat area...!!..");
		Assert.assertTrue(chat.elementIsEnabled("codes"), "@codes icon is not found in chat area...!!...");
		Assert.assertTrue(chat.elementIsEnabled("text area"), "text area is  not foudn in chat area...!!!..");
		Assert.assertTrue(chat.elementIsEnabled("sendBtn"), "sendBtn is not found in the chat area...!!.. ");
	}
	@Test(priority = 4)
	public void mediaFilesSectionTest() {
		String[] media = chat.mediaOptionIsVisible().split("\n");
		for (int i = 0; i <= media.length - 1; i++) {
			if (media[i].equalsIgnoreCase("Upload files"))

				Assert.assertTrue(media[i].equalsIgnoreCase("Upload files"), "Upload file options not found...!!!..");

			else if (media[i].equalsIgnoreCase("Record Audio"))

				Assert.assertTrue(media[i].equalsIgnoreCase("Record Audio"),
						"Record Audio not found in the media section..!!!");

			else

				Assert.assertTrue(media[i].equalsIgnoreCase("Take Picture"),
						"Take Picture option not found in the media section..!!!.");
		}
	}

	@Test(priority = 5)
	public void atCodesVisibilityTest()	 {
		
		chat.atCodesListIsVisible();
	}

	@Test(priority = 6)
	public void videoMemberListTest() {
		
		 chat.videoMemberVisiblility();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
