package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.TestBase;

public class FilterPage extends TestBase {
	WebDriverWait wait = new WebDriverWait(driver, 10);

	@FindBy(xpath = "//span[text()=' All Chats']")
	WebElement allchatFilter;

	@FindBy(xpath = "//span[text()=' Unresponded Chats']")
	WebElement unrespondeFilter;

	@FindBy(xpath = "//span[text()='Unread Chats']")
	WebElement unreadFilter;

	@FindBy(xpath = "//span[text()='Closed Chats']")
	WebElement closeFilter;

	@FindBy(xpath = "//span[text()='Live Chats']")
	WebElement liveFilter;

	@FindBy(xpath = "//span[@role='active']")
	WebElement activeFilter;

	@FindBy(css = "div.close___D8mT6")
	WebElement filterclose;

	@FindBy(xpath = "//div[@role='filter_holder']//img[contains(@src,'data:image/png;')]")
	WebElement filterbtn;

	public FilterPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isFilterEnabled(String filterName) {
		if (filterName.contentEquals("unresponded Chats"))
			return unrespondeFilter.isEnabled();
		else if (filterName.contains("all chats"))
			return allchatFilter.isEnabled();
		else if (filterName.contains("unread chats"))
			return unreadFilter.isEnabled();
		else if (filterName.contains("closed chats"))
			return closeFilter.isEnabled();
		else
			return liveFilter.isEnabled();
	}

	public String isFilterActive(String filterName) {

		if (filterName.equalsIgnoreCase("Unresponded")) {
			try {
				unrespondeFilter.click();
			} catch (Exception e) {
				driver.findElement(By.xpath("//span[text()=' Unresponded Chats']")).click();
			}

			/*
			 * boolean flag = true; while (flag == false) { if (activeFilter.getText() ==
			 * "Unresponded") { flag = false; } else {
			 * Reporter.log("Please wait....!!!...Page is still loading...!!!", true); }
			 * 
			 * }
			 */
			return activeFilter.getText();
		} else if (filterName.equalsIgnoreCase("All Chats")) {

			// TestUtil.wait.until(ExpectedConditions.elementToBeClickable(allchatFilter));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' All Chats']")));
				allchatFilter.click();
			} catch (StaleElementReferenceException e) {

				WebElement allFilter = driver.findElement(By.xpath("//span[text()=' All Chats']"));

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", allFilter);

			} catch (NoSuchElementException e) {
				driver.findElement(By.xpath("//span[text()=' All Chats']")).click();
			} catch (ElementClickInterceptedException e) {
				WebElement allChat = driver.findElement(By.xpath("//span[text()=' All Chats']"));

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", allChat);
			} catch (Exception e) {
				driver.findElement(By.xpath("//span[text()=' All Chats']")).click();

			}
			/*
			 * boolean flag = true; while (flag == false) { if (activeFilter.getText() ==
			 * "All Chats") { flag = false; } else {
			 * Reporter.log("Please wait....!!!...Page is still loading...!!!", true); }
			 * 
			 * }
			 */ return activeFilter.getText();
		}

		else if (filterName.equalsIgnoreCase("Unread Chats")) {
			try {
				unreadFilter.click();

			} catch (Exception e) {
				driver.findElement(By.xpath("//span[text()='Unread Chats']")).click();

			}
			return activeFilter.getText();
		} else if (filterName.equalsIgnoreCase("Closed")) {

			try {
				closeFilter.click();

			} catch (Exception e) {

				WebElement closed = driver.findElement(By.xpath("//span[text()='Closed Chats']"));
				closed.click();
			}
			/*
			 * boolean flag = true; while (flag == false) { if (activeFilter.getText() ==
			 * "Closed") { flag = false; } else {
			 * Reporter.log("Please wait....!!!...Page is still loading...!!!", true); } }
			 */

			return activeFilter.getText();
		} else {
			try {
				liveFilter.click();
			} catch (Exception e) {
				driver.findElement(By.xpath("//span[text()='Live Chats']")).click();

			}
			return activeFilter.getText();
		}
	}

	public SortPage retrnClassObject() {
		return new SortPage();
	}

	public void closeFilter() {
		try {

			// filterbtn.click();

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", filterbtn);

			executor.executeScript("arguments[0].click();", filterclose);

			// filterclose.click();

		} catch (Exception e) {
			driver.findElement(By.xpath("//div[contains(@class,'close___')]")).click();
		}

	}

	public ChatPage chatPageObj() {
		return new ChatPage();
	}

}
