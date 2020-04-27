package seleniumPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Day2Nykaa {
	
	public static void main(String[] args) throws InterruptedException {
		
		// 1) Go to https://www.nykaa.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		//disable notifications further
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");

		//Launch URL
		WebDriver driver = new ChromeDriver(op);
		driver.get("https://www.nykaa.com/");

		//Maximize Window and Add implicit wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// 2) MouseHover on Brands and MouseHover on Popular
		Actions builder = new Actions(driver);		
		WebElement brands = driver.findElement(By.xpath("//*[text()='brands']"));
		builder.moveToElement(brands).perform();
		WebElement popular = driver.findElement(By.xpath("//*[text()='Popular']"));
		builder.moveToElement(popular).perform();
		
		// 3) Click L'Oreal Paris
		driver.findElement(By.xpath("//a[@href='/brands/loreal-paris/c/595?eq=desktop']")).click();
		
		// 4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> winHan = driver.getWindowHandles();
		List<String> l1 = new ArrayList<String>();		
		l1.addAll(winHan);
		
		driver.switchTo().window(l1.get(1));
		
		String win2Title = driver.getTitle();
		if(win2Title.contains("L'Oreal Paris")) {
			System.out.println("2nd Window Titile Contains L'Oreal Paris");
		}
		// 5) Click sort By and select customer top rated
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
		driver.findElement(By.xpath("//input[@id='3']/following::div")).click();
		Thread.sleep(3000);
		
		// 6) Click Category and click Shampoo
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Shampoo')]/following-sibling::div")).click();
		Thread.sleep(3000);
		
		// 7) check whether the Filter is applied with Shampoo
		String filterText = driver.findElement(By.xpath("//ul[@class='pull-left applied-filter-lists']/li")).getText();
		if(filterText.contains("Shampoo")) {
			System.out.println("Filter is Applied with Shampoo");
		}
		
		// 8) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("(//div[@class='m-content__product-list__title']/h2)[4]")).click();
		
		// 9) GO to the new window and select size as 175ml
		Set<String> winHan1 = driver.getWindowHandles();
		List<String> l2 = new ArrayList<String>();		
		l2.addAll(winHan1);
		
		driver.switchTo().window(l2.get(2));
		
		driver.findElement(By.xpath("//span[@class='size-pallets' and text()='175ml']")).click();
		
		// 10) Print the MRP of the product
		String mrpPrice = driver.findElement(By.xpath("(//span[@class='post-card__content-price-offer'])[1]")).getText();
		System.out.println(mrpPrice);
		
		// 11) Click on ADD to BAG
		driver.findElement(By.xpath("(//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  '])[1]")).click();
		Thread.sleep(3000);
		
		// 12) Go to Shopping Bag
		driver.findElement(By.xpath("//div[@class='AddToBagbox']")).click();
		Thread.sleep(3000);
		
		// 13) Print the Grand Total amount
		driver.findElement(By.xpath("//div[@class='sticky-bottom proceed-cart-btn']/div/div/div")).getText();
		Thread.sleep(3000);
		
		// 14) Click Proceed
		driver.findElement(By.xpath("//div[@class='sticky-bottom proceed-cart-btn']/div/div[2]/button")).click();
		Thread.sleep(3000);
		
		// 15) Click on Continue as Guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		Thread.sleep(3000);
		
		// 16) Print the warning message (delay in shipment)
		String errorMessage = driver.findElement(By.xpath("//div[@class='message']")).getText();
		System.out.println(errorMessage);
		
		// 17) Close all windows
		driver.quit();
		
	}
	

}

/*
1) Go to https://www.nykaa.com/
2) Mouseover on Brands and Mouseover on Popular
3) Click L'Oreal Paris
4) Go to the newly opened window and check the title contains L'Oreal Paris
5) Click sort By and select customer top rated
6) Click Category and click Shampoo
7) check whether the Filter is applied with Shampoo
8) Click on L'Oreal Paris Colour Protect Shampoo
9) GO to the new window and select size as 175ml
10) Print the MRP of the product
11) Click on ADD to BAG
12) Go to Shopping Bag 
13) Print the Grand Total amount
14) Click Proceed
15) Click on Continue as Guest
16) Print the warning message (delay in shipment)
17) Close all windows
*/