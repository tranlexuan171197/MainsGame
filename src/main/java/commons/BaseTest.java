package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
	private WebDriver driver;

	protected final Log log;

	protected BaseTest(){
		log = LogFactory.getLog(getClass());
		log.debug("Debug Message Logged !!!");
		log.info("Info Message Logged !!!");

	}

	public WebDriver getWebDriver() {
		return this.driver;
	}

	private enum BROWSER{
		CHROME, FIREFOX, IE, SAFARI, EDGE_LEGACY, EDGE_CHROMIUM, H_CHROME, H_FIREFOX;
	}

	private enum OS{
		WINDOWS, MAX_OSX, LINUX;
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl){
		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		if(browser == BROWSER.CHROME){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if (browser == BROWSER.FIREFOX){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser == BROWSER.EDGE_CHROMIUM){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else{
			throw new RuntimeException("browser entered "+browserName+"is not correct please enter again.");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;
	}

	private boolean checkTrue(boolean condition){
		boolean pass = true;
		try {
			if (condition == true){
				log.info(" -------------------------- PASSED -------------------------- ");
			}else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		}catch (Throwable e){
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(),e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition){
		return checkTrue(condition);
	}

	private boolean checkFalse(boolean condition){
		boolean pass = true;
		try {
			if (condition == false){
				log.info(" -------------------------- PASSED -------------------------- ");
			}else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		}catch (Throwable e){
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(),e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	private boolean verifyFalse(boolean condition){
		return checkFalse(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected void cleanBrowserAndDriver(){
		if(driver != null){
			driver.manage().deleteAllCookies();
			driver.quit();
			System.out.println("Close driver instance");
		}

	}

	@AfterSuite(alwaysRun = true)
	public void cleanExecutableDriver(){
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
			System.out.println("Run command line");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void deleteAllFilesInReportNGScreenshot(){
		log.info("---------- START delete file in folder ----------");

		try{
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir +"\\screenshotReportNG";
			File file = new File (pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for(int i =0;i<listOfFiles.length;i++){
				if(listOfFiles[i].isFile()){
					new File(listOfFiles[i].toString()).delete();
				}
			}

		}catch (Exception e ){
			System.out.println(e.getMessage());
		}
		log.info("---------- END delete file in folder ----------");
	}

}
