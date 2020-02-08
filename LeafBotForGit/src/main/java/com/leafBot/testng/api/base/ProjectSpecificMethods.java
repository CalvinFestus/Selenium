package com.leafBot.testng.api.base;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.leafBot.selenium.api.base.SeleniumBase;

import utils.DataLibrary;


public class ProjectSpecificMethods extends SeleniumBase {

	public String dataSheetName;		

	@DataProvider(name = "fetchData")
	public Object[][] fetchData() throws IOException {
		String [][] data = new String[1][2];
		
		data[0][0] = "kumar.testleaf@gmail.com";
		data[0][1] = "leaf@12";
	
		
		return data;
	}	

	@BeforeMethod
	public void beforeMethod() {
		driver = startApp("chrome", "https://acme-test.uipath.com/account/login");
		node = test.createNode(testCaseName);
	}

	@AfterMethod
	public void afterMethod() {
		close();
	}













}
