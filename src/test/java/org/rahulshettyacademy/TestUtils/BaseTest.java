package org.rahulshettyacademy.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.rahulshettyacademy.pageObjects.android.FormPage;
import org.rahulshettyacademy.utils.appiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest extends appiumUtils{

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;
	
	@BeforeClass (alwaysRun=true)
	public void ConfigureAppium() throws IOException {
		//Code to start server
		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\rahulshettyacademy\\resources\\data.properties");
		property.load(fis);
		
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : property.getProperty("ipAddress");
		//String ipAddress= property.getProperty("ipAddress");
		String port = property.getProperty("port");
				
		
	    service = startAppiumServer(ipAddress,Integer.parseInt(port));
		
		//Appium code -> Appium Server -> Mobile
		
		//Android Driver
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(property.getProperty("AndroidDeviceName"));
		options.setChromedriverExecutable("C:\\Users\\axelc\\eclipse-workspace\\chromedriver.exe");
		options.setApp(System.getProperty("user.dir")+ "\\src\\test\\java\\org\\rahulshettyacademy\\resources\\General-Store.apk"  /*"\\src\\test\\java\\resources\\General-Store.apk"*/);
				
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage  = new FormPage(driver);
	}
	
	public void longpress(WebElement opt) {
		driver.executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((RemoteWebElement)opt).getId(),"duration",2000));

	}
	
	public void scrollToElement(String elementName) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementName + "\"));"));
	
	}
	
	public void scrollToEnd() {
		boolean canScrollMore;
		do
		{
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					"left", 100, "top", 100, "width", 200, "height", 200,
					"direction", "down",
					"percent", 2.0
					));
		}while(canScrollMore);
	}
	
	public void swipeAction(WebElement firstImage, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)firstImage).getId(),
				"direction",direction,
				"percent",0.75
				));
	}
	
	public void dragElementToCoordinates(WebElement origin,int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)origin).getId(),
				"endX",x,
				"endY",y
				));
	}
	
	public void tapOnCoordinates(WebElement origin, double x, double y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)origin).getId(),
				"endX",x,
				"endY",y
				));
	}
	
	public void logResult(String message) {
		System.out.println(message);
	}
	
	public Double changeDolarToAmount(String amount) {
		 Double price = Double.parseDouble(amount.substring(1));
		 return price;
	}
	
	@AfterClass (alwaysRun=true)
	public void TearDown() {
		//Stop Server and close app
		driver.quit(); 
	    service.stop();
	}
}
