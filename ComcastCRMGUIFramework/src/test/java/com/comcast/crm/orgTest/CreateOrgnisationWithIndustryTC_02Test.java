package com.comcast.crm.orgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.omcast.crm.objectrepositoryutility.CreateOrgnizationInformationPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewOrgnisationPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;
import com.omcast.crm.objectrepositoryutility.OrgnizationPage;

public class CreateOrgnisationWithIndustryTC_02Test 
{	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		WebDriver driver;
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		String orgName=eLib.getDataFromExcel("org", 4, 2)+jLib.getRandomNumber();
		String industryName = eLib.getDataFromExcel("org", 4, 3);//we can use toString method instead of getStringCellValue
		String industryType = eLib.getDataFromExcel("org", 4, 4);

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new EdgeDriver();
		}
		//LOGIN ITNO APP
		wLib.waitForPageToLoad(driver);
		//driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.loginToApplication(URL,USERNAME, PASSWORD);
		// STEP 2=NAVIGATE TO ORGNISATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getOrgnizationLink().click();;
		// STEP 3=CLICK ON "CREATE ORGNISATION" BUTTON
        OrgnizationPage og=new OrgnizationPage(driver);
        og.getCreateNewOrgnization().click();
		
		
		
		// STEP 4=ENTER ALL THE DETAILS & CREATE NEW ORGNISATION
  
        CreatingNewOrgnisationPage cnop=new CreatingNewOrgnisationPage(driver);
        cnop.createOrg(orgName, industryName, industryType);
		
        
	

        //1.VERIFY industries and type info
        CreateOrgnizationInformationPage oip=new CreateOrgnizationInformationPage(driver);
        String actualIndustryName = oip.getIndustryMsg().getText();
		if(actualIndustryName.equals(industryName))
		{
			System.out.println(industryName+" is verified==pass");
		}
		else
		{
			System.out.println(industryName+" is not verified==fail");
		}
		CreateOrgnizationInformationPage oip1=new CreateOrgnizationInformationPage(driver);
		String actualTypeName = oip1.getTypeMsg().getText();
		if(actualTypeName.equals(industryType))
		{
			System.out.println(industryType+" is verified==pass");
		}
		else
		{
			System.out.println(industryType+" is not verified==fail");
		}
		
		// STEP 5=LOGOUT
		
		hp.logOut();
		driver.quit();

	}
}
