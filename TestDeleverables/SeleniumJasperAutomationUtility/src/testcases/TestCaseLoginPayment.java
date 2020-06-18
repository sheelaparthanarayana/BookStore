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

public class TestCaseLoginPayment {

public static void run() throws IOException {
		
		ArrayList<String> pathsRun = new ArrayList<String>();
		pathsRun = Utilclass.readColumnValuesFromExcel(System.getProperty("user.dir") + "\\input\\Controller.xlsx",
				"Configuration", 2, 4, 3);
		String[] pathRun = pathsRun.toArray(new String[pathsRun.size()]);

		System.out.println(System.getProperty("user.dir"));
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, 60);

		driver.get("http://localhost:8080/home/");

		// driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

		// Quick Add path-//html/body/div/div/div[2]/div/div[1]/div/form/button
		WebElement ele1 = driver.findElement(By.xpath(pathRun[0]));

		ele1.click();

		// Clicking Checkout path-//*[@id='navbarResponsive']/ul/li[2]/a
		WebElement ele2 = driver.findElement(By.xpath(pathRun[1]));

		ele2.click();

		// Clicking Checkout CTA on Checkout Page
		// path-//html/body/div/div/div/div[2]/div/table/tfoot/tr[2]/td[4]/a
		WebElement ele3 = driver.findElement(By.xpath(pathRun[2]));

		ele3.click();

		login(driver);
		confirmDetail(driver);
		payment(driver);
		success(driver);
	}

	
	private static void login(WebDriver driver) {

		ArrayList<String> pathsLogin = new ArrayList<String>();
		pathsLogin = Utilclass.readColumnValuesFromExcel(System.getProperty("user.dir") + "\\input\\Controller.xlsx", "Configuration", 22, 24, 3);
		String[] pathLogin = pathsLogin.toArray(new String[pathsLogin.size()]);
		
		
		
		ArrayList<String> loginInputs = new ArrayList<String>();
		loginInputs = Utilclass.readColumnValuesFromExcel(System.getProperty("user.dir") + "\\input\\Controller.xlsx", "Configuration", 8, 9, 4);
		String[] loginInput = loginInputs.toArray(new String[loginInputs.size()]);
		
		
		
		// Input Email - //*[@id='inputEmail']"
		WebElement ele19 = driver.findElement(By.xpath(pathLogin[0]));
		ele19.sendKeys(loginInput[0]);

		// Input Password - "//*[@id='inputPassword']"
		WebElement ele20 = driver.findElement(By.xpath(pathLogin[1]));
		ele20.sendKeys(loginInput[1]);

		// Clicking login CTA - "//html/body/div/div/div[2]/form/button"
		WebElement ele21 = driver.findElement(By.xpath(pathLogin[2]));
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		ele21.click();
		
	}

	private static void confirmDetail(WebDriver driver) {

		String pathConfirm = Utilclass.readFromExcel(System.getProperty("user.dir") + "\\input\\Controller.xlsx", "Configuration", 26,3);
		// Clicking next CTA - //html/body/div/div/div[2]/form/button
		WebElement ele22 = driver.findElement(By.xpath(pathConfirm));
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		ele22.click();
	}

	private static void payment(WebDriver driver) {
		
		ArrayList<String> pathsPayment = new ArrayList<String>();
		pathsPayment = Utilclass.readColumnValuesFromExcel(System.getProperty("user.dir") + "\\input\\Controller.xlsx", "Configuration", 28, 33, 3);
		String[] pathPayment = pathsPayment.toArray(new String[pathsPayment.size()]);

		// Enter user name - "//html/body/div/div/div/form/div[1]/input"
		WebElement ele23 = driver.findElement(By.xpath(pathPayment[0]));
		ele23.sendKeys("Liam Johnson");

		// Enter credit card number - "//html/body/div/div/div/form/div[2]/input"
		WebElement ele24 = driver.findElement(By.xpath(pathPayment[1]));
		ele24.sendKeys("1111222233334444");

		// Enter CVV - "//*[@id=\"subbtn\"]"
		WebElement ele27 = driver.findElement(By.xpath(pathPayment[4]));
		ele27.sendKeys("121");
		
		//Click Pay Now
		WebElement ele28 = driver.findElement(By.xpath(pathPayment[5]));
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		ele28.click();
		
	}

	private static void success(WebDriver driver) throws IOException {
		
		ArrayList<String> pathsSuccess = new ArrayList<String>();
		pathsSuccess = Utilclass.readColumnValuesFromExcel(System.getProperty("user.dir") + "\\input\\Controller.xlsx", "Configuration", 34, 35, 3);
		String[] pathSuccess = pathsSuccess.toArray(new String[pathsSuccess.size()]);

		// Verify the Order Passed message - //html/body/div/div/div/div/h4
		WebElement ele28 = driver.findElement(By.xpath(pathSuccess[0]));
		
		String currentText = ele28.getText();
		
		boolean pass = currentText.equalsIgnoreCase("Your Order has been Placed.");

		if (pass) {
			System.out.println("Test case got passed");
			Utilclass.writeInExcel(System.getProperty("user.dir") + "\\output\\Output.xlsx", "TestCases", 8, 3,
					"Test case got passed");
		} else {
			System.out.println("Test case got failed");
			Utilclass.writeInExcel(System.getProperty("user.dir") + "\\output\\Output.xlsx", "TestCases", 8, 3,"Test case got failed");
		}
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		// Click continue shopping link
		WebElement ele24 = driver.findElement(By.xpath(pathSuccess[1]));
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		ele24.click();
	}

}
