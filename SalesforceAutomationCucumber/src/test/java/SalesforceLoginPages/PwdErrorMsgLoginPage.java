package SalesforceLoginPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Salesforce.Automation.Base.*;

public class PwdErrorMsgLoginPage extends BasePage {
	
	public PwdErrorMsgLoginPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//div[@id='error']") WebElement error;
	
	public boolean foundErrorMessage()
	{
		waitExplicit(error);
		if ( isPresent(error))
			return true;
		else 
			return false;
	}
	
}

