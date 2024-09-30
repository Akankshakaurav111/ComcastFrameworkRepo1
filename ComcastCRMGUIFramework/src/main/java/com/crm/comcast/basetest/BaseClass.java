package com.crm.comcast.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.comcast.generic.databaseutility.DataBaseUtility;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass
{
	public  WebDriver driver;
	//public static WebDriver sdriver=null;
	public DataBaseUtility dbLib=new DataBaseUtility();
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public HomePage hp;
	
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS()
	{
		System.out.println("===Connect to DB , Report confige");
		//dbLib.getDbConnection();
		
	}
//	@org.testng.annotations.Parameters("a")
//	@BeforeTest
//	public void confirbt(String a)
//	{
//		System.out.println("BEFORE TEST");
//		System.out.println("===="+a+"====");
//	}
//	
	
   // @org.testng.annotations.Parameters("Browser")
	
	//@org.testng.annotations.Parameters("BROWSER")
	
	
	
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC() throws IOException //String browser
	{
		System.out.println("===LAUNCH BROWSER==");
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		//String BROWSER = browser;
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else
		{
			driver=new EdgeDriver();
		}
		wLib.maximizeTheWindow(driver);
		//sdriver=driver;
		
		UtilityClassObject.setDriver(driver);
	}
	
	
    //@org.testng.annotations.Parameters("bm")
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws IOException//String bmvalue
	{
		hp=new HomePage(driver);
		System.out.println("===LOGIN===");
		//System.out.println("*****"+bmvalue+"**********");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		LoginPage lp=new LoginPage(driver);
		lp.loginToApplication(URL, USERNAME, PASSWORD);
		
	}
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM()
	{
		System.out.println("===LOGOUT===");
		hp.logOut();
	}
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC()
	{
		System.out.println("===CLOSE THE BROWSER");
		driver.quit();
	}

	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS() throws SQLException
	{
		System.out.println("===Close DB ,Report BackUP");
		dbLib.closeConnection();
	
	}

}
