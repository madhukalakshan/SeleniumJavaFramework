package com.selenium.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.config.PropertiesFile;
import com.selenium.page.GooglePageObject;
import com.selenium.utils.ExcelDataProvider;
import com.selenium.utils.InitiateReport;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestSelenium {
	public static WebDriver driver = null;
	public static String errorMsg = null;
	String projectPath;

	GooglePageObject googlePageObject;
	PropertiesFile propertiesFile;
	InitiateReport initiateReport;
	ExcelDataProvider excelUtils;
	ExcelDataProvider excelDataProvider;

	private static ExtentTest test = null;

	Logger log = LogManager.getLogger(TestSelenium.class);

	public TestSelenium() {
		initiateReport = new InitiateReport();
		test = initiateReport.getExtentTest();
		propertiesFile = new PropertiesFile();
	}

	@Parameters("browserName")
	@BeforeTest
	@Test(groups = { "smoke" })
	public void initiate(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("mozila")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		googlePageObject = new GooglePageObject(driver);

		String url = propertiesFile.getProperties("url");
		driver.get(url);
		driver.manage().window().maximize();
	}

	@DataProvider(name = "testData1")
	public Object[][] getData1() {
		projectPath = System.getProperty("user.dir");
		excelDataProvider = new ExcelDataProvider(projectPath + "/Excel/Data.xlsx", "Sheet1");
		Object data1[][] = excelDataProvider.testData();
		return data1;
	}

	@Test(priority = 1, dataProvider = "testData1", groups = { "smoke" })
	public void enterLoginDetails(String userName, String Password) {

		System.out.println("\n initiate \n");

		log.info("This is an information message");
		log.error("This is an error message");
		log.warn("This is an warning message");
		log.fatal("This is an fatal message");

		System.out.println("\n completed \n");

		test.log(Status.INFO, "Starting Login");
		googlePageObject.enterUserName(userName);
		googlePageObject.enterPassword(Password);
		test.pass("Entered login details are success");
	}

	@Test(priority = 2, groups = { "smoke" })
	public void clickSearch() {
		googlePageObject.clickLoginButton();
		test.pass("Click on Login button");
	}

	@Test(priority = 3, groups = { "smoke" })
	public void validateMessage() {
		String msg = googlePageObject.getMessage();
		if (msg.equalsIgnoreCase("success")) {
			test.pass("Login is success");
		} else {
			errorMsg = "Login is failed";
			Assert.fail();
		}
	}

	@AfterSuite
	public void closeWindow() {
		driver.close();
		driver.quit();
		test.pass("Close the window");
		test.info("Test completed");
		initiateReport.closeReport();
	}

}