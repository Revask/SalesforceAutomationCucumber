package com.Runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
					features = {"src\\test\\resources\\SalesforceLogin.feature"},
					glue = {"com.Steps"}
				)
public class SalesforceRunner extends AbstractTestNGCucumberTests
{
	
}
