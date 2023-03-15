package com.Salesforce.Automation.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SalesforceCommonUtilities {
	
	public static String getLoginUrl()
	{
		String path = SalesforceAutomationConstants.DATAFILE_PATH;
		String url = null;
		try
		{
		FileInputStream fis = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fis);
		url = (prop.getProperty("path"));
		}catch (FileNotFoundException e)
		{
			System.out.println("file not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
				
	}
	public static String getLoginId()
	{
		String path = SalesforceAutomationConstants.DATAFILE_PATH;
		String loginId = null;
		try
		{
		FileInputStream fis = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fis);
		loginId = prop.getProperty("loginid");
		}catch (FileNotFoundException e)
		{
			System.out.println("file not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginId;
	}
	public static String getLoginpwd()
	{
		String path = SalesforceAutomationConstants.DATAFILE_PATH;
		String pwd = null;
		try
		{
		FileInputStream fis = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fis);
		pwd = prop.getProperty("passwd");
		}catch (FileNotFoundException e)
		{
			System.out.println("file not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pwd;
	}
	
	public static String getExpectedValue(String key)
	{
		String path = SalesforceAutomationConstants.EXPECTED_RESULTS_VALUES_PATH;
		String expectedValue ="";
		try
		{
			FileInputStream fis = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(fis);
			expectedValue = prop.getProperty(key);
		}catch (FileNotFoundException e)
		{
			System.out.println("file not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expectedValue;
	}
	
	public static String getScreenshotPath()
	{
		return SalesforceAutomationConstants.SCREEN_SHOT_PATH;
	}
}
