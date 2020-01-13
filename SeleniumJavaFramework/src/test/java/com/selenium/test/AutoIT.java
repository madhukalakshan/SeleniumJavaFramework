package com.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.interactions.Actions;

public class AutoIT {

	public static void main(String[] args) {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.tinyupload.com");
		driver.manage().window().maximize();
		WebElement webElement = driver.findElement(By.name("uploaded_file"));
		Actions builder = new Actions(driver);
		builder.moveToElement(webElement).click(webElement);
		builder.perform();
		//Runtime.getRuntime().exec("E:/Lakshan/Workplace/SeleniumJavaFramework/Excel/Upload.exe");
		
		String filepath =System.getProperty("user.dir")+"\\Excel\\data.txt";	
		System.out.println("cmd.exe /c Start AutoIt3.exe Excel/Upload.exe \\"+ filepath);
		Runtime.getRuntime().exec("cmd.exe /c Start AutoIt3.exe Excel/Upload.exe \\"+ filepath);
		
		System.out.println("---completed-------");

	}
}
