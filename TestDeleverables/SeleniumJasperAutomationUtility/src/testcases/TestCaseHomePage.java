package testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Utilclass;

public class TestCaseHomePage {

	public static void run() throws IOException {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("http://localhost:8080/home/");

		String currentURL = driver.getCurrentUrl();
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		System.out.println(currentURL);
		
		boolean pass = currentURL.equalsIgnoreCase("http://localhost:8080/home/");

		if (pass) {
			System.out.println("Test case got passed");
			Utilclass.writeInExcel(System.getProperty("user.dir") + "\\output\\Output.xlsx", "TestCases", 3, 3,
					"Test case got passed");
		} else {
			System.out.println("Test case got failed");
			Utilclass.writeInExcel(System.getProperty("user.dir") + "\\output\\Output.xlsx", "TestCases", 3, 3,
					"Test case got failed");
		}

	}

}
