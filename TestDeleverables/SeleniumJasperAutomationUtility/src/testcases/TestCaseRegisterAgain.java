package testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Utilclass;

public class TestCaseRegisterAgain {

public static void run() throws IOException {
	
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, 60);

		driver.get("http://localhost:8080/home/login.jsp");

		Register(driver);

		// driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

	}


	private static void Register(WebDriver driver) throws IOException {

		ArrayList<String> pathsRegister = new ArrayList<String>();
		pathsRegister = Utilclass.readColumnValuesFromExcel(System.getProperty("user.dir") + "\\input\\Controller.xlsx","Configuration", 6, 21, 3);
		String[] pathRegister = pathsRegister.toArray(new String[pathsRegister.size()]);

		ArrayList<String> registerInputs = new ArrayList<String>();
		registerInputs = Utilclass.readColumnValuesFromExcel(System.getProperty("user.dir") + "\\input\\Controller.xlsx", "Configuration", 6, 19, 4);
		String[] registerInput = registerInputs.toArray(new String[20]);
		
		// Input Username - //html/body/div/div/div[1]/form/div[1]/input[1]
		WebElement ele4 = driver.findElement(By.xpath(pathRegister[0]));
		ele4.sendKeys(registerInput[0]);

		// Input Lastname - //html/body/div/div/div[1]/form/div[1]/input[2]
		WebElement ele5 = driver.findElement(By.xpath(pathRegister[1]));
		ele5.sendKeys(registerInput[1]);

		// Input Email ID - //html/body/div/div/div[1]/form/div[2]/input
		WebElement ele6 = driver.findElement(By.xpath(pathRegister[2]));
		ele6.sendKeys(registerInput[2]);

		// Input Password - //*[@id='firstpass']
		WebElement ele7 = driver.findElement(By.xpath(pathRegister[3]));
		ele7.sendKeys(registerInput[3]);

		// Input Confirm Password - //*[@id='confirmpass']
		WebElement ele8 = driver.findElement(By.xpath(pathRegister[4]));
		ele8.sendKeys(registerInput[4]);

		// Input Phone Number - //html/body/div/div/div[1]/form/div[5]/input
		WebElement ele9 = driver.findElement(By.xpath(pathRegister[5]));
		ele9.sendKeys(registerInput[5]);

		// Input Shipping Address - //html/body/div/div/div[1]/form/div[6]/input
		WebElement ele10 = driver.findElement(By.xpath(pathRegister[6]));
		ele10.sendKeys(registerInput[6]);

		// Input City - //html/body/div/div/div[1]/form/div[7]/input[1]
		WebElement ele11 = driver.findElement(By.xpath(pathRegister[7]));
		ele11.sendKeys(registerInput[7]);

		// Input Province - //html/body/div/div/div[1]/form/div[7]/input[2]
		WebElement ele12 = driver.findElement(By.xpath(pathRegister[8]));
		ele12.sendKeys(registerInput[8]);

		// Input Zip - "//html/body/div/div/div[1]/form/div[7]/input[3]"
		WebElement ele13 = driver.findElement(By.xpath(pathRegister[9]));
		ele13.sendKeys(registerInput[9]);

		// Input Billing Address - "//html/body/div/div/div[1]/form/div[8]/input"
		WebElement ele14 = driver.findElement(By.xpath(pathRegister[10]));
		ele14.sendKeys(registerInput[10]);

		// Input City - "//html/body/div/div/div[1]/form/div[9]/input[1]"
		WebElement ele15 = driver.findElement(By.xpath(pathRegister[11]));
		ele15.sendKeys(registerInput[11]);

		// Input Province - "//html/body/div/div/div[1]/form/div[9]/input[2]"
		WebElement ele16 = driver.findElement(By.xpath(pathRegister[12]));
		ele16.sendKeys(registerInput[12]);

		// Input Zip - "//html/body/div/div/div[1]/form/div[9]/input[3]"
		WebElement ele17 = driver.findElement(By.xpath(pathRegister[13]));
		ele17.sendKeys(registerInput[13]);

		//Click Register CTA - "//*[@id='subbtn']"
		WebElement ele18 = driver.findElement(By.xpath(pathRegister[14]));
		ele18.click();

		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		//Click Register CTA - "//*[@id='subbtn']"
		WebElement ele19 = driver.findElement(By.xpath(pathRegister[15]));
		
		String currentText = ele19.getText();
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		
		boolean pass = currentText.equalsIgnoreCase("User Already Exist");

		if (pass) {
			System.out.println("Test case got passed");
			Utilclass.writeInExcel(System.getProperty("user.dir") + "\\output\\Output.xlsx", "TestCases", 6, 3,
					"Test case got passed");
		} else {
			System.out.println("Test case got failed");
			Utilclass.writeInExcel(System.getProperty("user.dir") + "\\output\\Output.xlsx", "TestCases", 6, 3,"Test case got failed");
		}
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}

}
