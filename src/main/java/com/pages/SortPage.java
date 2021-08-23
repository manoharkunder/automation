package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Base.TestBase;

public class SortPage  extends TestBase{
	
	@FindBy(xpath = "//span[text()='Oldest']")
	WebElement oldsort;
	
	@FindBy(xpath = "//span[text()='Newest']")
	WebElement newsort;
	
	@FindBy(xpath = "//span[@role='message_tag']")
	WebElement chatTime;
	
	@FindBy(xpath = "//div[@role='filter_holder']//img[contains(@src,'/static/sort')]")
	WebElement sortbtn;

	@FindBy(css = "div.close___D8mT6")
	WebElement sortClose;

	
	public SortPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean sortOptionsIsEnable(String sortName)
	{
		if(sortName.contains("New"))
		{
			return newsort.isEnabled();
		}
		else
			return oldsort.isEnabled();
		
	}
	
	public String  isSortVisible(String sortOption)
	{
		if(sortOption.contains("New"))
		{
			return newsort.getText();
		}
		else
			return oldsort.getText();
		
	}
	
	
	public String[] clickonSort() 
	{
		String newChat=chatTime.getText();
		sortbtn.click();
		
		oldsort.click();
		String oldChat=chatTime.getText();
		String[]timeResult=new String[2];
		timeResult[0]=newChat;
		timeResult[1]=oldChat;
		
		return timeResult;
	}
	
	public void closeSort()
	{
		try
		{
		sortbtn.click();
		sortClose.click();
		
		}
		catch (Exception e) {
			driver.findElement(By.xpath("//div[@role='filter_holder']//img[contains(@src,'/static/sort')]")).click();
			driver.findElement(By.cssSelector("div.close___D8mT6"));
		}
		
		
	}
	
	public SearchPage getSearchPageObj()
	{
		return new SearchPage();
	}
	
	}
	

