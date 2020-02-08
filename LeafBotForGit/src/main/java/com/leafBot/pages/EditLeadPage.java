package com.leafBot.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.leafBot.testng.api.base.ProjectSpecificMethods;


public class EditLeadPage extends ProjectSpecificMethods {


	public EditLeadPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test){
		this.driver = driver;
		this.node = node;
		this.test = test;			
		PageFactory.initElements(driver, this);			
	}

	@FindBy(how=How.ID,using="updateLeadForm_companyName")
	private WebElement eleupdateCompanyName;
	public EditLeadPage updateCompanyName(String updcomnyName){
		clearAndType(eleupdateCompanyName, updcomnyName);
		return this;

	}
	@FindBy(how=How.CLASS_NAME,using="smallSubmit")
	private WebElement eleUpdateSubmit;
	public ViewLeadPage clickUpdateSubmit(){
		click(eleUpdateSubmit);
		return new ViewLeadPage(driver, node, test);
	}

}

