package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.TestBase;

public class AlertPage extends TestBase {

	@FindBy(xpath = "//div[contains(@class,'footer')]//span[text()='Yes']")
	WebElement alertyes;

	@FindBy(xpath = "//div[contains(@class,'footer')]//span[text()=' No']")
	WebElement alertNo;

	@FindBy(xpath = "//span[@role='font-header-primary']")
	WebElement alertinfo;

	@FindBy(xpath = "//span[@class='anticon anticon-warning']")
	WebElement alertwarn;

	public AlertPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isWarnIconIsEnable() {
		
		return alertwarn.isEnabled();
	}

	public String titleOfAlertPopup() {
		return alertinfo.getText();
	}

	public ChooseRolePage acceptAlert() {
		try {
			if (alertyes.isEnabled())
				alertyes.click();
		} catch (Exception e) {
			e.getMessage();
		}
		return new ChooseRolePage();
	}

	public void dclineAlert() {
		try {
			if (alertyes.isEnabled())
				alertNo.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
