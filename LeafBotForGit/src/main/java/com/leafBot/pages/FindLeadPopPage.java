package com.leafBot.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.leafBot.testng.api.base.ProjectSpecificMethods;


public class FindLeadPopPage extends ProjectSpecificMethods {


	public FindLeadPopPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test){
		this.driver = driver;
		this.node = node;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	@FindBy(how=How.NAME,using="firstName")
	private WebElement elefindFirstName;
	public FindLeadPopPage enterFirstName(String findfistname){
		clearAndType(elefindFirstName, findfistname);
		return this;

	}
	@FindBy(how=How.XPATH,using="//button[text()='Find Leads']")
	private WebElement eleFindleadsButton;
	public FindLeadPopPage clickFindleadsButton(){
		click(eleFindleadsButton);
		return this;
	}
	@FindBy(how=How.XPATH,using="(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")
	private WebElement eleGetResultingLeads;
	public String getFirstResultingLead(){	
		return getElementText(eleGetResultingLeads);
	}

	@FindBy(how=How.XPATH,using="(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")
	private WebElement eleResultingLeads;
	public MergeLeadPage clickResultingLeads(){
		clickWithNoSnap(eleResultingLeads);
		switchToWindow(0);
		return new MergeLeadPage(driver, node, test);
	}
}