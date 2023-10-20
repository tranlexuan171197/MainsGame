package com.project.function;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LandingPageObject;
import pageObjects.SignUpPageObject;

public class Test_Case_Sign_Up extends BaseTest {
	WebDriver driver;
	LandingPageObject landingPage;
	SignUpPageObject signUpPage;

	String emailWrongFormatError ="The email field must be a valid email.";
	String passwordError ="The password field must be at least 8 characters.";
	String confirmPasswordError ="The password_confirmation field must be at least 8 characters.";

	@Parameters({"browser", "url"})
	@BeforeTest
//	@BeforeClass
	public void initBrowser(String browser, String url) {
		log.info("Pre condition: Open Browser: " + browser + "and navigate to url:" + url);
		driver = getBrowserDriver(browser, url);
		driver.manage().window().maximize();

		log.info("Click Sign Up For Free at Landing Page");
		landingPage = new LandingPageObject(driver);
		landingPage.clickToSignUpButton();
	}

	/**
	 * This test case is nonsense because the button is dis-able when empty all field so can not wait for clickable
	 * temporary remove wait for click-able for button.
	 */
	@Test(priority = 1)
	public void TC_Sign_Up_For_Free_With_All_Empty_Field(){
/*		String email ="tes123@gmail.com";
		String password ="12345678";
		String confirmPassword = "12345678";
		String name = "test";*/

		String email ="";
		String password ="";
		String confirmPassword = "";
		String name = "";

		log.info("Empty All Field And Click Sign Up");
		signUpPage = new SignUpPageObject(driver);
		signUpPage.SignUpToEklipse(name,email,password,confirmPassword);


	}

	@Test(priority = 2)
	public void TC_Sign_Up_For_Free_With_Wrong_Email_Format(){
		String email ="tes123";
		String password ="";
		String confirmPassword = "";
		String name = "";

		log.info("Empty All Field except Email with wrong email format And Click Sign Up");
		signUpPage = new SignUpPageObject(driver);
		signUpPage.SignUpToEklipse(name,email,password,confirmPassword);
		String actualEmailError = signUpPage.getEmailErrorText();
		verifyEquals(actualEmailError,emailWrongFormatError);

	}

	@Test(priority = 3)
	public void TC_Sign_Up_For_Free_With_Short_Password_And_Confirm_Password(){
		String email ="tes123@gmail.com";
		String password ="123456";
		String confirmPassword = "123456";
		String name = "Test";

		log.info("Input password < 8 character And Click Sign Up");
		signUpPage = new SignUpPageObject(driver);
		signUpPage.SignUpToEklipse(name,email,password,confirmPassword);
		String actualPasswordError = signUpPage.getPasswordErrorText();
		verifyEquals(actualPasswordError,passwordError);

		String actualConfirmPasswordError = signUpPage.getCofirmPasswordErrorText();
		verifyEquals(actualConfirmPasswordError,confirmPasswordError);

	}

	@Test(priority = 4)
	public void TC_Sign_In_To_Eklipse_Successfully(){
		String email ="tes321@gmail.com";
		String password ="12345678";
		String confirmPassword = "12345678";
		String name = "Test";

		log.info("Input valid to all field And Click Sign Up");
		signUpPage = new SignUpPageObject(driver);
		signUpPage.SignUpToEklipse(name,email,password,confirmPassword);

	}

	@AfterClass
	public void cleanBrowser(){
		cleanBrowserAndDriver();
	}
}
