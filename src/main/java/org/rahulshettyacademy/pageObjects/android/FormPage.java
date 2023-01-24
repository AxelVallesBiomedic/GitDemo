package org.rahulshettyacademy.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{
	
	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Axel Castruita"); 
	
	@AndroidFindBy(xpath ="//*[@text='Female']")
	private WebElement femaleGender;
	//		driver.findElement(By.xpath("//*[@text='Female']")).click();
	
	@AndroidFindBy(xpath="//*[@text='Male']")
	private WebElement maleGender;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdownMenu;
	//driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();

	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	@AndroidFindBy(xpath="(//android.widget.Toast)[1]")
	private WebElement toast;
	
	
	
	/*
	 * 		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		scrollToElement("Argentina");
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
	 */

	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender) {
		if(gender.contains("Female")) {
			femaleGender.click();
		}
		else {
			maleGender.click();
		}
	}

	public void setCountry(String country) {
		countryDropdownMenu.click();
		scrollToElement(country);
		driver.findElement(By.xpath("//*[@text='" + country + "']")).click();
	}
	
	public ProductCatalog submitForm() {
		shopButton.click();
		return new ProductCatalog(driver);
	}
	
	public String errorMessage() {
		return toast.getAttribute("name");
	}
	

}
