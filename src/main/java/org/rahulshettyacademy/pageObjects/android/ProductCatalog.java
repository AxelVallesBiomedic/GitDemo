package org.rahulshettyacademy.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalog extends AndroidActions {

	AndroidDriver driver;
	
	@AndroidFindBy(xpath="//*[@text='ADD TO CART']")
	private List<WebElement> addToCart;					//this way you construct as find ElementS in plural
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement buttonCart;
	//driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

	
	public ProductCatalog(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	public void addItemToCartByIndex(int index) {
		addToCart.get(index).click();		
	}
	
	public CartPage goToCartPage() throws InterruptedException {
	buttonCart.click();
	Thread.sleep(2000);
	return new CartPage(driver);

	}
}
