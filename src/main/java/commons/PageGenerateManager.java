package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.DemoPageObject;
import pageObjects.LandingPageObject;
import pageObjects.SignInPageObject;
import pageObjects.SignUpPageObject;

public class PageGenerateManager {
	private static DemoPageObject demoPage;
	private static LandingPageObject landingPage;
	private static SignInPageObject signinPage;
	private static SignUpPageObject signupPage;


	private PageGenerateManager(){
	}
	public LandingPageObject getLandingPage(WebDriver driver){
		return landingPage = new LandingPageObject(driver);
	}

	public SignInPageObject getSigninPage(WebDriver driver){
		return signinPage = new SignInPageObject(driver);
	}

	public SignUpPageObject getSignupPage(WebDriver driver){
		return signupPage = new SignUpPageObject(driver);
	}


	public static DemoPageObject getDemoPage(WebDriver driver){
		return demoPage = new DemoPageObject();
	}
}
