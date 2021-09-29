package com.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.AfterSuite;

import com.util.TestUtil;
import com.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebDriverEventListener eventListener;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {

		String browserName = prop.getProperty("browser");
		ChromeOptions options = new ChromeOptions();

		if (prop.getProperty("mode").equals("headless")) {
			options.addArguments("--headless");
			options.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else {
			if (browserName.equals("chrome")) {
				
				options.addArguments("--disable-notifications");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
			} else if (browserName.equals("Firefox")) {
			
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}
	
	  @AfterSuite public void flushReport() throws Exception { generateReport();
	  
	  SendEmail email = new SendEmail(); email.sendEmail();
	  
	  }
	  
	  public static void generateReport() throws Exception { FileOutputStream file
	  = new FileOutputStream( System.getProperty("user.dir") +
	  "/src/main/java/com/config/config.properties");
	  
	  
	 // prop.setProperty("url","file:///home/niveus/Afya-Automation/test-output/Afya_Extent.html");
	  prop.setProperty("url","http://localhost:8080/job/Automation/ws/test-output/Afya_Extent.html");
	  prop.store(file, null); initialization(); prop.setProperty("url",
	  "https://stagingwebapp.afya.chat/"); prop.store(file, null) ; file.close();
	  TestUtil.generateScreenShot();
	  
	  }
	  
	  public static void updateScreenShot() throws Exception { generateReport(); }
	 
}
