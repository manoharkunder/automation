package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.TestBase;

public class SubmitterHomePage extends TestBase {

	@FindBy(xpath = "//div[contains(@class,'addnew')]")
	WebElement addpatientbtn;

	@FindBy(xpath = "//span[text()='Unresponded']")
	WebElement unresponderFilter;

	@FindBy(xpath = "//span[text()='All Chats']")
	WebElement allChatFilter;

	@FindBy(css = "span.ant-input-affix-wrapper")
	 WebElement search;

	@FindBy(xpath = "//div[@role='filter_holder']//img[contains(@src,'data:image/png;')]")
	 WebElement filterbtn;

	@FindBy(xpath = "//div[@role='filter_holder']//img[contains(@src,'/static/sort')]")
	WebElement sortbtn;

	@FindBy(xpath = "//a[@href='/providerroles']")
	WebElement switchrolelink;

	@FindBy(xpath = "//a[@href='/chatcenter']")
	WebElement chatLink;

	@FindBy(xpath = "//a[@href='/profile']")
	WebElement profileLink;

	@FindBy(id = "logo")
	WebElement afyaLogo;
	@FindBy(xpath = "//img[contains(@src,'data:image/webp')]")
	WebElement missedcallIcon;

	@FindBy(xpath = "//div[contains(@class,'user_name')]")
	WebElement sidemenuprofile;
	
	@FindBy(xpath = "//span[@role='active']")
	WebElement activeFilter;

	// Initializing the Page Objects:
	public SubmitterHomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public boolean isSubmitterPageIsDisplayed() {
		return addpatientbtn.isEnabled();
	}

	public boolean unrespondedFilterIsUnable() {
		return unresponderFilter.isEnabled();
	}

	public boolean filterSortBtnIsEnabled(String btnName) {
		if (btnName == "Filter")

			return filterbtn.isEnabled();

		else
			return sortbtn.isEnabled();

	}

	public boolean isSearchIsEnabled() {
		return search.isEnabled();
	}

	public String getHeaderText(String heraderName) {
		if (heraderName.equalsIgnoreCase("Switch Roles")) {
			return switchrolelink.getText();
		} else if (heraderName.equalsIgnoreCase("Chats")) {
			return chatLink.getText();
		} else {
			return profileLink.getText();
		}
	}

	public boolean isAfyaLogoIsDisplayed() {
		return afyaLogo.isDisplayed();
	}

	public String userNameIsDisplayed() {
		return sidemenuprofile.getText();

	}

	public FilterPage clickOnFilterSort(String actionName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		if (actionName.contains("filter")) {
			// filterbtn.click();
			js.executeScript("arguments[0].click();", filterbtn);
			return new FilterPage();
		}

		else {
			//sortbtn.click();
			js.executeScript("arguments[0].click();", sortbtn);

			return new FilterPage();
		}

	}
	
	public String isChatPageInUnrespondedFilter()
	
	{
		String filterText=activeFilter.getText();
		return filterText;
	}
	
	public SwitchRolePage getSwitchRoleObj()
	{
		return new SwitchRolePage();
	}
}
