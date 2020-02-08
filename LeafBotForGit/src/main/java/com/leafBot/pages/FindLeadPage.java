package com.leafBot.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.leafBot.testng.api.base.ProjectSpecificMethods;


public class FindLeadPage extends ProjectSpecificMethods {


	public FindLeadPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test){
		this.driver = driver;
		this.node = node;
		this.test = test;		
		PageFactory.initElements(driver, this);
	}
	@FindBy(how=How.XPATH,using="(//input[@name='firstName'])[3]")
	private WebElement elefindFirstName;
	public FindLeadPage enterFirstName(String findfistname){
		clearAndType(elefindFirstName, findfistname);
		return this;

	}
	@FindBy(how=How.NAME,using="id")
	private WebElement eleLeadId;
	public FindLeadPage enterLeadId(String findfistname){
		clearAndType(eleLeadId, findfistname);
		return this;

	}
	@FindBy(how=How.XPATH,using="//button[text()='Find Leads']")
	private WebElement eleFindleadsButton;
	public FindLeadPage clickFindleadsButton(){
		click(eleFindleadsButton);
		return this;
	}
	
	@FindBy(how=How.XPATH,using="(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")
	public WebElement eleGetResultingLeads;
	public String getFirstResultingLead(){	
		return 	getElementText(eleGetResultingLeads);
	}
	@FindBy(how=How.XPATH,using="(//div[@class='x-grid3-cell-inner x-grid3-col-firstName']/a)[1]")
	public WebElement eleGetResultingFName;
	public String getFirstResultingFirstName() throws InterruptedException{	
		Thread.sleep(1000);
		return 	getElementText(eleGetResultingFName);
	}

	@FindBy(how=How.XPATH,using="(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")
	public WebElement eleResultingLeads;
	public ViewLeadPage clickFirstResultingLead(){
		click(eleResultingLeads);
		return new ViewLeadPage(driver, node, test);
	}
	@FindBy(how=How.XPATH,using="//span[text()='Phone']")
	public WebElement elePhoneTab;
	public FindLeadPage clickPhoneTab(){
		click(elePhoneTab);
		return this;
	}
	@FindBy(how=How.XPATH,using="//span[text()='Email']")
	public WebElement eleEmailTab;
	public FindLeadPage clickEmailTab(){
		click(eleEmailTab);
		return this;
	}
	@FindBy(how=How.NAME,using="phoneNumber")
	public WebElement elePhoneNumberField;
	public FindLeadPage enterPhoneNumberField(String PhoneNumber){
		clearAndType(elePhoneNumberField, PhoneNumber);
		return this;
	}
	@FindBy(how=How.NAME,using="emailAddress")
	public WebElement eleEmailAddress;
	public FindLeadPage enterEmailAddress(String emailAddress){
		clearAndType(eleEmailAddress, emailAddress);
		return this;
	}
	
	@FindBy(how=How.CLASS_NAME,using="x-paging-info")
	public WebElement eleErrorMsg;
	public FindLeadPage verifyErrorMsg(String eleErrorMsgValue){
		verifyPartialText(eleErrorMsg, eleErrorMsgValue);
		return this;
	}
	
	
	
}