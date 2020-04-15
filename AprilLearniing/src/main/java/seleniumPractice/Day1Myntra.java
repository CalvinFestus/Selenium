package seleniumPractice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Day1Myntra {

	public static void main(String[] args) throws InterruptedException {


		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		//disable notifications further
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");

		//Launch URL
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");

		//Maximize Window and Add implicit wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//Mouse over the Women icon
		WebElement women = driver.findElement(By.xpath("(//div[@class='desktop-navLink'])[2]"));		
		Actions ac = new Actions(driver);
		ac.moveToElement(women).perform();

		//Click on Jackets and Coats option
		driver.findElement(By.linkText("Jackets & Coats")).click();

		//Get the total no of coats & Jackets
		String totalValue = driver.findElement(By.xpath("//*[@class='title-count']")).getText();
		String totalAll = totalValue.replaceAll("\\D", "");
		int total = Integer.parseInt(totalAll);

		//Get the Total no of coats
		String coatsValue = driver.findElement(By.xpath("(//*[@class='categories-num'])[1]")).getText();
		String coatsAll = coatsValue.replaceAll("\\D", "");
		int coats = Integer.parseInt(coatsAll);

		//Get the Total no of jackets separately
		String jacketsValue = driver.findElement(By.xpath("(//*[@class='categories-num'])[2]")).getText();
		String JacketsAll = jacketsValue.replaceAll("\\D", "");
		int jackets = Integer.parseInt(JacketsAll);

		//Sum the coats & jacket should match the total
		int sum = coats+jackets;

		System.out.println(total);
		System.out.println(sum);

		if(total==sum) {
			System.out.println("The sum of jackets and coats matches with the total.");
		}
		else
			System.out.println("Count not matched.");

		//Check coats box and click brand to open all brands
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[2]")).click();
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();

		//Type Mango and check the box displayed
		driver.findElement(By.xpath("//input[@placeholder='Search brand']")).sendKeys("Mango");
		driver.findElement(By.xpath("//label[@class=' common-customCheckbox']//div[1]")).click();

		//Close Pop-Up
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		Thread.sleep(5000);

		//List the brand of products displayed
		List<WebElement> brandList = driver.findElements(By.xpath("//h3[@class='product-brand']"));

		for (WebElement brandName : brandList) {
			String brand = brandName.getText();
			System.out.println("Brand Name:"+brand);
		}

		//Better discount Sort
		WebElement sort = driver.findElement(By.xpath("//div[@class='sort-sortBy']"));
		ac.moveToElement(sort).perform();
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();

		//Price of first displayed item
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='product-discountedPrice']"));
		String lowestPrice = price.get(0).getText();
		System.out.println("Price of the first displayed Item: "+lowestPrice);

		//MouseHover the size of first item
		WebElement size = driver.findElement(By.xpath("(//div[@class='product-productMetaInfo'])[1]"));
		ac.moveToElement(size).perform();

		//Click WishList 
		driver.findElement(By.xpath("//span[(@class='product-actionsButton product-wishlist product-prelaunchBtn')]")).click();

		//Close Browser
		driver.close();

	}

}

/*1) Open https://www.myntra.com/
2) Mouse over on WOMEN (Actions -> moveToElement
3) Click Jackets & Coats
4) Find the total count of item (top) -> getText -> String

	 String str = driver.findElementByClassName("title-count").getText();
	 split, 
	 String text = str.replaceAll("\\D","") -> String
	 Integer.parseInt(text) -> int

5) Validate the sum of categories count matches
6) Check Coats
7) Click + More option under BRAND	
8) Type MANGO and click checkbox
9) Close the pop-up x
10) Confirm all the Coats are of brand MANGO
    findElements (brand) -> List<WebElement> 
    foreach -> getText of each brand 
    compare > if(condition)
11) Sort by Better Discount
12) Find the price of first displayed item
     findElements (price) -> List<WebElement> 
     get(0) -> WebElement -> getText -> String -> int
13) Mouse over on size of the first item
14) Click on WishList Now
15) Close Browser*/
