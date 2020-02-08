package com.leafBot.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.leafBot.testng.api.base.ProjectSpecificMethods;

public class TestLoginPage extends ProjectSpecificMethods {
	
	public TestLoginPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test){
		this.driver = driver;
		this.node = node;
		this.test = test;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(how=How.ID,using="email")
	private WebElement userName;

	public TestLoginPage enterUserName(String uName){
		clearAndType(userName, uName);
		return this;

	}
	
	@FindBy(how=How.ID,using="password")
	private WebElement passWord;

	public TestLoginPage enterPassword(String pWord){
		clearAndType(passWord, pWord);
		return this;

	}
	
	@FindBy(how=How.XPATH,using="//button[@id='buttonLogin']")
	private WebElement eleLoginButton;
	public DashBoardPage clickLoginButton(){
		click(eleLoginButton);
		return new DashBoardPage(driver, node, test);
	}

}
