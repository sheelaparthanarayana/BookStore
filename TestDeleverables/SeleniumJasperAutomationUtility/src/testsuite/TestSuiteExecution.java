package testsuite;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import testcases.TestCaseHomePage;
import testcases.TestCaseLoginPayment;
import testcases.TestCaseRegister;
import testcases.TestCaseRegisterAgain;

public class TestSuiteExecution {

	public static void main(String[] args) throws IOException {
		
		//Running first test case
		TestCaseHomePage.run();
		
		//Running second test case
		TestCaseRegister.run();
		
		//Running third test case
		TestCaseRegisterAgain.run();
		
		//Running fourth test case
		TestCaseLoginPayment.run();
		
	}

}
