package seleniumPractice;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class EvaluationDay2 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		// 1) Go to https://azure.microsoft.com/en-in/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(op);
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// 2) Click on Pricing
		driver.findElementByXPath("(//a[@title='Pricing'])[1]").click();
		
		// 3) Click on Pricing Calculator
		driver.findElementByXPath("(//a[@data-event='global-navigation-secondarynav-clicked-topmenu'])[2]").click();
		
		// 4) Click on Containers
		driver.findElementByXPath("//button[@value='containers']").click();
		
		// 5) Select Container Instances
		driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();
		
		// 6) Click on Container Instance Added View
		driver.findElementByXPath("//a[@id='new-module-loc']").click();
		
		// 7) Select Region as "South India"
		WebElement regionEle = driver.findElementByXPath("//select[@name='region']");		
		Select region = new Select(regionEle);
		region.selectByValue("south-india");
		
		// 8) Set the Duration as 180000 seconds
		driver.findElementByXPath("//input[@aria-label='Seconds']").clear();
		driver.findElementByXPath("//input[@aria-label='Seconds']").sendKeys(Keys.BACK_SPACE);
		Thread.sleep(5000);
		driver.findElementByXPath("//input[@aria-label='Seconds']").sendKeys("180000");

		// 9) Select the Memory as 4GB
		WebElement memoryEle = driver.findElementByXPath("//select[@aria-label='Memory']");
		Select memory = new Select(memoryEle); 
		memory.selectByValue("4");
		
		// 10) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//button[@id='devtest-toggler']").click();
		
		// 11) Select Indian Rupee  as currency
		WebElement currencyEle = driver.findElementByXPath("(//select[@aria-label='Currency'])[1]");
		Select currency = new Select(currencyEle);
		currency.selectByValue("INR");
		
		// 12) Print the Estimated monthly price
		String monthlyCost = driver.findElementByXPath("(//span[@class='numeric'])[4]").getText();
		System.out.println(monthlyCost);
		
		// 13) Click on Export to download the estimate as excel
		driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
		Thread.sleep(5000);
		
		// 14) Verify the downloded file in the local folder
		File  expEst = new File("C:\\Users\\hp\\Downloads\\ExportedEstimate.xlsx");
		
		if(expEst.exists()) {
			System.out.println("File 1 Exists");
		}

		// 15) Navigate to Example Scenarios and Select CI/CD for Containers
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");

		
		WebElement exampleScenarios = driver.findElementByXPath("//li[@id='solution-architectures-picker']");		
		exampleScenarios.click();

		driver.findElementByXPath("//button[@title='CI/CD for Containers']").click();
		
		// 16) Click Add to Estimate
		driver.findElementByXPath("//button[text()='Add to estimate']").click();
		Thread.sleep(5000);
		
		// 17) Change the Currency as Indian Rupee
		driver.findElementByXPath("(//select[@aria-label='Currency'])[1]").click();
		Thread.sleep(2000);

		driver.findElementByXPath("(//option[@value='INR'])[1]").click();
		Thread.sleep(2000);
		
		// 18) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//button[@id='devtest-toggler']").click();
		Thread.sleep(2000);
		
		// 19) Export the Estimate
		driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
		
		// 20) Verify the downloded file in the local folder
		File  expEst1 = new File("C:\\Users\\hp\\Downloads\\ExportedEstimate (1).xlsx");
		
		if(expEst1.exists()) {
			System.out.println("File 2 Exists");
		}
		
		driver.close();

	}



}
