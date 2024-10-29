package Extentreport;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class openbrowsers {
	WebDriver driver;
	ExtentReports extentreport;
	ExtentHtmlReporter reporter;
	ExtentTest testcase;
	
	@BeforeSuite
	public void openbrowser() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver=new ChromeDriver();
		extentreport=new ExtentReports();
		reporter=new ExtentHtmlReporter("Extentreport.html");
		extentreport.attachReporter(reporter);
	}
	@Test
	public void opengoogle() throws IOException {
		testcase=extentreport.createTest("Verify google");
		testcase.log(Status.INFO,"navigating to google");
		driver.get("https://www.google.com/");
		String title = driver.getTitle();
		testcase.log(Status.INFO,title);
		if(title.equals("Google")) {
			testcase.log(Status.PASS, "As expected");
			
		}else {
			testcase.log(Status.FAIL, "deviated");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File sourcefile= screenshot.getScreenshotAs(OutputType.FILE);
			File destinationfile = new File("C:\\Users\\HP\\Downloads\\google.png");
			org.openqa.selenium.io.FileHandler.copy(sourcefile, destinationfile);
			testcase.addScreenCaptureFromPath("google.png");
		}
	}
    @Test
	public void openbing() throws IOException {
    	testcase=extentreport.createTest("Verify bing");
    	testcase.log(Status.INFO,"navigating to bing");
		driver.get("https://www.bing.com/");
		String title = driver.getTitle();
		testcase.log(Status.INFO,title);
		if(title.equals("Bing")) {
			testcase.log(Status.PASS, "As expected");
			
		}else {
			testcase.log(Status.FAIL, "deviated");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File sourcefile= screenshot.getScreenshotAs(OutputType.FILE);
			File destinationfile = new File("C:\\Users\\HP\\Downloads\\bing.png");
			org.openqa.selenium.io.FileHandler.copy(sourcefile, destinationfile);
			testcase.addScreenCaptureFromPath("bing.png");
		}
	}
    @AfterSuite
	public void closebrowser() {
		driver.quit();
		extentreport.flush();
	}
}
