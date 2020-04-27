package seleniumPractice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day8PepperFry {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		// 1) Go to https://www.pepperfry.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(op);
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement popUp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='regPopUp']/a")));
		popUp.click();
		
		// 2) MouseHover on Furniture and click Office Chairs under Chairs
		WebElement furniture = driver.findElementByXPath("//div[@id='menu_wrapper']/ul/li[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(furniture).perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//a[text()='Office Chairs']").click();
		
		// 3) click Executive Chairs
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();
		Thread.sleep(2000);
		
		// 4) Change the minimum Height as 50 in under Dimensions
		WebElement heightMin = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]");
		heightMin.clear();
		heightMin.sendKeys("50");
		Thread.sleep(5000);
		
		// 5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
		WebElement wishListChair = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-productname='Poise Executive Chair in Black Colour']")));
		wishListChair.click();
		Thread.sleep(5000);
		
		// 6) Mouseover on Homeware and Click Pressure Cookers under Cookware
		WebElement homeWare = driver.findElementByXPath("(//a[text()='Homeware'])[1]");
		builder.moveToElement(homeWare).perform();
		Thread.sleep(3000);
		WebElement pressureCooker = driver.findElementByXPath("(//a[text()='Pressure Cookers'])[1]");
		pressureCooker.click();
		Thread.sleep(3000);
		
		// 7) Select Prestige as Brand
		driver.findElementByXPath("//li[@data-search='Prestige']").click();
		Thread.sleep(3000);
		
		// 8) Select Capacity as 1-3 Ltr
		driver.findElementByXPath("//li[@data-search='1 Ltr - 3 Ltr']/label").click();
		Thread.sleep(3000);
		
		// 9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		WebElement wishListCooker = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']")));
		wishListCooker.click();
		Thread.sleep(5000);
		
		// 10) Verify the number of items in Wishlist
		WebElement wishListCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='count_alert'])[2]")));
		System.out.println(wishListCount.getText());
		Thread.sleep(5000);
		
		// 11) Navigate to Wishlist
		WebElement wishList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='wishlist_bar']/a")));
		wishList.click();
		Thread.sleep(5000);
		
		// 12) Move Pressure Cooker only to Cart from Wishlist
		driver.findElementByXPath("//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']/following::div[@class='item_cta']/div/a[1]").click();
		Thread.sleep(5000);
		
		// 13) Check for the availability for Pincode 600128
		driver.findElementByXPath("//input[@class='srvc_pin_text']").sendKeys("600128");
		driver.findElementByXPath("//a[@class='check_available']").click();
		
		// 14) Click Proceed to Pay Securely and 15) Click Proceed to Pay 
		driver.findElementByXPath("//a[@class='proceed_cta']").click();
		Thread.sleep(5000);
		
		// 16) Capture the screenshot of the item under Order Item
		File source = driver.findElementByXPath("//a[@class='ck-pro-img-link']/img").getScreenshotAs(OutputType.FILE);
		File destination = new File("./screenshot/screenshot1.png");
		FileUtils.copyFile(source, destination);
		
		// 17) Close the browser
		driver.close();
		
	}

}
/*
23/04/2020
============
1) Go to https://www.pepperfry.com/
2) Mouseover on Furniture and click Office Chairs under Chairs
3) click Executive Chairs
4) Change the minimum Height as 50 in under Dimensions
5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
6) Mouseover on Homeware and Click Pressure Cookers under Cookware
7) Select Prestige as Brand
8) Select Capacity as 1-3 Ltr
9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
10) Verify the number of items in Wishlist
11) Navigate to Wishlist
12) Move Pressure Cooker only to Cart from Wishlist
13) Check for the availability for Pincode 600128
14) Click Proceed to Pay Securely
15 Click Proceed to Pay
16) Capture the screenshot of the item under Order Item
17) Close the browser6
*/