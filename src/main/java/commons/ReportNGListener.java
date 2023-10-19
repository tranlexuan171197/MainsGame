package commons;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ReportNGListener implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("---------- " + result.getName() + " FAILED test ----------");
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		Object testClass = result.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getWebDriver();

		String screenshotPath = captureScreenshot(webDriver, result.getName());
		Reporter.getCurrentTestResult();
		Reporter.log("<br><a target=\"_blank\" href=\"data:image/png;base64," + screenshotPath + "\" "+ "height='100' width='150' >" + "<img src=\"data::image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
		Reporter.setCurrentTestResult(null);

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

	public String captureScreenshot( WebDriver driver, String screeshotName){
		String screenshotBase64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return screenshotBase64;
	}
}
