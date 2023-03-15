package SalesforceLoginPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Salesforce.Automation.Base.*;

public class InvalidUsernamePwdPage extends BasePage 
{
	public InvalidUsernamePwdPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='error']") WebElement errorMsg;
	
	public boolean foundErrorMessage()
	{
		waitExplicit(errorMsg);
		return isPresent(errorMsg);
	}
	
}
