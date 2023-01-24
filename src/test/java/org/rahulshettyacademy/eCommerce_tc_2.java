package org.rahulshettyacademy;

import org.openqa.selenium.By;
import org.rahulshettyacademy.TestUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_tc_2 extends BaseTest {
	
	/*@BeforeMethod
	public void preSetup()
	{		
		//start navigation on a  specific page
		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}*/
	
	@Test
	public void FillForm_ErrorTesting() throws InterruptedException
	{
		
////Actual automation
		formPage.submitForm();
		
		//toast is the popup message, interview grade question xpath is pretty much always like this for toast message, and it always has name attribute
		String toastMessage = formPage.errorMessage();
		Assert.assertEquals(toastMessage, "Please enter your name");
		
		
	}
	
	@Test
	public void FillForm_WorkingCorrectly() {
		
		formPage.setNameField("Axel Castruita");
		
		formPage.setGender("Female");
		
		formPage.setCountry("Argentina");
	
		formPage.submitForm();
		
		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
		
	}

}