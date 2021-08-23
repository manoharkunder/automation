package com.pages;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Base.TestBase;


public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="userName")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(tagName = "button")
	WebElement loginBtn;
	
	@FindBy(xpath="//span[text()='Forgot Password?']")
	WebElement signUpBtn;
	
	@FindBy(xpath="//span[text()='Login to your account']")
	WebElement logintext;
	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver.getTitle();
	}
	
	public boolean validateCRMImage(){
		return logintext.isDisplayed();
	}
	
	public AlertPage login(String un, String pwd){
		username.sendKeys(un);
		password.sendKeys(pwd);
		//loginBtn.click();
		    	JavascriptExecutor js = (JavascriptExecutor)driver;
		    	js.executeScript("arguments[0].click();", loginBtn);
		    	
		return new AlertPage();
	}
	
}

