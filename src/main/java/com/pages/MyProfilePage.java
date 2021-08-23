package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.TestBase;

public class MyProfilePage extends TestBase {

	@FindBy(linkText = "My Profile")
	WebElement profile;

	@FindBy(xpath = "//span[contains(@class,'profileTitle')]")
	WebElement userName;

	@FindBy(xpath = "//input[@type='file']")
	WebElement uploadFile;

	@FindBy(xpath = "//div[contains(@id,'rc-tabs')]//span[text()='Personal Details']")
	WebElement personalDetails;

	@FindBy(xpath = "//div[contains(@id,'rc-tabs')]//span[text()='Contact Details']")
	WebElement contactDetails;

	@FindBy(id = "first_name")
	WebElement fname;

	@FindBy(id = "last_name")
	WebElement lname;

	@FindBy(id = "gender")
	WebElement gender;

	@FindBy(xpath = "//div[contains(@class,'female_hover___')]")
	WebElement femaleGender;

	@FindBy(xpath = "//div[contains(@class,'male_hover')]")
	WebElement maleGender;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement updateInformation;

	@FindBy(id = "email")
	WebElement emailID;

	@FindBy(css = "div.react-tel-input")
	WebElement phoneNumber;

	@FindBy(id = "bio")
	WebElement bioData;

	@FindBy(xpath = "//span[text()='information successfully Updated']")
	WebElement confirmationMsg;

	@FindBy(xpath = "//span[text()='Submitter Provider']")
	WebElement userRole;

	@FindBy(xpath = "//span[text()='information successfully Updated']")
	WebElement informationMessage;
	
	
	@FindBy(xpath = "//span[text()='your profile image has been updated successfully']")
	WebElement profileSucess;
	
	@FindBy(xpath = "//span[contains(@class,'name___')]")
	WebElement sidemenuprofile;
	
	@FindBy(xpath = "//div[@role='user_header']")
	WebElement userHeader;
	
	WebDriverWait wait = new WebDriverWait(driver, 20);

	public MyProfilePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean myProfileLinkIsEnabled() {
		return profile.isEnabled();
	}

	public String getLogedInUserName() {
		return userName.getText();
	}

	public String getUserRoleInProfile() {
		return userRole.getText();
	}

	public String ProfileHeaderIsDisplayed() {

		// profile.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", profile);

		return personalDetails.getText() + "split" + contactDetails.getText();
	}

	public void clickOnWebElement(String nameOfElement) {
		if (nameOfElement.contains("My Profile"))
			profile.click();
		else
			try {
				wait.until(ExpectedConditions.elementToBeClickable(updateInformation));
				updateInformation.click();
			} catch (Exception e) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", updateInformation);

			}
	}

	public String updateUserProfile(String fName, String lName, String gen, String bio) {
		String delete = clearText();
		fname.sendKeys(delete + fName);
		lname.sendKeys(delete + lName);
		if (maleGender.isSelected() == false) {
			if (gen.equals("male")) {
				try {
					maleGender.click();

				} catch (Exception e) {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", maleGender);
				}
			} else {
				try {
					femaleGender.click();

				} catch (Exception e) {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", femaleGender);
				}
			}

			contactDetails.click();

			bioData.sendKeys(delete + bio);

			clickOnWebElement("UpdateInformation");
		}
		wait.until(ExpectedConditions.visibilityOf(informationMessage));
		return informationMessage.getText();
	}

	public String clearText() {
		return Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

	}

	public String  profileImageUpdate(String path) {
		uploadFile.sendKeys(path);
		wait.until(ExpectedConditions.visibilityOf(profileSucess));
		return profileSucess.getText();
	}
	
	public String userProfile()
	{
		sidemenuprofile.click();
		wait.until(ExpectedConditions.visibilityOf(userHeader));
		return userHeader.getText();
		
	}
}
