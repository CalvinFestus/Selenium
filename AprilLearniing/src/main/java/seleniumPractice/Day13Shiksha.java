package seleniumPractice;

import java.util.List;

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

public class Day13Shiksha {

	public static void main(String[] args) throws InterruptedException {

		double max=1000.0;
		int j=0;
		
		// 1) Go to https://studyabroad.shiksha.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		DesiredCapabilities cap = new  DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		op.merge(cap);
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://studyabroad.shiksha.com/");
		driver.manage().window().maximize();

		// 2) Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges
		WebElement colleges = driver.findElementByXPath("(//label[@class='menuTab-div fnt-wt melabel'])[3]");
		Actions builder = new Actions(driver);
		builder.moveToElement(colleges).perform();
		driver.findElementByXPath("(//label[text()='By Specialization']/following::li)[1]").click();
		
		// 3) Select GRE under Exam Accepted and Score 300 & Below
		driver.findElementByXPath("(//label[@class='exam-col-chkbx flLt'])[1]").click();
		Thread.sleep(3000);

		WebElement greScore = driver.findElementByXPath("(//select[@name='examsScore[]'])[1]");
		Thread.sleep(3000);
		Select greMark = new Select(greScore);
		greMark.selectByValue("GRE--300--4");
		Thread.sleep(3000);
		
		// 4) Max 10 Lakhs under 1st year Total fees, USA under countries
		driver.findElementByXPath("//p[text()='Max 10 Lakhs']").click();
		Thread.sleep(3000);

		driver.findElementByXPath("//div[@class='refine-col flLt last']/div//ul/li[4]//span").click();
		Thread.sleep(3000);
		
		// 5) Select Sort By: Low to high 1st year total fees
		Select sortBy = new Select(driver.findElementByXPath("//select[@id='categorySorter']"));
		Thread.sleep(3000);
		sortBy.selectByValue("fees_ASC");
		Thread.sleep(3000);

		//  6) Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation

		List<WebElement> totNoOfColleges = driver.findElementsByXPath("//div[@class='tuple-detail']");
		int noOfEntries = totNoOfColleges.size();

		for(int i=0;i<noOfEntries;i++) {

			WebElement feesEle = driver.findElementByXPath("((//div[@class='tuple-detail'])["+(i+1)+"]//div[@class='detail-col flLt'])[1]/p");
			WebElement pubUnivEle = driver.findElementByXPath("((//div[@class='tuple-detail'])["+(i+1)+"]//div[@class='detail-col flLt'])[3]/p[1]/span");
			WebElement scholarshipEle = driver.findElementByXPath("((//div[@class='tuple-detail'])["+(i+1)+"]//div[@class='detail-col flLt'])[3]/p[2]/span");
			WebElement accomodationEle = driver.findElementByXPath("((//div[@class='tuple-detail'])["+(i+1)+"]//div[@class='detail-col flLt'])[3]/p[3]/span");

			String textFeesAll = feesEle.getText();
			String textFees = textFeesAll.replaceAll("[a-zA-Z]", "");
			double fees = Double.parseDouble(textFees);
			System.out.println(fees);

			String pubUniv = pubUnivEle.getAttribute("class");
			System.out.println(pubUniv);
			String scholarship = scholarshipEle.getAttribute("class");
			System.out.println(scholarship);
			String accomodation = accomodationEle.getAttribute("class");
			System.out.println(accomodation);

			if(pubUniv.equalsIgnoreCase("tick-mark") && scholarship.equalsIgnoreCase("tick-mark") && accomodation.equalsIgnoreCase("tick-mark")) {

				if(fees<max) {					
					max=fees;
					j=i;
				}				
			}
			
		}

		driver.findElementByXPath("((//div[@class='tuple-detail'])["+(j)+"]/following::p[text()='Add to compare'])[1]").click();
		Thread.sleep(3000);
		
		// 7) Select the first college under Compare with similar colleges 
		driver.findElementByXPath("(//ul[@class='sticky-suggestion-list']/li/a)[1]").click();
		Thread.sleep(3000);
		
		// 8) Click on Compare College>
		driver.findElementByXPath("//a[@class='button-style']/strong[text()='Compare Colleges >']").click();
		Thread.sleep(3000);
		
		// 9) Select When to Study as 2021
		driver.findElementByXPath("(//div[@class='columns'])[2]/label/p").click();
		Thread.sleep(3000);
		
		// 10) Select Preferred Countries as USA
		driver.findElementByXPath("//div[@class='sp-frm selectCountryField signup-fld invalid ']").click();
		Thread.sleep(3000);
		
		driver.findElementByXPath("(//li[@class='ctry-row'])[2]/div[1]/label").click();
		Thread.sleep(3000);
		
		driver.findElementByXPath("//a[@class='ok-btn']").click();
		Thread.sleep(3000);
		
		// 11) Select Level of Study as Masters
		driver.findElementByXPath("(//div[@class='columns'])[5]/label/p").click();
		Thread.sleep(3000);
		
		// 12) Select Preferred Course as MS
		driver.findElementByXPath("//div[@class='sp-frm selectField signup-fld invalid']").click();
		Thread.sleep(3000);
		
		driver.findElementByXPath("//div[@class='city-lr prefCourse']/ul/li[2]").click();
		Thread.sleep(3000);
		
		// 13) Select Specialization as "Computer Science & Engineering"
		driver.findElementByXPath("//div[@class='signUp-child-wrap specDiv']/div").click();
		Thread.sleep(3000);
				
		driver.findElementByXPath("//li[text()='Computer Science & Engineering']").click();
		Thread.sleep(3000);
		
		// 14) Click on Sign Up
		driver.findElementByXPath("//a[@class='button-style big-button ']").click();
		Thread.sleep(3000);
		
		// 15) Print all the warning messages displayed on the screen for missed mandatory fields
		List<WebElement> errorMessages = driver.findElementsByXPath("//div[contains(@id,'error')]/div[@class='helper-text' and contains(text(),'Please')]");
		
		for (WebElement ele : errorMessages) {
			
			String errMsg = ele.getText();
			if(errMsg!="") {
				System.out.println(errMsg);
			}
			
		}
		
		driver.close();
		
				
	}

}
/*30/04/2020
==========
1) Go to https://studyabroad.shiksha.com/
2) Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges
3) Select GRE under Exam Accepted and Score 300 & Below 
4) Max 10 Lakhs under 1st year Total fees, USA under countries
5) Select Sort By: Low to high 1st year total fees
6) Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation
7) Select the first college under Compare with similar colleges 
8) Click on Compare College>
9) Select When to Study as 2021
10) Select Preferred Countries as USA
11) Select Level of Study as Masters
12) Select Preferred Course as MS
13) Select Specialization as "Computer Science & Engineering"
14) Click on Sign Up
15) Print all the warning messages displayed on the screen for missed mandatory fields*/