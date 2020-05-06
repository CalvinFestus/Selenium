package seleniumPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Day15Airbnb {

	public static void main(String[] args) {

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

		driver.findElementById("bigsearch-query-attached-query").sendKeys("Coorg");

		driver.findElementByXPath("//ul[@role='listbox']/li[1]/div[2]").click();

		driver.findElementByXPath("//td[contains(@aria-label,'June 1, 2020')]").click();

		driver.findElementByXPath("//td[contains(@aria-label,'June 5, 2020')]").click();

		driver.findElementByXPath("//div[text()='Guests']").click();

		for(int i=0;i<3;i++) {

			driver.findElementByXPath("(//button[@type='button'])[5]").click();

		}

		for(int i=0;i<3;i++) {

			driver.findElementByXPath("(//button[@type='button'])[7]").click();

		}

		driver.findElementByXPath("//button[@type='submit']").click();

		driver.findElementByXPath("(//span[text()='Cancellation flexibility'])[1]").click();

		driver.findElementByXPath("//button[@id='filterItem-switch-flexible_cancellation-true']").click();

		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();

		driver.findElementByXPath("(//span[text()='Type of place'])[1]").click();

		driver.findElementByXPath("//div[text()='Entire place']").click();

		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();

		driver.findElementByXPath("(//span[text()='Price'])[1]").click();

		driver.findElementByXPath("//input[@id='price_filter_min']").clear();
		driver.findElementByXPath("//input[@id='price_filter_min']").sendKeys("3000");

		driver.findElementByXPath("//input[@id='price_filter_max']").clear();
		driver.findElementByXPath("//input[@id='price_filter_max']").sendKeys("5000");

		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();

		driver.findElementByXPath("(//span[text()='More filters'])[1]").click();

		for(int i=0;i<3;i++) {

			driver.findElementByXPath("(//button[@aria-label='increase value'])[2]").click();

		}

		for(int i=0;i<3;i++) {

			driver.findElementByXPath("(//button[@aria-label='increase value'])[3]").click();

		}

		driver.findElementByXPath("//div[text()='Kitchen']").click();

		driver.findElementByXPath("//div[text()='Free parking on premises']").click();

		driver.findElementByXPath("//div[text()='House']").click();

		driver.findElementByXPath("//div[text()='English']").click();

		driver.findElementByXPath("(//button[@type='button'])[33]").click();

		driver.findElementByXPath("(//a[@aria-label='Prahari Nivas, the complete house'])[1]").click();

		Set<String> winHan = driver.getWindowHandles();
		List<String> li = new ArrayList<String>();
		li.addAll(winHan);

		driver.switchTo().window(li.get(1));

		driver.findElementByXPath("//a[contains(text(),'amenities')]").click();

		List<WebElement> amenities = driver.findElementsByXPath("//h4[text()='Not included']/following::del");

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
		
		if(guests.contains("9")) {

			System.out.println("Number of Guests are verified");
		}





	}

}
