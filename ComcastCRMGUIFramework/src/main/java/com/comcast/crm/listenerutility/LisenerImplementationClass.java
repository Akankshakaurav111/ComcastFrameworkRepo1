package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.crm.comcast.basetest.BaseClass;

public class LisenerImplementationClass extends BaseClass implements ITestListener,ISuiteListener
{
	//public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");

		//SPARK REPORT CONFIGURATION
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report_"+time+" .html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//ADD ENVIORMENT CONFIGURATION
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window11");
		report.setSystemInfo("BROWSER", "CHROME");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	//it will execute automatically before executing each test
	public void onTestStart(ITestResult result) {
		System.out.println("====="+result.getMethod().getMethodName()+"===Start===");
	    test = report.createTest(result.getMethod().getMethodName());
	    UtilityClassObject.setTest(test);
	    test.log(Status.INFO, result.getMethod().getMethodName()+"===>STARTED<===");

	}

	@Override
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("====="+result.getMethod().getMethodName()+"===END===");
	//	test = report.createTest(result.getMethod().getMethodName());
	    test.log(Status.PASS, result.getMethod().getMethodName()+"===>COMPLETED<===");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		//UtilityClassObject.getDriver();
		TakesScreenshot ts=(TakesScreenshot)(UtilityClassObject.getDriver());
		String srcFile = ts.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
       // String date = new JavaUtility().getSystemDateYYYYDDMM();
		test.addScreenCaptureFromBase64String(srcFile,testName+"_"+time);
	//	test = report.createTest(result.getMethod().getMethodName());
	    test.log(Status.FAIL, result.getMethod().getMethodName()+"===>FAILED<===");


	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

	
}
