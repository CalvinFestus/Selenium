package seleniumPractice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class Day10JustDial {

	public static void main(String[] args) throws InterruptedException {
		
		String phno = " ";
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		//op.addArguments("--headless");
		ChromeDriver driver = new ChromeDriver(op);
		driver.get("https://www.justdial.com/");
		driver.manage().window().maximize();

		driver.findElementByXPath("//input[@id='city']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//ul[@id='cuto']/li[4]").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='Auto care']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//span[@title='Car Repair']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("(//span[@title='Hyundai'])[1]").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='Hyundai Xcent']").click();
		Thread.sleep(3000);

		/*driver.findElementByXPath("//a[text()='Location']").click();
		Thread.sleep(3000);
		WebElement loc = driver.findElementByXPath("//input[@id='sortbydist']");
		loc.clear();
		loc.sendKeys("Porur");
		Thread.sleep(3000);
		driver.findElementByXPath("//button[@id='sortbydistbtn']").click();
		driver.findElementByXPath("//i[@class='res_downarrowic resultimg']").click();
		driver.findElementByXPath("//i[@class='res_downarrowic resultimg']/following::a[text()='1 km']").click();
		Thread.sleep(3000);*/
		
		Map<String,String> hm = new LinkedHashMap<String,String>();
		hm.put("+", "dc");
		hm.put("(", "fe");
		hm.put(")", "hg");
		hm.put("-", "ba");
		hm.put("0", "acb");
		hm.put("1", "yz");
		hm.put("2", "wx");
		hm.put("3", "vu");
		hm.put("4", "ts");
		hm.put("5", "rq");
		hm.put("6", "po");
		hm.put("7", "nm");
		hm.put("8", "lk");
		hm.put("9", "ji");

		List<WebElement> ratings = driver.findElementsByXPath("//span[@class='green-box']");
		List<String> serviceName = new ArrayList<String>();
		List<String> servicePhNo = new ArrayList<String>();

		for (int i=0;i<ratings.size();i++) {
			System.out.println("For Loop");
			String textRating = ratings.get(i).getText();
			float rating = Float.parseFloat(textRating);
			System.out.println(rating);
			if(rating>=4.5) {
				String textVotesAll = driver.findElementByXPath("(//span[@class='green-box'])["+(i+1)+"]/following::span[2]").getText();
				Thread.sleep(5000);
				System.out.println(textVotesAll);
				String textVotes = textVotesAll.replaceAll("\\D", " ");
				System.out.println(textVotesAll);
				int votes = Integer.parseInt(textVotes);

				if(votes>=50) {
					String textCompName = driver.findElementByXPath("(//span[@class='green-box'])["+(i+1)+"]/preceding::span[@class='lng_cont_name']").getText();
					serviceName.add(textCompName);

					List<WebElement> phoneNumber = driver.findElementsByXPath("(//p[@class='contact-info '])["+(i+1)+"]/span/span");

					for (int j=0;j<phoneNumber.size();j++) {
						
						String phoneNum = phoneNumber.get(j).getAttribute("class");
						String phNum = phoneNum.substring(14);
						
						for (Entry<String,String> eachEntry : hm.entrySet()) {
							
							if(eachEntry.getValue().equalsIgnoreCase(phNum)) {
								phno = phno+(eachEntry.getKey());
							}
						}

					}
					
					System.out.println(phno);
				}
			}
		}
	}

}
