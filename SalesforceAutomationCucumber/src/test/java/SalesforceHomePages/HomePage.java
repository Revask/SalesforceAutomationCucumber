package SalesforceHomePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Salesforce.Automation.Base.*;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//span[@id='userNavLabel']") WebElement usermenu;
	@FindBy(linkText="Logout") static WebElement logout_button;
	
	public void selectUserMenu()
	{
		waitExplicit(usermenu);
		clickOn(usermenu);
		
	}
	public void logout()
	{
		selectUserMenu();
		waitExplicit(logout_button);
		clickOn(logout_button);
	}
	
}
