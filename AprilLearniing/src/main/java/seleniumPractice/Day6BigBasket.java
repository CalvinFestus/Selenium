package seleniumPractice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day6BigBasket {
	
	public static void main(String[] args) throws InterruptedException {
		// 1) Go to https://www.bigbasket.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		op.merge(cap);
		ChromeDriver driver = new ChromeDriver(op);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver,15);
		Actions builder = new Actions(driver);
		
		
		driver.findElementByXPath("//span[@class='arrow-marker']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//i[@class='caret pull-right'])[1]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//input[@type='search'])[1]").sendKeys("Chennai");
		Thread.sleep(2000);
		driver.findElementByXPath("//a[@class='ui-select-choices-row-inner']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//button[@type='button'])[1]").click();
		Thread.sleep(2000);
		
		// 2) mouse over on  Shop by Category 
		WebElement shopByCategory = driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']");
		builder.moveToElement(shopByCategory).perform();
		Thread.sleep(2000);
		
		// 3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS 
		WebElement foodGrainsOilMasala = driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]");
		builder.moveToElement(foodGrainsOilMasala).perform();
		Thread.sleep(2000);
		
		WebElement riceAndRiceProducts = driver.findElementByXPath("(//a[text()='Rice & Rice Products'])[2]");
		builder.moveToElement(riceAndRiceProducts).perform();
		Thread.sleep(2000);
		
		// 4) Click on Boiled & Steam Rice
		WebElement boiledAndSteamRice = driver.findElementByXPath("(//a[text()='Boiled & Steam Rice'])[2]");
		builder.moveToElement(boiledAndSteamRice).click().perform();
		Thread.sleep(2000);
		
		// 5) Choose the Brand as bb Royal
		driver.findElementByXPath("(//span[text()='bb Royal'])[1]").click();
		Thread.sleep(2000);
		
		// 6) Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::button[@class='btn btn-default dropdown-toggle form-control'])[1]").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::ul[@class='dropdown-menu drop-select'])[1]/li[1]").click();
		Thread.sleep(2000);
		
		// 7) print the price of Rice
		String priceRice = driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::span[@class='discnt-price'])[1]").getText();
		Thread.sleep(2000);
		System.out.println(priceRice);
		String priceRiceText = priceRice.replaceAll("\\D", "");
		int priceRiceInt = Integer.parseInt(priceRiceText);
		
		// 8) Click Add button
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::button[@type='button'])[2]").click();
		
		// 9) Verify the success message displayed 
		String successMsg = driver.findElementByXPath("//div[@class='toast-title']").getText();
		if (successMsg.equalsIgnoreCase("Successfully added Ponni Boiled Rice - Super Premium 5 kg to the basket")) {
			System.out.println(successMsg);
			Thread.sleep(3000);
		} else {
			System.out.println("Wrong message displayed" + successMsg);
		}
		
		// 10) Type Dal in Search field and enter
		driver.findElementByXPath("//input[@id='input']").sendKeys("Dal", Keys.ENTER);
		Thread.sleep(2000);
		
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1200)");*/
		
		// 12) Go to Toor/Arhar Dal and select 2kg & set Qty 2 
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::button[@type='button'])[1]").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::ul/li[3])[1]").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::input[@type='text'])[1]").clear();
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::input[@type='text'])[1]").sendKeys("2");
		Thread.sleep(2000);
		
		// 13) Print the price of Dal
		WebElement priceDalEle = driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[@class='discnt-price'])[1]");
		String priceDal = priceDalEle.getText();
		System.out.println(priceDal);
		String priceDalText = priceDal.replaceAll("\\D", "");
		int priceDalInt = Integer.parseInt(priceDalText);
		Thread.sleep(2000);
		
		// 14) Click Add button
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::button[@type='button'])[2]").click();
		Thread.sleep(2000);
		
		// 15) Mouse hover on My Basket 
		WebElement basket = driver.findElementByXPath("//i[@class='icon svg-basket svg-header svg-basket-dim']");
		builder.moveToElement(basket).perform();
		Thread.sleep(2000);
		
		WebElement subTotalEle = driver.findElementByXPath("//p[text()='Sub Total : ']/span/span");
		
		// 16) Validate the Sub Total displayed for the selected items
		String subTotalText = subTotalEle.getText();
		System.out.println(subTotalText);
		String subTotal = subTotalText.replaceAll("\\D", "");
		int subTotalInt = Integer.parseInt(subTotal);
		
		if(subTotalInt==(priceDalInt+priceRiceInt)) {
			System.out.println("Sub Total is displayed correctly");
		}
		
		// 17) Reduce the Quantity of Dal as 1 
		driver.findElementByXPath("(//button[@qa='decQtyMB'])[2]").click();
		Thread.sleep(2000);
		
		// 18) Validate the Sub Total for the current items
		WebElement subTotalNew = driver.findElementByXPath("//p[text()='Sub Total : ']/span/span");
		String subTotalTextNew = subTotalNew.getText();
		System.out.println(subTotalTextNew);
		String subTotalNew1 = subTotalTextNew.replaceAll("\\D", "");
		int subTotalNew1Int = Integer.parseInt(subTotalNew1);
		
		int totPrice=0;
		
		List<WebElement> prices = driver.findElementsByXPath("//span[@qa='priceMB']");
		
		for(int i=0;i<prices.size();i++) {
			WebElement priceEle = prices.get(i);
			String priceText = priceEle.getText();
			String priceTextNew = priceText.replaceAll("\\D", "");
			int priceInt = Integer.parseInt(priceTextNew);
			totPrice = priceInt + totPrice;
		}
		
		if(subTotalNew1Int==totPrice) {
			System.out.println("Sub Total is Displayed Correctly");
		}
		
		// 19) Close the Browser
		driver.quit();
	}

}
