package seleniumPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Day7Honda {
	
	public static void main(String[] args) throws InterruptedException {
		
		// 1) Go to https://www.honda2wheelersindia.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(op);
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().window().maximize();
		
		// 2) Click on scooters and click dio
		driver.findElementByXPath("//button[@class='close']").click();
		driver.findElementByXPath("//a[text()='Scooter']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//a[@href='/dio-BS-VI/']").click();
		
		// 3) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		Thread.sleep(5000);
		
		Actions builder = new Actions(driver);
		WebElement engine = driver.findElementByXPath("(//li[@class='specificationsLi wow bounceInLeft'])[2]");
		builder.moveToElement(engine).perform();
		
		// 4) Get Displacement value
		String displacement = driver.findElementByXPath("(//ul[@class='tab_content'])[2]/li[3]/span[2]").getText();
		System.out.println(displacement);
		double dispValue = Double.parseDouble(displacement.replaceAll("[a-zA-Z]", ""));
		System.out.println(dispValue);
		
		// 5) Go to Scooters and click Activa 125
		driver.findElementByXPath("//a[text()='Scooter']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//a[@href='/activa125-BS-VI/']").click();
		
		// 6) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		Thread.sleep(5000);
		
		WebElement engine1 = driver.findElementByXPath("(//li[@class='specificationsLi'])[4]");
		builder.moveToElement(engine1).perform();
		
		// 7) Get Displacement value
		String displacement2 = driver.findElementByXPath("(//ul[@class='tab_content'])[2]/li[3]/span[2]").getText();
		System.out.println(displacement2);
		double dispValue2 = Double.parseDouble(displacement2.replaceAll("[a-zA-Z]", ""));
		System.out.println(dispValue2);
		
		// 8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		if(dispValue>dispValue2) {
			System.out.println("Displacement Value of Dio is: "+dispValue);
		}
		else {
			System.out.println("Displacement Value of Activa is: "+dispValue2);
		}
		
		// 9) Click FAQ from Menu
		driver.findElementByXPath("//ul[@class='nav navbar-nav']/li[9]").click();
		Thread.sleep(5000);
		
		// 10) Click Activa 125 BS-VI under Browse By Product
		driver.findElementByXPath("//h4[text()='Scooter']/following::a[3]").click();
		Thread.sleep(5000);
		
		// 11) Click  Vehicle Price 
		driver.findElementByXPath("//ul[@class='nav nav-pills tabb-design  ']/li[6]").click();
		Thread.sleep(5000);
		
		// 12) Make sure Activa 125 BS-VI selected and click submit
		driver.findElementByXPath("//button[@id='submit6']").click();
		Thread.sleep(5000);
		
		String bikeName = driver.findElementByXPath("//table[@id='tblPriceMasterFilters']/tbody/tr/td[2]").getText();
		if(bikeName.contains("Activa 125 BS-VI")) {
			System.out.println("Bike selected is Activa 125 BS-VI");
		}
		
		// 13) click the price link
		driver.findElementByXPath("//table[@id='tblPriceMasterFilters']/tbody/tr/td[3]").click();
		Thread.sleep(5000);
		
		// 14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> l1 = new ArrayList<String>();
		l1.addAll(windowHandles);
		Thread.sleep(5000);
		
		driver.switchTo().window(l1.get(1));
		Thread.sleep(5000);
		
		WebElement selectState = driver.findElementByXPath("//select[@id='StateID']");
		Select state = new Select(selectState);
		state.selectByVisibleText("Tamil Nadu");
		Thread.sleep(5000);
		
		WebElement selectCity = driver.findElementByXPath("//select[@id='CityID']");
		Select city = new Select(selectCity);
		city.selectByValue("1524");
		Thread.sleep(5000);
		
		// 15) Click Search
		driver.findElementByXPath("//button[text()='Search']").click();
		
		// 16) Print all the 3 models and their prices
		WebElement table = driver.findElementByXPath("//table[@id='gvshow']");
		
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		
		for(int i=0;i<allRows.size();i++) {
			List<WebElement> allCols = allRows.get(i).findElements(By.tagName("td"));
			for(int j=0;j<allCols.size();j++) {
				String colValues = allCols.get(j).getText();
				System.out.println(colValues);
			}
		}
		// 17) Close the Browser
		driver.quit();
	}

}
