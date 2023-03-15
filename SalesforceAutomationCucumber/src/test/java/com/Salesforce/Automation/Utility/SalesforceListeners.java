package com.Salesforce.Automation.Utility;

import org.testng.ITestListener;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import com.Salesforce.Automation.Base.*;

public class SalesforceListeners implements ITestListener
{
	public GenerateReports report = GenerateReports.getInstance();
	public void onStart(ITestContext context)
	{	
		report = GenerateReports.getInstance();
		GenerateReports.startExtentReport();
	}
	
	public void onTestStart(ITestResult result)
	{
		report.startSingleTestReport(result.getName());
		report.logTestInfo(result.getName() +"  test execution started");	
	}
		
	public void onFinish(ITestContext context) 
	{
		report.logTestInfo(context.getName()+" tests finished execution");	
		report.endReport();
	}
	
	public void onTestSuccess(ITestResult result)
	{
		report.logTestpassed();
	}
	
	public void onTestFailure(ITestResult result)
	{
		String fname = result.getName()+BasePage.getDate()+".png";
		BasePage.screenShot(fname);	
		report.logTestFailed();
		try
		{
		report.attachScrshot(fname);
		}catch(Exception e)
		{
			report.logTestInfo("Screenshot not found to attach to report");
		}
		//report.attachScrshotBase64(getScreenshotBase64());
		report.logTestFailedWithException(result.getThrowable());
			
	}
	
	
}
