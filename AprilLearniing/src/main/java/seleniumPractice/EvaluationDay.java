package seleniumPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EvaluationDay {
	
	public static void main(String[] args) throws InterruptedException {
		// 1) Go to https://www.ajio.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		op.merge(cap);
		ChromeDriver driver = new ChromeDriver(op);
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// 2) Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElementByXPath("//input[@name='searchVal']").sendKeys("Bags");
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//a[@class='search-suggestionList  ']/span[text()='Bags in '])[1]").click();
		Thread.sleep(2000);
		
		// 3) Click on five grid and Select SORT BY as "What's New"
		driver.findElementByXPath("//div[@class='five-grid']").click();
		Thread.sleep(2000);
		
		WebElement sortBy = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Thread.sleep(2000);
		
		Select sortByValue = new Select(sortBy);
		sortByValue.selectByValue("newn");
		Thread.sleep(2000);
		
		// 4) Enter Price Range Min as 2000 and Max as 5000
		driver.findElementByXPath("//span[text()='price']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//input[@name='minPrice']").sendKeys("2500");
		Thread.sleep(2000);
		
		driver.findElementByXPath("//input[@name='maxPrice']").sendKeys("5000");
		Thread.sleep(2000);
		
		driver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
		Thread.sleep(2000);
		
		// 5) Click on the product "Puma Ferrari LS Shoulder Bag"
		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
		Thread.sleep(2000);
		
		Set<String> winHan = driver.getWindowHandles();
		List<String> li = new ArrayList<String>();
		li.addAll(winHan);
		
		driver.switchTo().window(li.get(1));
		
		// 6) Verify the Coupon code for the price above 2690 is applicable for your product, 
		//if applicable the get the Coupon Code and Calculate the discount price for the coupon
		String orgPriceVal = driver.findElementByXPath("//div[@class='prod-sp']").getText();
		String orgPrice = orgPriceVal.replaceAll("\\D", "");
		int orgCost = Integer.parseInt(orgPrice);
		System.out.println(orgCost);
		
		String disPriceVal = driver.findElementByXPath("//div[@class='promo-discounted-price']/span").getText();
		String disPrice = disPriceVal.replaceAll("\\D", "");
		int disCost = Integer.parseInt(disPrice);
		System.out.println(disCost);
		
		int save = orgCost - disCost;
		System.out.println(save);
		
		if(orgCost>2690) {
			String promoCode = driver.findElementByXPath("//div[@class='promo-title']").getText();
			System.out.println("Coupon Code is :"+promoCode);
		}
		
		// 7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
		driver.findElementByXPath("//span[@class='edd-pincode-msg-details edd-pincode-msg-details-pointer edd-pincode-msg-details-text-color']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//input[@class='edd-pincode-modal-text']").sendKeys("560043");
		Thread.sleep(2000);
		
		driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']").click();
		Thread.sleep(2000);
		
		String expectedDelivery = driver.findElementByXPath("//span[@class='edd-message-success-details-highlighted']").getText();
		System.out.println(expectedDelivery);
		
		// 8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		driver.findElementByXPath("//div[@class='other-info-toggle']").click();
		Thread.sleep(2000);
				
		String addressPhoneEmail = driver.findElementByXPath("(//span[@class='other-info'])[6]").getText();
		System.out.println(addressPhoneEmail);
		Thread.sleep(2000);
		
		// 9) Click on ADD TO BAG and then GO TO BAG
		driver.findElementByXPath("//div[@class='btn-gold']").click();
		Thread.sleep(8000);
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement goToBag = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='btn-cart']")));
		goToBag.click();
		Thread.sleep(2000);
		
		// 10) Check the Order Total before apply coupon
		String orderTotalTextEle = driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
		String orderTotalText = orderTotalTextEle.replaceAll("\\D", "");
		String orderTotalTxt = orderTotalText.substring(0,4);
		int orderTotal = Integer.parseInt(orderTotalTxt);
		System.out.println(orderTotal);
		
		// 11) Enter Coupon Code and Click Apply
		driver.findElementByXPath("//input[@class='coupon-code-input-vhr-not-apld  ']").sendKeys("EPIC");
		Thread.sleep(2000);
		
		driver.findElementByXPath("//button[@class='rilrtl-button button apply-button ']").click();
		Thread.sleep(2000);
		
		// 12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary 
		//and the matches the amount calculated in Product details
		String discountPriceTextEle = driver.findElementByXPath("(//span[@class='price-value discount-price'])[2]").getText();
		String discountPriceTextEl = discountPriceTextEle.replaceAll("[a-zA-z.]", "");
		String discountPriceText = discountPriceTextEl.replaceAll("\\s", "");
		String discountPriceTxt = discountPriceText.substring(0, 3);
		int discountPrice = Integer.parseInt(discountPriceTxt)+1;
		System.out.println(discountPrice);
		
		if(discountPrice==disCost) {
			System.out.println("Coupon Savings Amount matches the amount calculated in Product details");
		}
		
		// 13) Click on Delete and Delete the item from Bag
		driver.findElementByXPath("//div[@class='delete-btn']").click();
		Thread.sleep(2000);
				
		driver.findElementByXPath("(//div[@class='delete-btn'])[2]").click();
		Thread.sleep(2000);
		
		// 14) Close all the browsers
		driver.quit();
		
	}

}
