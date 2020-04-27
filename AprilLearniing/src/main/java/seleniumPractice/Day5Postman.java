package seleniumPractice;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class Day5Postman {

	public static void main(String[] args) {

		
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Users\\hp\\AppData\\Local\\Postman\\Postman.exe");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		//ChromeDriverService chromeservices = new ChromeDriverService.Builder().build(); 
		ChromeDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();

	}

}
