package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.Base.TestBase;

public class ChatPage extends TestBase {

	@FindBy(id = "chat_session_items")
	WebElement chatId;

	@FindBy(xpath = "//div[@role='header_name']")
	WebElement ownerName;

	@FindBy(xpath = "//div[contains(@class,'details_contaner')]")
	WebElement patietnBaaner;

	@FindBy(xpath = "//span[@role='img']")
	WebElement more;

	@FindBy(xpath = "//img[@role='video_call_icon']")
	WebElement videoICon;

	@FindBy(id = "input")
	WebElement textarea;

	@FindBy(xpath = "//div[@id='textArea']//div[contains(@class,'attachment___2t')]")
	WebElement attachFileImg;

	@FindBy(xpath = "//div[@id='textArea']//div[contains(@class,'trigger attachmen')]")
	WebElement codesTriger;

	@FindBy(xpath = "//div[@id='textArea']//div[contains(@class,'button')]")
	WebElement sendBtn;

	@FindBy(xpath = "//div[contains(@class,'chatinputcontainer')]")
	WebElement atttachement;

	@FindBy(xpath = "//div[contains(@class,'close___')]")
	WebElement closeAttachment;

	@FindBy(xpath = "//ul[contains(@class,'menu')]/parent::div[@class='list-sort-demo']")
	WebElement codesList;

	@FindBy(xpath = "//span[text()='Members']/parent::div//div[@class='rc-scrollbars-container']")
	WebElement videoMemberList;
	WebDriverWait wait=new WebDriverWait(driver, 10);
	

	public ChatPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnChat() {
		chatId.click();
	}

	public String chatHeaderIsDisplayed(String headerName) {
		if (headerName.contains("OwnerName")) {
			wait.until(ExpectedConditions.visibilityOf(ownerName));
			return ownerName.getText();
		} else {
			return patietnBaaner.getText();
		}

	}

	public boolean elementIsEnabled(String elementName) {
		if (elementName.equalsIgnoreCase("video call")) {
			return videoICon.isEnabled();
		} else if (elementName.equalsIgnoreCase("attachment")) {
			return attachFileImg.isEnabled();
		} else if (elementName.equalsIgnoreCase("@codes")) {
			return codesTriger.isEnabled();
		} else if (elementName.equalsIgnoreCase("text area")) {
			return textarea.isEnabled();
		}

		else {
			return sendBtn.isEnabled();
		}
	}

	public String mediaOptionIsVisible() {
		try {
		chatId.click();
		}
		catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", chatId);
		}
		try {
				attachFileImg.click();
			}
			catch (Exception e) {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", attachFileImg);
			}
		return atttachement.getText();
	}

	public void atCodesListIsVisible() {
		try {
		chatId.click();
		}
		catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", chatId);	
			}
		try {
		codesTriger.click();
		}
		catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", codesTriger);
		}
		ArrayList<String> codesList = new ArrayList<>();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> atList = driver.findElements(By.xpath("//div[@class='list-sort-demo']//li"));
		for (int i = 0; i < atList.size(); i++) {
			codesList.add(atList.get(i).getText().replaceAll(">", ""));
		}
		for (String codes : codesList) {
			String code = codes.replaceAll("\n", "");
			if (code.equalsIgnoreCase("Specialty")) {
				Assert.assertEquals(code, "Specialty", "Specialty options not found.in @section..!!!..");
			} else if (code.equalsIgnoreCase("Participant")) {

				Assert.assertEquals(code, "Participant", " Participant option not found in the @ section..!!!");
			} else if (code.equalsIgnoreCase("Close")) {
				Assert.assertEquals(code, "Close", "Close option not found in the @ section..!!!.");
			} else {
				Assert.assertEquals(code, "Staff", "Staff option not found in the @ section..!!!.");
			}
		}

	}

	public void videoMemberVisiblility() {
		chatId.click();
		videoICon.click();
		List<WebElement> member = driver.findElements(By.xpath("//span[contains(@class,'user_name__')]/span[1]"));
		for (WebElement mem : member) {
			if(mem.getText().equalsIgnoreCase("prasanna achar"))
			{
				String e= mem.getText();
				System.out.println(e);
				Assert.assertEquals(mem.getText(), "prasanna achar");
			}
			else if(mem.getText().equalsIgnoreCase("Kalkudka Pai"))
			{
				Assert.assertEquals(mem.getText(), "Kalkudka Pai");
			}
			else 
			{
				Assert.assertEquals(mem.getText(), "Prasanna Consumer");
			}
		}
	}

}
