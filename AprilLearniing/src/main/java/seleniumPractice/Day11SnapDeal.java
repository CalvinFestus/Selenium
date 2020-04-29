package seleniumPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Day11SnapDeal {
	
	public static void main(String[] args) throws InterruptedException {
		
		// 1) Go to https://www.snapdeal.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		op.merge(cap);
		ChromeDriver driver = new ChromeDriver(op);
		driver.get("https://snapdeal.com");
		driver.manage().window().maximize();
		
		// 2) Mouse Hover on Toys Kids Fashion and Click on Toys
		Actions builder = new Actions(driver);
		WebElement toys = driver.findElementByXPath("(//span[@class='catText'])[8]");
		builder.moveToElement(toys).perform();
		driver.findElementByXPath("//span[text()='Toys']").click();
		Thread.sleep(5000);
		
		// 3) Click Educational Toys in Toys and Games
		driver.findElementByXPath("//div[text()='Educational Toys']").click();
		Thread.sleep(5000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(5000);
		
		// 4) Click the Customer rating 4 star and up
		driver.findElementByXPath("//div[@data-name='avgRating']/div[1]").click();
		Thread.sleep(5000);
		
		// 5) Click the offer as 40-50
		driver.findElementByXPath("//div[@data-name='discount']/div[5]").click();
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(5000);
		
		// 6) Check the availability for the pincode
		driver.findElementByXPath("//input[@placeholder='Enter your pincode']").sendKeys("600126");
		Thread.sleep(5000);
		
		driver.findElementByXPath("//button[@class='pincode-check']").click();
		Thread.sleep(5000);
		
		// 7) Click the Quick View of the first product
		WebElement item1 = driver.findElementByXPath("(//div[@class='clearfix row-disc'])[1]/div");
		//builder.moveToElement(item1).click().perform();
		js.executeScript("arguments[0]. click();", item1);
		Thread.sleep(5000);
		
		// 8) Click on View Details
		driver.findElementByXPath("//a[@class=' btn btn-theme-secondary prodDetailBtn']").click();
		Thread.sleep(5000);
		
		// 9) Capture the Price of the Product and Delivery Charge
		String priceToyVal = driver.findElementByXPath("//span[@class='pdp-final-price']").getText();
		String priceToy = priceToyVal.replaceAll("\\D", "");
		int toyCost = Integer.parseInt(priceToy);
		System.out.println(toyCost);
		Thread.sleep(5000);
		
		String deliveryChargesVal = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		String deliveryCharges = deliveryChargesVal.replaceAll("\\D", "");
		int toyDeliChar = Integer.parseInt(deliveryCharges);
		System.out.println(toyDeliChar);
		Thread.sleep(5000);
		
		int totalCost = toyCost + toyDeliChar;
		System.out.println(totalCost);
		
		driver.findElementByXPath("//span[@class='intialtext' and text()='add to cart']").click();
		Thread.sleep(5000);
		
		String totCostText = driver.findElementByXPath("//div[@class='you-pay']/span").getText();
		String totCostInt = totCostText.replaceAll("\\D", "");
		int totCost = Integer.parseInt(totCostInt);
		System.out.println(totCost);
		Thread.sleep(5000);
		
		// 10) Validate the You Pay amount matches the sum of (price+deliver charge)
		if(totalCost==totCost) {
			System.out.println("Price Matches");
			
		}
		
		// 11) Search for Sanitizer
		driver.findElementByXPath("//input[@id='inputValEnter']").sendKeys("Sanitizer");
		Thread.sleep(5000);
		
		
		driver.findElementByXPath("//div[@class='first_menuitem suggestionList_menuitem']/a[1]").click();
		Thread.sleep(5000);
		
		// 12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"
		driver.findElementByXPath("//p[@title='BioAyurveda Neem Power  Hand Sanitizer 500 mL Pack of 1']").click();
		Thread.sleep(5000);
		
		Set<String> winHan = driver.getWindowHandles();
		List<String> l1 = new ArrayList<String>();
		l1.addAll(winHan);
		
		driver.switchTo().window(l1.get(1));
		Thread.sleep(5000);
		
		// 13) Capture the Price and Delivery Charge
		String priceSanVal = driver.findElementByXPath("//span[@class='pdp-final-price']").getText();
		String priceSan = priceSanVal.replaceAll("\\D", "");
		int sanCost = Integer.parseInt(priceSan);
		System.out.println(sanCost);
		Thread.sleep(5000);
		
		String deliveryChargesVal1 = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		String deliveryCharges1 = deliveryChargesVal1.replaceAll("\\D", "");
		int sanDeliChar = Integer.parseInt(deliveryCharges1);
		System.out.println(sanDeliChar);
		Thread.sleep(5000);
		
		int sanTot = sanCost+sanDeliChar;
		System.out.println(sanTot);
		
		// 14) Click on Add to Cart
		driver.findElementByXPath("(//span[text()='ADD TO CART'])[1]").click();
		Thread.sleep(5000);
		
		// 15) Click on Cart
		driver.findElementByXPath("//i[@class='sd-icon sd-icon-cart-icon-white-2']").click();
		Thread.sleep(5000);
		
		String buttonValue = driver.findElementByXPath("//input[@class='btn btn-xl rippleWhite cart-button']").getAttribute("value");
		String buttonValInt = buttonValue.replaceAll("\\D", "");
		int grandTotal = Integer.parseInt(buttonValInt);
		System.out.println(grandTotal);
		
		// 16) Validate the Proceed to Pay matches the total amount of both the products
		if(grandTotal==totalCost+sanTot) {
			System.out.println("Amount Matches Sucessfully");
		}
		
		// 17) Close all the windows
		driver.quit();
		
		
	}

}
