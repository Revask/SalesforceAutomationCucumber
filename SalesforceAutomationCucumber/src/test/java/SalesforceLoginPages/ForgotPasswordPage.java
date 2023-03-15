package SalesforceLoginPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Salesforce.Automation.Base.*;

public class ForgotPasswordPage extends BasePage {

	public ForgotPasswordPage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//input[@id='un']") WebElement uname;
	@FindBy(xpath="//input[@id='continue']") WebElement cont;
	
	public void enterUname(String username)
	{
		waitExplicit(uname);
		enter(uname,username);
	}
	public void clickOnContinue()
	{
		waitExplicit(cont);
		clickOn(cont);
	}
}
