package com.Salesforce.Automation.Utility;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import java.io.File;

public class GenerateReports {
	
		public static ExtentHtmlReporter sparkReporter;
		public static ExtentReports report;
		public static ExtentTest testlogger;
		private static GenerateReports ob;
		 static String testCaseName = null;
		 
		 private GenerateReports()
		 {
		 }
		 
		 public static GenerateReports getInstance()
		 {
			 if(ob==null)
			 {
				 ob=new GenerateReports();
			 }
			 return ob;
		 }
		public static void startExtentReport()
		{
			try {
				sparkReporter = new ExtentHtmlReporter(SalesforceAutomationConstants.GENERATE_REPORT_PATH);
			} catch (Exception e) {
				e.printStackTrace();
			}
			report = new ExtentReports();
			
			sparkReporter.config().setDocumentTitle("Salesforce Test Automation Report");
			sparkReporter.config().setReportName("Salesforce Tests");
			sparkReporter.config().setTheme(Theme.STANDARD);
			
			report.attachReporter(sparkReporter);
			report.setSystemInfo("Host Name", "Salesforce");
			report.setSystemInfo("Environment","Automation Testing");
			report.setSystemInfo("User Name", "Rsk");
								
		}
		
		public void startSingleTestReport(String testName)
		{
			testCaseName = testName;
			
			testlogger = report.createTest(testName);
		}
		
		public void logTestpassed()
		{
			testlogger.log(Status.PASS,MarkupHelper.createLabel(testCaseName + "is PASS ", ExtentColor.GREEN));
		}
		
		public void logTestInfo(String message)
		{
			testlogger.log(Status.INFO,message);
		}
		public void logTestFailed()
		{
			testlogger.log(Status.FAIL,MarkupHelper.createLabel(testCaseName + " is FAIL ", ExtentColor.RED));
		}
		public void logTestSkipped()
		{
			testlogger.log(Status.SKIP,MarkupHelper.createLabel(testCaseName + " is SKIPPED ", ExtentColor.ORANGE));
		}
		public void endReport()
		{
			report.flush();
		}
		public void attachScrshot(String fname) throws Exception
		{
			try
			{
			testlogger.addScreenCaptureFromPath(SalesforceAutomationConstants.SCREEN_SHOT_PATH + fname);
			}catch(Exception e)
			{
				testlogger.log(Status.INFO,"Screenshot not found");
			}
		}
		public void logTestFailedWithException(Throwable throwable)
		{
			testlogger.log(Status.FAIL,throwable);
		}
	/*	public void attachScrshotBase64(String img) throws Exception
		{
			testlogger.addScreenCaptureFromBase64String(img);
		}
		
		
		*/
}

	