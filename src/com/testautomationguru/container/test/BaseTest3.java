package com.testautomationguru.container.test;


import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BaseTest3 {

	@Test
	public void chromeTest1() throws MalformedURLException {
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver= new RemoteWebDriver(url,dc);
		driver.get("https://www.Flipkart.com");
		System.out.println("Navigated to Flipkart page");
		driver.getTitle();
		driver.quit();
		
	}
    
    
}
