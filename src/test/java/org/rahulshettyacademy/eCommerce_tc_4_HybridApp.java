package org.rahulshettyacademy;

import java.net.MalformedURLException;

import org.rahulshettyacademy.TestUtils.BaseTest;
import org.rahulshettyacademy.pageObjects.android.CartPage;
import org.rahulshettyacademy.pageObjects.android.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class eCommerce_tc_4_HybridApp extends BaseTest {
	 
	
	@Test(dataProvider="getData", groups= {"Smoke"})
	public void ConfirmCost(String name, String gender, String country) throws MalformedURLException, InterruptedException
	{
		
////Actual automation
		 		
		formPage.setNameField(name);
		
		formPage.setGender(gender);
		
		formPage.setCountry(country);
		
		ProductCatalog productCatalog = formPage.submitForm();
				
		productCatalog.addItemToCartByIndex(0);

		productCatalog.addItemToCartByIndex(0);

		CartPage cartPage = productCatalog.goToCartPage();
				
		double pricesSum = cartPage.getProductSum();
		double totalShown = cartPage.confirmPrice();
		
		Assert.assertEquals(pricesSum, totalShown);
		
		cartPage.clickCheckbox();
				
		cartPage.checkTerms();	
		
		cartPage.completeBuy();		
		
		
		/*
		//Web handling
		Set<String> contextList = driver.getContextHandles();
		for(String contextNames : contextList) {
			System.out.println(contextNames);
		}
			
		driver.context("WEBVIEW_com.androidsample.generalstore");
		
		driver.findElement(By.xpath("//*[@name='q']")).sendKeys("Axel Manuel Valles");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.pressKey(new KeyEvent(AndroidKey.BACK));

		driver.context("NATIVE_APP");

		*/
	}
	
	@AfterMethod(alwaysRun=true)
	public void preSetup()
	{				
		driver.terminateApp("com.androidsample.generalstore");
		driver.activateApp("com.androidsample.generalstore");
	}
	
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {{"Axel Castruita","Femmale","Argentina"}, {"Luis Flores","Male","Angola"}};
	}

}