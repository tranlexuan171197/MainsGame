package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePageObject;
import pageObjects.LandingPageObject;
import pageObjects.SignInPageObject;
import pageObjects.SignUpPageObject;

public class PageGenerateManager {
	private static HomePageObject demoPage;
	private static LandingPageObject landingPage;
	private static SignInPageObject signinPage;
	private static SignUpPageObject signupPage;
	private static HomePageObject homePage;


	private PageGenerateManager(){
	}
	public static HomePageObject getHomePage(WebDriver driver){
		return homePage = new HomePageObject(driver);
	}

	public static  LandingPageObject getLandingPage(WebDriver driver){
		return landingPage = new LandingPageObject(driver);
	}

	public static SignInPageObject getSigninPage(WebDriver driver){
		return signinPage = new SignInPageObject(driver);
	}

	public static SignUpPageObject getSignupPage(WebDriver driver){
		return signupPage = new SignUpPageObject(driver);
	}

}
