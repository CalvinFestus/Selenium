package seleniumPractice;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day9CRM {
	
	public static void main(String[] args) throws InterruptedException {
		
		// 1) Go to https://demo.1crmcloud.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		op.merge(cap);
		ChromeDriver driver = new ChromeDriver(op);
		driver.get("https://demo.1crmcloud.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		// 2) Give username as admin and password as admin
		driver.findElementById("login_user").sendKeys("admin");		
		driver.findElementById("login_pass").sendKeys("admin");
		
		// 3) Choose theme as Claro Theme
		WebElement themeEle = driver.findElementById("login_theme");
		Select theme = new Select(themeEle);
		theme.selectByValue("Claro");
		
		driver.findElementByXPath("//span[@class='uii uii-arrow-right']").click();
		Thread.sleep(2000);
		
		// 4) Click on Sales and Marketting 
		WebElement salesAndMarketing = driver.findElementByXPath("//div[text()='Sales & Marketing']");
		salesAndMarketing.click();
		Thread.sleep(2000);
		
		// 5) Click Create contact
		driver.findElementByXPath("//div[text()='Create Contact']").click();
		Thread.sleep(2000);
		
		// 6) Select Title and type First name, Last Name, Email and Phone Numbers
		JavascriptExecutor js = (JavascriptExecutor)driver;
		wait.until(ExpectedConditions.elementToBeClickable(By.id("DetailFormsalutation-input")));
		driver.findElementById("DetailFormsalutation-input").sendKeys("Mr.");;
		
		Thread.sleep(4000);
		
		driver.findElementByXPath("//div[@id='DetailFormsalutation-input-popup']//div[text()='Mr.']").click();		
		Thread.sleep(2000);
		
		driver.findElementByXPath("//input[@name='first_name']").sendKeys("Vijay");
		Thread.sleep(2000);
		
		driver.findElementByXPath("//input[@name='last_name']").sendKeys("Sethupathy");
		Thread.sleep(2000);
		
		driver.findElementByXPath("//input[@name='email1']").sendKeys("vijay.sethupathy@gmail.com");
		Thread.sleep(2000);
		
		driver.findElementByXPath("//input[@name='phone_work']").sendKeys("9171347542");
		Thread.sleep(2000);
		
		// 7) Select Lead Source as "Public Relations"
		driver.findElementByXPath("(//div[@class='input-field input-field-group rbullet'])[2]").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//div[text()='Public Relations']").click();
		Thread.sleep(2000);
		
		// 8) Select Business Roles as "Sales"
		driver.findElementByXPath("//div[@id='DetailFormbusiness_role-input']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//div[text()='Sales']").click();
		Thread.sleep(2000);

		// 9) Fill the Primary Address, City, State, Country and Postal Code and click Save
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys("No:6 Vivekanandar Street");
		Thread.sleep(2000);
		
		driver.findElementById("DetailFormprimary_address_city-input").sendKeys("Chennai");
		Thread.sleep(2000);
		
		driver.findElementById("DetailFormprimary_address_state-input").sendKeys("Tamil Nadu");
		Thread.sleep(2000);
		
		driver.findElementById("DetailFormprimary_address_country-input").sendKeys("India");
		Thread.sleep(2000);
		
		driver.findElementById("DetailFormprimary_address_postalcode-input").sendKeys("600028");
		Thread.sleep(2000);
		
		driver.findElementById("DetailForm_save2").click();
		Thread.sleep(2000);
		
		// 10) Mouse over on Today's Activities and click Meetings
		WebElement todaysActivities = driver.findElementByXPath("//ul[@class='menubar-main']/li[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(todaysActivities).perform();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//div[text()='Meetings']").click();
		Thread.sleep(2000);
		
		// 11) Click Create 
		driver.findElementByXPath("(//div[@class='input-icon left icon-edit'])[1]").click();
		Thread.sleep(2000);
		
		// 12) Type Subject as "Project Status" , Status as "Planned" 
		driver.findElementById("DetailFormname-input").sendKeys("Project Status");
		Thread.sleep(2000);
		
		driver.findElementById("DetailFormstatus-input-label").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//div[text()='Planned'])[2]").click();
		Thread.sleep(2000);
		
		// 13) Start Date & Time as tomorrow 3 pm and Duration as 1hr
		driver.findElementByXPath("//div[@class='input-label datetime-label text-number']").click();
		Thread.sleep(2000);
		
		String dateTodayText = driver.findElementByXPath("//div[@class='grid-cell number-cell text-right day inside current selected responsive']").getText();
		int dateToday = Integer.parseInt(dateTodayText);
		int dateTmw = dateToday+1;
		Thread.sleep(2000);
		
		driver.findElementByXPath("//div[@class='grid-cell number-cell text-right day inside current selected responsive']/following::div[1]").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//input[@class='input-text'])[4]").clear();
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//input[@class='input-text'])[4]").sendKeys("15:00");
		Thread.sleep(2000);
		
		driver.findElementByXPath("//div[@class='active-icon uii-accept uii-lg uii']").click();
		Thread.sleep(2000);
		
		driver.findElementById("DetailFormduration-time").clear();
		Thread.sleep(2000);
		
		driver.findElementById("DetailFormduration-time").sendKeys("1");
		Thread.sleep(2000);
		
		// 14) Click Add paricipants, add your created Contact name and click Save
		driver.findElementByXPath("//span[text()=' Add Participants']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//div[@class='input-label input-field-icon lg'])[2]/div/following::input").sendKeys("Vijay Sethupathy");
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//div[@class='option-cell input-label '])[14]").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		Thread.sleep(2000);
		
		// 15) Go to Sales and Marketting-->Contacts
		builder.moveToElement(salesAndMarketing).perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[text()='Contacts']").click();
		Thread.sleep(2000);
		
		// 16) search the lead Name and click the name from the result
		driver.findElementByXPath("//input[@class='input-text input-entry ']").sendKeys("Vijay Sethupathy");
		Thread.sleep(2000);
		
		try {
			driver.findElementByXPath("(//div[@class='card-body menu-outer input-scroll'])[3]/div/div").click();
			Thread.sleep(6000);
		}catch(Exception e) {
			
		}
		finally {
			driver.findElementByXPath("//a[@class='listViewNameLink']").click();
			Thread.sleep(6000);
		}
		
		// 17) Check weather the Meeting is assigned to the contact under Activities Section.
		if(driver.findElementByXPath("//a[@class='listViewNameLink']").isDisplayed()) {
			
			System.out.println("Meeting is Sheduled");
			
		}
		
		driver.close();
		
	}
	

}
/*
24/04/2020
============
1) Go to https://demo.1crmcloud.com/
2) Give username as admin and password as admin
3) Choose theme as Claro Theme
4) Click on Sales and Marketting 
5) Click Create contact
6) Select Title and type First name, Last Name, Email and Phone Numbers
7) Select Lead Source as "Public Relations"
8) Select Business Roles as "Sales"
9) Fill the Primary Address, City, State, Country and Postal Code and click Save
10) Mouse over on Today's Activities and click Meetings
11) Click Create 
12) Type Subject as "Project Status" , Status as "Planned" 
13) Start Date & Time as tomorrow 3 pm and Duration as 1hr
14) Click Add paricipants, add your created Contact name and click Save
15) Go to Sales and Marketting-->Contacts
16) search the lead Name and click the name from the result
17) Check weather the Meeting is assigned to the contact under Activities Section.*/