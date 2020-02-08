package com.leafBot.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.leafBot.testng.api.base.ProjectSpecificMethods;



public class ViewLeadPage extends ProjectSpecificMethods  {

	public ViewLeadPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test){
		this.driver = driver;
		this.node = node;
		this.test = test;		
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(how=How.ID,using="viewLead_firstName_sp")
	public WebElement eleLeadLink;
	public ViewLeadPage verifyFirstName(String fname) {
		verifyPartialText(eleLeadLink, fname);
		return this;
	}
	@FindBy(how=How.LINK_TEXT,using="Find Leads")
	public WebElement elefindLead;
	
	public FindLeadPage clickFindLead(){
		click(elefindLead);
		return new FindLeadPage(driver, node, test);
	}
	
	@FindBy(how=How.ID,using="viewLead_companyName_sp")
	public WebElement eleCompanyName;
	public ViewLeadPage verifyCompanyName(String CompanyName) {
		verifyPartialText(eleCompanyName, CompanyName);
		return this;
	}
	@FindBy(how=How.LINK_TEXT,using="Edit")
	public WebElement eleEditLeadLink;
	public EditLeadPage clickEditLeadLink(){
		click(eleEditLeadLink);
		return new EditLeadPage(driver, node, test);
	}
	@FindBy(how=How.LINK_TEXT,using="Duplicate Lead")
	public WebElement eleDuplicateLeadLink;
	public DuplicateLeadPage clickDuplicateLeadLink(){
		click(eleDuplicateLeadLink);
		return new DuplicateLeadPage(driver, node, test);
	}
	@FindBy(how=How.LINK_TEXT,using="Delete")
	public WebElement eleDeleteLeadLink;
	public MyLeadsPage clickDeleteLeadLink(){
		click(eleDeleteLeadLink);
		return new MyLeadsPage(driver, node, test);
	}
	
	

	
	
}
