package org.rahulshettyacademy.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class appiumUtils {

	public AppiumDriverLocalService service;

	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
	    service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\axelc\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).withIPAddress(ipAddress).usingPort(port).build();
		service.start();
		return service;
	}
	
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") +"\\reports"+ testCaseName +".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}
}
