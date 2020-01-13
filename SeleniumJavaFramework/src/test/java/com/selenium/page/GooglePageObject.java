package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GooglePageObject {
	
	WebDriver driver=null;
	
	By userName= By.id("txtUsername");
	By passWord= By.id("txtPassword");
	By logIn=By.id("btnLogin");
	By message=By.id("spanMessage");
	
	public GooglePageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterUserName(String username) {
		driver.findElement(userName).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passWord).sendKeys(password);
	}
	
	public void clickLoginButton() {
		driver.findElement(logIn).sendKeys(Keys.RETURN);
	}
	
	public String getMessage() {
		String msg=driver.findElement(message).getText();
		return msg;
	}
	
	public void closeWindow() {
		driver.close();
	}

}
