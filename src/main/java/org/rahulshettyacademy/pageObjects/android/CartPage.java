package org.rahulshettyacademy.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {
	
	AndroidDriver driver;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrice;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPrice;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsButton;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement confirmTermsButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement confirmCartButton;
	

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
		
	}
	
	public List<WebElement> getProductList(){
		return productPrice;
	}
	
	public double getProductSum() {
		int count = productPrice.size();
		double totalSum =0;
		
		for(int i=0;i<count;i++) {
			String amountInString = productPrice.get(i).getText();
			Double price = Double.parseDouble(amountInString.substring(1));
			totalSum += price;
		}
		return totalSum;
	}
	
	public double confirmPrice() {
	
			String totalAmountInString = totalPrice.getText();
			Double totalPrice = changeDolarToAmount(totalAmountInString);  
			return totalPrice;
		}
	
	
		public void clickCheckbox(){
		checkBox.click();
		}
		
		public void checkTerms() {
			longpress(termsButton);
			confirmTermsButton.click();
		}
		
		public void completeBuy() throws InterruptedException {
			driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
			Thread.sleep(5000);
		} 
	}

