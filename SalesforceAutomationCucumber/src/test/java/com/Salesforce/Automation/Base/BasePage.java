package com.Salesforce.Automation.Base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.Salesforce.Automation.Utility.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.google.common.io.Files;

public class BasePage {
	

		protected static WebDriver driver; 
		public static GenerateReports report = GenerateReports.getInstance();
		protected static WebDriverWait wait;
		Logger logfourj = LogManager.getLogger(BasePage.class.getName());
		
		public BasePage(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver,this);
		}
		//implicit wait
				public static void setImplicitWait()
				{
					driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				}
				//Waiting for the web elements to load
				public static void waitExplicit(WebElement element)
				{
					WebDriverWait wait = new WebDriverWait(driver,60);
					wait.until(ExpectedConditions.elementToBeClickable(element));
				}	
				public static String getPageTitle()
				{
					return driver.getTitle();
				}	
		public static String read(WebElement element)
		{
			String s1 = element.getText();
			return s1;
		}
		
		public static void enter(WebElement element,String s)	
		{
			if(element.isEnabled())
				element.sendKeys(s);
			else
				System.out.println("text not  entered" + s);
		}
		
		//clear Contents of the webelement
		
		public static void emptyContent(WebElement element)
		{
			element.clear();
		}
		public static boolean checkIfEmpty(WebElement element)
		{
			String str = element.getText();
			return str.isEmpty();
		}
		public static boolean isPresent(WebElement element)
		{
			if (element.isDisplayed())
				return true;
			else
				return false;
				
		}
		
		public static void checkIfFrameAvailableAndSwitch(WebElement element)
		{
			WebDriverWait wait = new WebDriverWait(driver,25);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		}
	
		//Window handling
		public static void maximizeWindow()
		{
			driver.manage().window().maximize();
		}
		public static String getCurrentWindowHandle()
		{
			String str = driver.getWindowHandle();
			return str;
		}
		public static String getChildWindowHandle(String parent)
		{
			Set<String> handles = driver.getWindowHandles();
			String child = "";
			for(String s : handles )
			{
				if (!(parent.equals(s)))
				{
					System.out.println("child window handle found :" + s);
					child = s;
					break;
				}
			}
			return child;
		}
		public static void switchToWindow(String s)
		{
			driver.switchTo().window(s);
			System.out.println("Switched to new window" + s);
		}
		public static void closeChildWindow(String handle)
		{
			Set<String> handles = driver.getWindowHandles();
			for(String s : handles )
			{
				if (!(handle.equals(s)))
				{
					driver.switchTo().window(s);
					driver.close();
					System.out.println("child window closed");
				}
					
			}
		}
		
		//Selecting from a dropdown menu
		public static int findIndexInDropDown(WebElement element ,String str1)
		{
			Select s = new Select(element);
			List<WebElement> list= s.getOptions();
			int n = list.size();
			int index = -1;
			System.out.println("given string " + str1);
			for(int i=0;i<n;i++)
			{
				String str2 = list.get(i).getText();
				System.out.println("test string " + str2);
				if(str1.equalsIgnoreCase(str2))
				{
					System.out.println(" option from dropdown "+ str2 + " found at index " + i);
					index = i;
					break;
				}
			}
			return index;
				
		}
		public static void setDropDown(WebElement element,int i)
		{
			if(element.isDisplayed())
			{	
				Select list = new Select(element);
				list.selectByIndex(i);
				System.out.println("Dropdown choice "+i+" selected");
			}
			else
				System.out.println("Element  not selected");
		}
		public static void setDropDown( WebElement element,String s)
		{
			Select list = new Select(element);
			list.selectByVisibleText(s);
		}
		
		public static String chosenOption( WebElement element)
		{
			Select list = new Select(element);
			return list.getFirstSelectedOption().getText();
		}
		
		
		public static boolean chkIffLinkIsPresent()
		{
			boolean present = false;
			System.out.println("Finding all linnks in a page");
			List<WebElement> list = driver.findElements(By.tagName("a"));
			if (list.size() > 0)
			{
				for(WebElement e : list)
				{
					System.out.println("link text" + e.getText());
					if(e.getText() == "Reva Kumar")
					{
						System.out.println("link found" + e.getText());
						present = true;
						break;
					}
				}
			}
			return present;
		}
		public static boolean chkIfPresentInList(WebElement element,String str)
		{
			List<WebElement> tabElements = element.findElements(By.tagName("li"));
			boolean present = false;
			for(WebElement t : tabElements)
			{
				System.out.println("Salesforce tabbar item :" + t.getText());
				if(str.equalsIgnoreCase(t.getText()))
				{
					present = true;
					break;
				}
			}
			return present;
		}
		
		//Unselect CheckBox
			public static void unselectCheckBox( WebElement element) 
			{
				if(element.isSelected())	
				{
					clickOn(element);
				}
			}
			
			public static void selectCheckBox(WebElement element) 
			{
				if(!(element.isSelected()))	
					clickOn(element);
					
			}
			public static boolean isCheckBoxSelected( WebElement element)
			{
				return element.isSelected();
			}
		//Switch control to Alert box
	 
		public static void acceptAlert()
		{
			driver.switchTo().alert().accept();
			System.out.println("Ok - accpeted in alert");
		}
		
		public static void switchToAlertBox()
		{
		Alert alert = driver.switchTo().alert();
		System.out.println("switched control to alert");
		}
	
		public static void switchToFrame(String frameId)
		{
			driver.switchTo().frame(frameId);
			System.out.println("switched control to frame");
		}
		public static void switchToFrame(int index)
		{
			driver.switchTo().frame(index);
			System.out.println("switched control to frame");
		}
		public static void switchToFrame(WebElement element)
		{
			driver.switchTo().frame(element);
			System.out.println("switched control to frame");
		}
	
		public static void goToParentFrame()
		{
			driver.switchTo().parentFrame();
			System.out.println("Control back to Parent Frame");
		}
		
		public static void moveTo(WebElement element)
		{
			Actions act = new Actions(driver);
			if(element.isEnabled())
			{
	    		act.moveToElement(element).build().perform();
	    		
	    		System.out.println(" Element enabled nd moved to");
			}
			else
				System.out.println("Element not moved to");
		}
		//Click on a webelement
			public static void clickOn(WebElement element)
			{
				Actions act = new Actions(driver);
				if(element.isEnabled())
				{
					act.click(element).build().perform();
				}
				
			}
			public static String getDate()
			{
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddhhmm");
				LocalDateTime now = LocalDateTime.now(); 
				String s = now.format(dtf);
				return s;
			}
			public static void screenShot(String fileName)
			{
				TakesScreenshot scr = (TakesScreenshot) driver;
				File sourceFile = scr.getScreenshotAs(OutputType.FILE);
				String location = SalesforceAutomationConstants.SCREEN_SHOT_PATH + fileName;
				File scrFile = new File(location);
				try {
					FileUtils.copyFile(sourceFile, scrFile);
					report.logTestInfo("Screen shot captured");
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
			
			public static String getScreenshotBase64()
			{
				TakesScreenshot scr = (TakesScreenshot) driver;
				String img = scr.getScreenshotAs(OutputType.BASE64);
				return img;
			}
			
}
			
			