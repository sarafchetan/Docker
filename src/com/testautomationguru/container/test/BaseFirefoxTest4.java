package com.testautomationguru.container.test;


import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BaseFirefoxTest4 {

	@Test
	public void chromeTest1() throws MalformedURLException {
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver= new RemoteWebDriver(url,dc);
		driver.get("https://www.yahoo.com");
		System.out.println("Navigated to google search page");
		driver.getTitle();
		driver.quit();
		
	}
    
    
}
