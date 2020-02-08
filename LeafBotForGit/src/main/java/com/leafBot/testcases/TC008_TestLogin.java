package com.leafBot.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.leafBot.pages.TestLoginPage;
import com.leafBot.testng.api.base.ProjectSpecificMethods;

public class TC008_TestLogin extends ProjectSpecificMethods{
	
	@BeforeTest
	public void setValues() {
		testCaseName = "TestLogin";
		testDescription = "Login and Verify Title";
		nodes = "Leads";
		authors = "Calvin";
		category = "Test";
		
	}

	@Test(dataProvider = "fetchData")
	public void makeLogin(String uName, String pwd) throws InterruptedException {
		new TestLoginPage(driver, node, test)
		.enterUserName(uName)
		.enterPassword(pwd)
		.clickLoginButton()
		.getTitleForPage();
		
		Thread.sleep(5000);

	}

}
