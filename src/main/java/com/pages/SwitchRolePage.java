package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.TestBase;

public class SwitchRolePage extends TestBase {

	@FindBy(linkText = "Switch Roles")
	WebElement switchRole;

	@FindBy(xpath = "//span[text()='Responder Provider']/parent::div")
	WebElement responderole;

	@FindBy(xpath = "//span[text()='Submitter Provider']/parent::div")
	WebElement submitterrole;

	@FindBy(xpath = "//span[@role='earning_dollar']")
	WebElement dollarImage;

	public SwitchRolePage() {
		PageFactory.initElements(driver, this);
	}

	public String getSwitchRoleText() {
		return switchRole.getText();
	}

	public void performClickAction(String elementName) {
		if (elementName.contains("Switch Roles")) {
			switchRole.click();
		} else if (elementName.contains("Submitter")) {
			submitterrole.click();
		} else {
			responderole.click();
		}

	}

	public String roleIsDisplayed(String roleName1, String roleName2) {
		return responderole.getText() + submitterrole.getText();
	}
	
	public MyProfilePage getProfileObj()
	{
		return new MyProfilePage();
	}
}
