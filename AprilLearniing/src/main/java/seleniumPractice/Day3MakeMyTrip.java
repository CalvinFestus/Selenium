package seleniumPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Day3MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {

		// 1) Go to https://www.makemytrip.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// 2) Click Hotels
		driver.findElement(By.xpath("//li[@class='menu_Hotels']")).click();


		driver.findElement(By.xpath("//div[@class='hsw_inputBox selectHtlCity  ']")).click();
		Thread.sleep(2000);

		// 3) Enter city as Goa, and choose Goa, India
		driver.findElement(By.xpath("//input[@class='react-autosuggest__input react-autosuggest__input--open']")).sendKeys("Goa");
		driver.findElement(By.xpath("//div[@class='react-autosuggest__section-container react-autosuggest__section-container--first']/ul/li[1]")).click();

		// 4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div[2]/div[3]/div[3]/div[6]")).click();		
		driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div[2]/div[3]/div[4]/div[3]")).click();

		// 5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		driver.findElement(By.xpath("//input[@class='hsw_inputField guests font20']")).click();		
		driver.findElement(By.xpath("//p[@class='appendBottom15 makeFlex spaceBetween']/following::li[2]")).click();		
		driver.findElement(By.xpath("//p[@class='appendBottom15 makeFlex spaceBetween']/following::li[14]")).click();		
		driver.findElement(By.xpath("//p[@class='appendBottom15 makeFlex spaceBetween']/following::option[12]")).click();
		driver.findElement(By.xpath("//button[@class='primaryBtn btnApply']")).click();

		// 6) Click Search button
		driver.findElement(By.xpath("//button[@id='hsw_search_button']")).click();

		// 7) Select locality as Baga
		driver.findElementByXPath("//body[@class='bodyFixed overlayWholeBlack']").click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");

		driver.findElement(By.xpath("//label[@for='mmLocality_checkbox_35']")).click();
		Thread.sleep(3000);

		// 8) Select 5 start in Star Category under Select Filters
		js.executeScript("window.scrollBy(0,800)");
		driver.findElement(By.xpath("//label[text()='5 Star']")).click();
		Thread.sleep(3000);

		// 9) Click on the first resulting hotel and go to the new window
		js.executeScript("window.scrollBy(0,400)");
		driver.findElement(By.xpath("//div[@class='infinite-scroll-component ']/div[2]/a")).click();

		// 10) Print the Hotel Name 
		String hotelName = driver.findElementByXPath("(//div[@class='makeFlex spaceBetween'])[1]/div[1]/p[1]/span[1]").getText();
		System.out.println(hotelName);

		Set<String> hotelWindow = driver.getWindowHandles();
		List<String> l1 = new ArrayList<String>();
		l1.addAll(hotelWindow);

		driver.switchTo().window(l1.get(1));

		// 11) Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElementByXPath("(//span[@class='latoBold font10 blueText pointer'])[1]").click();		
		driver.findElementByXPath("(//td[@class='textRight'])[1]").click();
		driver.findElementByXPath("//span[@class='close']").click();

		// 12) Click on BOOK THIS NOW
		driver.findElementByXPath("//a[@id='detpg_headerright_book_now']").click();

		// 13) Print the Total Payable amount
		String totalPayAmount = driver.findElementByXPath("//span[@id='revpg_total_payable_amt']").getText();
		System.out.println(totalPayAmount);

		// 14) Close the browser
		driver.quit();
	}

}
