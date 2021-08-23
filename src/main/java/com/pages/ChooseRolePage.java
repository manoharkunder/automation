package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.TestBase;

public class ChooseRolePage extends TestBase {

	@FindBy(xpath = "//span[text()='Choose Role']")
	WebElement role;

	@FindBy(xpath = "//span[text()='Responder Provider']/parent::div")
	WebElement responderole;

	@FindBy(xpath = "//span[text()='Submitter Provider']/parent::div")
	WebElement submitterrole;

	public ChooseRolePage() {
		PageFactory.initElements(driver, this);
	}

	public String chooseRoleText(String roleName) {
		if (roleName.equalsIgnoreCase("Submitter Provider")) {
			return submitterrole.getText();
		} else if (roleName.equalsIgnoreCase("Responder Provider")) {
			return responderole.getText();
		} else {
			return role.getText();
		}

	}

	public SubmitterHomePage clickOnRole(String roleName) {
		if (roleName.equalsIgnoreCase("Submitter Provider")) {
			submitterrole.click();
		} else {
			responderole.click();
		}
		return new SubmitterHomePage();
	}

	public boolean isRoleDisplayed(String RoleName) {
		if (RoleName.equalsIgnoreCase("Submitter Provider"))

			return submitterrole.isEnabled();

		else
			return responderole.isEnabled();

	}
}
