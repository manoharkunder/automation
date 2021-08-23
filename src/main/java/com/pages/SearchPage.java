package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.TestBase;

public class SearchPage extends TestBase {

	@FindBy(xpath  = "//div[@role='session_search']")
	WebElement clearSearch;

	@FindBy(xpath = "//input[@placeholder='Search..']")
	WebElement search;

	@FindBy(xpath = "//div[contains(@class,'chatItem')]")
	WebElement chatList;

	WebDriverWait wait = new WebDriverWait(driver, 10);

	public SearchPage() {

		PageFactory.initElements(driver, this);
	}

	public boolean clearBtnEnabled() {
		return clearSearch.isEnabled();
	}

	public void ClearSearch() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", clearSearch);

		clearSearch.click();
	}

	public String doSearch(String searchData) {

		try {
			String js = "arguments[0].setAttribute('value','" + searchData + "')";
			((JavascriptExecutor) driver).executeScript(js, search);
			// search.sendKeys(searchData);
		} catch (Exception e) {

			WebElement searchRes = driver.findElement(By.xpath("//input[@placeholder='Search..']"));

			String js = "arguments[0].setAttribute('value','" + searchRes + "')";
			((JavascriptExecutor) driver).executeScript(js, search);
		}
		wait.until(ExpectedConditions.visibilityOf(chatList));
		return chatList.getText();

	}

}
