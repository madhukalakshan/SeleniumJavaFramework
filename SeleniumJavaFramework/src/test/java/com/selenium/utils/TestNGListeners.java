package com.selenium.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.selenium.test.TestSelenium;

public class TestNGListeners implements ITestListener {
	
	InitiateReport initiateReport;
	private static ExtentTest test = null;
	
	public TestNGListeners() {
		initiateReport = new InitiateReport();
		initiateReport.initiate();
		test = initiateReport.getExtentTest();
	}

	public void onTestFailure(ITestResult result) {
		String screenShotPath;
		try {
			screenShotPath = capture(TestSelenium.driver, "screenShotName");
			test.fail(TestSelenium.errorMsg, MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String capture(WebDriver driver, String screenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\ErrorScreenshots\\" + screenShotName + ".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);

		return dest;
	}

}
