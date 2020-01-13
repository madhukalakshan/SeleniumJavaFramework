package com.selenium.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFile {
	
	String projectPath=System.getProperty("user.dir");
	Properties pop=new Properties();
	
	public String getProperties(String propertyName) {
		String propertyValue=null;
		try {
			InputStream input=new FileInputStream(projectPath+"/src/test/java/com/selenium/config/config.properties");
			pop.load(input);
			propertyValue=pop.getProperty(propertyName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}
	
	public void setProperties() {
		try {
			OutputStream output=new FileOutputStream(projectPath+"/src/test/java/com/selenium/config/config.properties");
			pop.setProperty("pass", "value");
			pop.store(output, null);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
