package com.selenium.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class InitiateReport {
	
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent;
	private static ExtentTest test;
	
	public void initiate() {
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest("Google search","This is test to validate google search");
	}
	
	public void closeReport() {
		extent.flush();
	}
	
	public ExtentTest getExtentTest() {
		return test;
	}
	
	

}
