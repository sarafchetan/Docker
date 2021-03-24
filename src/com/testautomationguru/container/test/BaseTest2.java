package com.testautomationguru.container.test;


import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BaseTest2 {

	@Test
	public void chromeTest1() throws MalformedURLException {
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		URL url = new URL("http://localhost:4444/wd/hub");
		
		RemoteWebDriver driver= new RemoteWebDriver(url,dc);
		driver.get("https://www.amazon.com");
		System.out.println("Navigated to amazon page");
		driver.getTitle();
		driver.quit();
		
	}
    
    
}
