package seleniumPractice;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class Day4HP {

	public static void main(String[] args) throws InterruptedException {
		// 1) Go to https://store.hp.com/in-en/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();

		// 2) Mouse over on Laptops menu and click on Pavilion
		Actions builder = new Actions(driver);
		WebElement laptop = driver.findElementByXPath("(//span[text()='Laptops'])[1]");
		builder.moveToElement(laptop).perform();
		driver.findElementByXPath("(//span[text()='Pavilion'])[1]").click();
		Thread.sleep(2000);

		// 3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		driver.findElementByXPath("(//dl[@role='tablist']/dt//span[text()='Processor'])[2]").click();
		Thread.sleep(2000);

		// 4) Hard Drive Capacity -->More than 1TB
		driver.findElementByXPath("//dd[@class='filter-options-item allow active']/div/div/ol/li[1]").click();
		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(5000);

		// 5) Select Sort By: Price: Low to High
		driver.findElementByXPath("(//li[@class='item']//span[text()='More than 1 TB'])[1]").click();
		Thread.sleep(2000);

		WebElement sortBy = driver.findElementByXPath("(//select[@id='sorter'])[1]");
		Select sort = new Select(sortBy);
		sort.selectByValue("price_asc");
		Thread.sleep(2000);

		// 6) Print the First resulting Product Name and Price
		String lapName = driver.findElementByXPath("(//div[@class='product details product-item-details'])[1]//a").getText();
		System.out.println(lapName);
		Thread.sleep(2000);

		String lapPriceText = driver.findElementByXPath("(//span[@class='price'])[2]").getText();
		String lapPriceVal = lapPriceText.replaceAll("\\D", "");
		double lapPrice = Double.parseDouble(lapPriceVal);
		System.out.println(lapPrice);
		Thread.sleep(2000);


		// 7) Click on Add to Cart
		driver.findElementByXPath("(//span[text()='Add To Cart'])[1]").click();
		Thread.sleep(2000);
		// 8) Click on Shopping Cart icon --> Click on View and Edit Cart
		driver.findElementByXPath("//a[@class='action showcart']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//span[text()='View and edit cart']").click();
		Thread.sleep(2000);

		// 9) Check the Shipping Option --> Check availability at Pincode
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("600126");
		driver.findElementByXPath("//button[@class='primary standard_delivery-button']").click();
		Thread.sleep(2000);

		// 10) Verify the order Total against the product price
		String actLapPriceText = driver.findElementByXPath("//strong/span[@class='price']").getText();
		String actLapPriceVal = actLapPriceText.replaceAll("\\D", "");
		double actLapPrice = Double.parseDouble(actLapPriceVal);
		System.out.println(actLapPrice);

		// 11) Proceed to Checkout if Order Total and Product Price matches
		if(actLapPrice==lapPrice) {
			driver.findElementByXPath("(//span[text()='Proceed to Checkout'])[1]").click();
			Thread.sleep(2000);

			// 12) Click on Place Order
			driver.findElementByXPath("(//span[text()='Place Order'])[3]").click();
			Thread.sleep(2000);

			// 13) Capture the Error message and Print
			String errorMessage = driver.findElementByXPath("//div[@id='customer-email-error']").getText();
			System.out.println(errorMessage);

		}

		// 14) Close Browser
		driver.close();
	}

}
/*driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
		Thread.sleep(2000);*/

