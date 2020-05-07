package seleniumPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Day15Airbnb {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		op.merge(cap);
		ChromeDriver driver = new ChromeDriver(op);
		driver.get("https://www.airbnb.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElementByXPath("//button[@class='optanon-allow-all accept-cookies-button']").click();
		Thread.sleep(2000);
		
		driver.findElementById("bigsearch-query-attached-query").sendKeys("Coorg");
		Thread.sleep(2000);

		driver.findElementByXPath("//ul[@role='listbox']/li[1]/div[2]").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//td[contains(@aria-label,'June 1, 2020')]").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//td[contains(@aria-label,'June 5, 2020')]").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//div[text()='Guests']").click();
		Thread.sleep(2000);

		for(int i=0;i<3;i++) {

			driver.findElementByXPath("(//button[@aria-label='increase value'])[1]").click();
			Thread.sleep(2000);

		}

		for(int i=0;i<3;i++) {

			driver.findElementByXPath("(//button[@aria-label='increase value'])[2]").click();
			Thread.sleep(2000);

		}

		driver.findElementByXPath("//button[@type='submit']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("(//span[text()='Cancellation flexibility'])[1]").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//button[@id='filterItem-switch-flexible_cancellation-true']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("(//span[text()='Type of place'])[1]").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//div[text()='Entire place']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("(//span[text()='Price'])[1]").click();
		Thread.sleep(2000);
		
		for(int i=0;i<3;i++) {
			driver.findElementByXPath("//input[@id='price_filter_min']").sendKeys(Keys.BACK_SPACE);
			Thread.sleep(2000);
		}
		
		driver.findElementByXPath("//input[@id='price_filter_min']").sendKeys("3000");
		Thread.sleep(2000);
		
		for(int i=0;i<7;i++) {
			driver.findElementByXPath("//input[@id='price_filter_max']").sendKeys(Keys.BACK_SPACE);
			Thread.sleep(2000);
		}
		
		driver.findElementByXPath("//input[@id='price_filter_max']").sendKeys("5000");
		Thread.sleep(2000);

		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("(//span[text()='More filters'])[1]").click();
		Thread.sleep(2000);

		for(int i=0;i<3;i++) {

			driver.findElementByXPath("(//button[@aria-label='increase value'])[2]").click();
			Thread.sleep(2000);

		}

		for(int i=0;i<3;i++) {

			driver.findElementByXPath("(//button[@aria-label='increase value'])[3]").click();
			Thread.sleep(2000);

		}

		driver.findElementByXPath("//div[text()='Kitchen']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//div[text()='Free parking on premises']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//div[text()='House']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//div[text()='English']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//button[contains(text(),'Show')]").click();
		Thread.sleep(2000);

		driver.findElementByXPath("(//a[@aria-label='Prahari Nivas, the complete house'])[1]").click();
		Thread.sleep(2000);

		Set<String> winHan = driver.getWindowHandles();
		List<String> li = new ArrayList<String>();
		li.addAll(winHan);

		driver.switchTo().window(li.get(1));

		driver.findElementByXPath("//a[contains(text(),'amenities')]").click();
		Thread.sleep(2000);

		List<WebElement> amenities = driver.findElementsByXPath("//*[text()='Not included']/following::del");
		System.out.println("Amenities Not Included are:");
		for(int i=0;i<amenities.size();i++) {

			String notIncludedAmenities = amenities.get(i).getText();
			System.out.println(notIncludedAmenities);

		}

		driver.findElementByXPath("//button[@aria-label='Close']").click();

		String checkIn = driver.findElementByXPath("(//div[text()='Check-in'])[1]/following::div[1]").getText();

		if(checkIn.equals("06/01/2020")) {

			System.out.println("Check In Date is verified");
		}

		String checkOut = driver.findElementByXPath("(//div[text()='Checkout'])[1]/following::div[1]").getText();

		if(checkOut.equals("06/05/2020")) {

			System.out.println("Check Out Date is verified");
		}
		
		String guests = driver.findElementByXPath("//div[text()='Guests']/following::div[2]").getText();
		
		if(guests.contains("6")) {

			System.out.println("Number of Guests are verified");
		}


		List<WebElement> sleepArrangements = driver.findElementsByXPath("//*[text()='Sleeping arrangements']/following::div[contains(text(),'Bedroom')]");
		System.out.println("Sleeping arrangements");
		for(int i=0;i<sleepArrangements.size();i++) {
			
			WebElement bedRoom = driver.findElementByXPath("(//*[text()='Sleeping arrangements']/following::div[contains(text(),'Bedroom')])["+(i+1)+"]");
			WebElement noOfCot = driver.findElementByXPath("(//*[text()='Sleeping arrangements']/following::div[contains(text(),'Bedroom')])["+(i+1)+"]/following::div[1]");
			System.out.println(bedRoom.getText()+" has "+noOfCot.getText());
			
			if((i+1)>3) {
				
			}
			
		}
		
		driver.quit();


	}

}
