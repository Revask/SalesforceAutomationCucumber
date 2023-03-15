package SalesforceLoginPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Salesforce.Automation.Base.*;

public class LoginPage extends BasePage {

	WebDriver driver;
	
	@FindBy(xpath ="//input[@id='username']") WebElement username;
	@FindBy(xpath="//input[@id='password']") WebElement password;
	@FindBy(xpath="//input[@id='Login']") WebElement loginButton;
	@FindBy(xpath="//input[@id='rememberUn']")WebElement rememberUserName;
	@FindBy(xpath="//a[@id='forgot_password_link']")  WebElement forgotPwd;

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	public 	void enterUsername(String uname)
	{
		waitExplicit(username);
		enter(username,uname);
	}
	public void enterPwd(String pwd)
	{
		enter(password,pwd);
	}
	public void clickLogin()
	{
		clickOn(loginButton);
	}
	public void login(String uname,String pwd)  
	{
		
		enterUsername(uname);
		emptyContent(password);
		enterPwd(pwd);
		clickLogin();
	}
	public void selectRememberMeChkbox()
	{
		selectCheckBox(rememberUserName);
	}
	public void forgotPwdLink()
	{
		waitExplicit(forgotPwd);
		clickOn(forgotPwd);
	}
	public boolean chkIfPwdEmpty()
	{
		if(checkIfEmpty(password))
			return true;
		else
			return false;
	}
	public boolean chkIfUnameEmpty()
	{
		if(checkIfEmpty(username))
			return true;
		else
			return false;
	}
	public String getUname()
	{
		return read(username);
	}
	public boolean chkIfRememberUnameSelected()
	{
		if(isCheckBoxSelected(rememberUserName))
			return true;
			else
				return false;
	}

}
