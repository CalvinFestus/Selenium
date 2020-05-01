package seleniumPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class Day12Carwale {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		op.merge(cap);
		ChromeDriver driver = new ChromeDriver(op);
		driver.get("https://www.carwale.com/");
		driver.manage().window().maximize();
		
		driver.findElementByXPath("//li[@data-tabs='usedCars']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//input[@id='usedCarsList']").sendKeys("Chennai");
		Thread.sleep(2000);
		driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click();
		Thread.sleep(2000);
		
		//driver.findElementByXPath("//span[@id='budgetBtn']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//li[text()='8 Lakh']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("(//li[text()='12 Lakh'])[2]").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//button[@id='btnFindCar']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//div[@name='filterbyadditional']/div//li[1]").click();
		Thread.sleep(2000);
		
		/*driver.findElementByXPath("//span[text()='Cars with Photos']").click();
		Thread.sleep(2000);*/
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");
		
		driver.findElementByXPath("//span[text()=' Hyundai ']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//span[text()='Creta']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//div[@name='fuel']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//span[text()='Petrol']").click();
		Thread.sleep(2000);
		
		WebElement sortDrpDwn = driver.findElementByXPath("//select[@id='sort']");
		Thread.sleep(2000);
		Select sort = new Select(sortDrpDwn);
		sort.selectByVisibleText("KM: Low to High");
		
		List<Integer> kmsAll = new ArrayList<Integer>();
		List<WebElement> kmsTextAllEle = driver.findElementsByXPath("//span[@class='slkms vehicle-data__item']");
		Thread.sleep(5000);
		for(int i=0;i<kmsTextAllEle.size();i++) {
			System.out.println("For Loop 1");
			String kmsTextAll = kmsTextAllEle.get(i).getText();
			String kmsText = kmsTextAll.replaceAll("\\D", "");
			System.out.println(kmsText);
			int kms = Integer.parseInt(kmsText);
			kmsAll.add(kms);
		}
		
		System.out.println(kmsAll);
		
		List<Integer> kmsAllSort = new ArrayList<Integer>(kmsAll);
		Collections.sort(kmsAllSort);
		
		System.out.println(kmsAllSort);
		
		if(kmsAll.equals(kmsAllSort)) {
			System.out.println("Sorted based on Low KM: LOW to HIGH");
		}
		else {
			System.out.println("Not Sorted based on Low KM: LOW to HIGH");
		}
		
		for(int i=0;i<kmsTextAllEle.size();i++) {
			if(kmsAll.get(i).equals(kmsAllSort.get(0))) {
				WebElement wishList = driver.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])["+(i+1)+"]");
				js.executeScript("arguments[0]. click();", wishList);
				Thread.sleep(2000);
			}
		}
		
		driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//a[text()='More details »']").click();
		Thread.sleep(2000);
		
		Set<String> winHan = driver.getWindowHandles();
		List<String> li = new ArrayList<String>();
		li.addAll(winHan);
		
		driver.switchTo().window(li.get(1));
		
		Map<String, String> mp = new LinkedHashMap<String, String>();
		
		List<WebElement> overviewKey = driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width text-light-grey']");
		List<WebElement> overviewVal = driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width dark-text']");
		
		for(int i=0;i<overviewKey.size();i++) {
			
			String keyFeature = overviewKey.get(i).getText();
			String valueFeature = overviewVal.get(i).getText();
			
			mp.put(keyFeature, valueFeature);
			
		}
		
		for (Entry<String,String> map : mp.entrySet()) {
			
			System.out.println(map.getKey() + "*****************" + map.getValue());
		}
		
		driver.quit();
		
	}

}
