package org.rahulshettyacademy.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {
	
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
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
	
}
