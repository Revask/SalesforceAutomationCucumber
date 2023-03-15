package com.Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import SalesforceHomePages.*;
import SalesforceLoginPages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.Salesforce.Automation.Utility.GenerateReports;
import com.Salesforce.Automation.Utility.SalesforceCommonUtilities;

@Listeners  (com.Salesforce.Automation.Utility.SalesforceListeners.class)
public class SalesforceLoginSteps {
	
	public static WebDriver driver;
	LoginPage loginpg;
	HomePage homepg;
	ForgotPasswordPage fpp;
	InvalidUsernamePwdPage iupp;
	PwdErrorMsgLoginPage err;
	ReturnToLoginPage ret;
	public GenerateReports report = GenerateReports.getInstance();
	
	@Given("user opens salesforce login page")
	public void user_opens_salesforce_login_page() throws InterruptedException {
		System.out.println("Chrome steps");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com/");
		Thread.sleep(1000);
		System.out.println("Driver "+driver.toString())	;
		System.out.println("Inside open salesforce page");
	   
	}
	@When("user on {string}")
	public void user_on(String pagename) {
		
	   switch(pagename)
	   {
	   		case "LoginPage":
	   		{
	   			loginpg = new LoginPage(driver);
	   			break;
	   		}
	   		case "HomePage":
	   		{
	   			homepg = new HomePage(driver);
	   			break;
	   		}
	   		case "ForgotPasswordPage":
	   		{  
	   			fpp = new ForgotPasswordPage(driver);
	   			break;
	   		}
	   		case "InvalidUsernamePwdPage":
	   		{
	   			iupp = new InvalidUsernamePwdPage(driver);
	   			break;
	   		}
	   		case "PwdErrorMsgLoginPage":
	   		{
	   			err = new PwdErrorMsgLoginPage(driver);
	   			break;
	   		}
	   		case "ReturnToLoginPage":
	   		{
	   			ret = new ReturnToLoginPage(driver);
	   			break;
	   		}
	   }
	}
	@When("user enters username from propertyfile")
	public void user_enters_username_from_propertyfile() {
		String id = SalesforceCommonUtilities.getLoginId();
		loginpg.enterUsername(id);
	}
	@When("user enters password from propertyfile")
	public void user_enters_password_from_propertyfile() {
		String pwd = SalesforceCommonUtilities.getLoginpwd();
		loginpg.enterPwd(pwd);
	}
	@When("user click on login button")
	public void user_click_on_login_button() {
	   loginpg.clickLogin();
	}
	@Then("verify page title should be {string}")
	public void verify_page_title_should_be(String expectedTitle) {
	    String actualTitle = driver.getTitle();
	   Assert.assertEquals(actualTitle,expectedTitle);
	}

	@When("user enters password as {string}")
	public void user_enters_password_as(String pwd) {
		loginpg.enterPwd(pwd);
	}

	@Then("verify error message {string} is displayed")
	public void verify_error_message_is_displayed(String errMsg) {
	   boolean actualMsg = err.foundErrorMessage();
	   Assert.assertTrue(actualMsg);
	}

	@When("user clicks on Remember me checkbox")
	public void user_clicks_on_remember_me_checkbox() {
	    loginpg.selectRememberMeChkbox();
	}
	@When("user click on user menu button")
	public void user_click_on_user_menu_button() throws InterruptedException {
	    homepg.selectUserMenu();
	    Thread.sleep(3000);
	}
	@When("user click on logout button")
	public void user_click_on_logout_button() {
		homepg.logout();
	}
	@Then("verify username is displayed and remember me checkbox is selected")
	public void verify_username_is_displayed_and_remember_me_checkbox_is_selected() {
	    boolean unameIsEmpty = loginpg.chkIfUnameEmpty();
	    boolean rememberMeSelected = loginpg.chkIfRememberUnameSelected();
	    Assert.assertFalse(unameIsEmpty);
	    Assert.assertTrue(rememberMeSelected);
	}

	@When("clicks on forgot password link")
	public void clicks_on_forgot_password_link() {
	   loginpg.forgotPwdLink();
	}
	@When("user enters {string} in username field")
	public void user_enters_in_username_field(String username) {
		fpp.enterUname(username);
	}
	@When("clicks on continue button")
	public void clicks_on_continue_button() {
	   fpp.clickOnContinue();
	}
	@Then("verify {string} message is displayed")
	public void verify_message_is_displayed(String expectedMsg) {
	    String actual = ret.readMsg();
	    Assert.assertEquals(actual,expectedMsg);
	}

	@When("user enters {string} for username field")
	public void user_enters_for_username_field(String uname) {
		loginpg.enterUsername(uname);
	}
	@Then("verify error message is present")
	public void verify_error_message_is_present() {
	    Assert.assertTrue(iupp.foundErrorMessage());
	}
	/*@After
	public void closing() throws InterruptedException
	{
		Thread.sleep(4000);
		driver.quit();
	}
*/
	
}
