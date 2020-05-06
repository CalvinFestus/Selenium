package seleniumPractice;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day14Zalando {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// 1) Go to https://www.zalando.com/
		// 2) Get the Alert text and print it

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_81.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.zalando.com/");
		Thread.sleep(2000);

		try {
			Alert alert = driver.switchTo().alert();
			String alerttext = alert.getText();
			System.out.println("Alert text is '" + alerttext + "'");
			alert.accept();
		} catch (Exception e) {
			System.out.println("No Alert present");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);

		// 3) Close the Alert box and click on Zalando.uk

		driver.findElementByXPath("//li//a[text()='Zalando.uk']").click();
		Thread.sleep(2000);

		// 4) Click Women--> Clothing and click Coat

		Actions builder = new Actions(driver);
		WebElement clothing = driver.findElementByXPath("//span[text()='Clothing']");
		builder.moveToElement(clothing).perform();
		WebElement coats = driver.findElementByXPath("(//a[@class='z-navicat-header_subCategoryLink'])[3]");
		builder.moveToElement(coats).click().perform();
		Thread.sleep(2000);

		// 5) Choose Material as cotton (100%) and Length as thigh-length

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uc-btn-accept-banner")));

		driver.findElementById("uc-btn-accept-banner").click();
		driver.findElementByXPath("//span[text()='Material']").click();
		driver.findElementByXPath("//span[text()='cotton (100%)']").click();
		driver.findElementByXPath("//button[text()='Save']").click();
		Thread.sleep(3000);

		driver.findElementByXPath("//span[text()='Length']").click();
		driver.findElementByXPath("(//li[contains(@class,'cat_pill-eq1Rc cat_row-36vtu')])[2]").click();
		driver.findElementByXPath("//button[text()='Save']").click();
		Thread.sleep(2000);

		// 6) Click on Q/S designed by MANTEL - Parka coat

		driver.findElementByXPath("//div[text()='MANTEL - Parka - navy']").click();
		Thread.sleep(2000);

		// 7) Check the availability for Color as Olive and Size as 'M'

		/*
		 * if (driver.findElementByXPath("(//img[@alt='olive'])[2]").isDisplayed()) {
		 * System.out.println("Color Olive is avialable");
		 * driver.findElementByXPath("(//img[@alt='olive'])[2]").click(); }
		 * 
		 */
		driver.findElementById("picker-trigger").click();
		driver.findElementByXPath("//span[text()='M']").click();

		// 8) If the previous preference is not available, check availability for Color
		// Navy and Size 'M'

		/*
		 * if(driver.findElementByCssSelector("div#z-pdp-topSection>div>h2").isDisplayed
		 * ()) {
		 * 
		 * List<WebElement> colors =
		 * driver.findElementsByXPath("//div[@class='C-HkOdqtYMnP']//div//img");
		 * System.out.println("Total colors avialable is " + colors.size() +
		 * ". So please select another color.");
		 * driver.findElementByXPath("(//div[@class='C-HkOdqtYMnP']//div//img)[2]").
		 * click(); Thread.sleep(2000);
		 * driver.findElementById("picker-trigger").click();
		 * driver.findElementByXPath("//span[text()='M']").click();
		 * 
		 * } else {
		 * 
		 * System.out.println("No other colors are avialable"); }
		 */
		// 9) Add to bag only if Standard Delivery is free

		if (driver.findElementByXPath("//div[@class='Z4PFF1 UyCaZm lNDA6p']//span[text()='Free']").isDisplayed()) {
			System.out.println("Standard Delivery is Free");
			driver.findElementByXPath("//span[text()='Add to bag']").click();
		} else {
			System.out.println("Standard Delivery is not Free");
		}

		// 10) Mouse over on Your Bag and Click on "Go to Bag"

		WebElement bag = driver.findElementByXPath("//span[text()='Your bag']");
		builder.moveToElement(bag).click().perform();
		Thread.sleep(2000);

		// 11) Capture the Estimated Deliver Date and print

		String estdate = driver
				.findElementByCssSelector("div#app>div>div>div>div>div:nth-of-type(2)>div:nth-of-type(2)>div")
				.getText();
		System.out.println("Estimated Delivery Time - " + estdate);

		// 12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print

		WebElement fdel = driver.findElementByXPath("(//a[@name='“headbanner.about.us\"'])[1]");
		builder.moveToElement(fdel).perform();
		WebElement eletooltip = driver.findElementByXPath("(//div[@class='z-navicat-header-uspBar_message']//span)[2]");
		String tooltip = eletooltip.getAttribute("title");
		System.out.println("Tool Tip - " + tooltip);
		fdel.click();
		Thread.sleep(2000);

		// 13) Click on Start chat in the Start chat and go to the new window

		WebElement chat = driver.findElementByXPath("//span[text()='Start chat']");
		builder.moveToElement(chat).click().perform();
		chat.click();
		Thread.sleep(3000);
		Set<String> windows = driver.getWindowHandles();
		List<String> lt = new ArrayList<String>(windows);
		driver.switchTo().window(lt.get(1));

		// 14) Enter you first name and a dummy email and click Start Chat

		driver.findElementById("prechat_customer_name_id").sendKeys("Rini");
		driver.findElementById("prechat_customer_email_id").sendKeys("iresgfhkkkkj@gmail.com");
		driver.findElementByTagName("span").click();
		Thread.sleep(3000);

		// 15) Type Hi, click Send and print thr reply message and close the chat
		// window.

		driver.findElementById("liveAgentChatTextArea").sendKeys("Hi", Keys.ENTER);
		String chatr = driver.findElementByXPath("(//span[@class='operator'])[3]//span[2]").getText();
		System.out.println(chatr);
		Thread.sleep(2000);
		driver.close();

	}

}