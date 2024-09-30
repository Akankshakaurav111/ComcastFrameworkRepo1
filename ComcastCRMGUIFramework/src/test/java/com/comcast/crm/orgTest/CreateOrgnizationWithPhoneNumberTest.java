package com.comcast.crm.orgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.omcast.crm.objectrepositoryutility.CreateOrgnizationInformationPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewOrgnisationPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;
import com.omcast.crm.objectrepositoryutility.OrgnizationPage;

public class CreateOrgnizationWithPhoneNumberTest 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		WebDriver driver;
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		

		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();//we can use toString method instead of getStringCellValue
		
		String phoneNumber = eLib.getDataFromExcelUsingDataFormatter("org", 7, 4);
//		row=sheet.getRow(7);
//	    Cell cell = row.getCell(4);
//	    eLib.getDataFromExcel("org", 7, 4);
//	    DataFormatter data = new DataFormatter();
//	    String phoneNumber = data.formatCellValue(cell);
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new EdgeDriver();
		}
		//LOGIN ITO APP
		wLib.waitForPageToLoad(driver);
		//driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.loginToApplication(URL,USERNAME, PASSWORD);
		// STEP 2=NAVIGATE TO ORGNISATION MODULE
        HomePage hp=new HomePage(driver);
        hp.getOrgnizationLink().click();
	
		// STEP 3=CLICK ON "CREATE ORGNISATION" BUTTON

        OrgnizationPage op=new OrgnizationPage(driver);
        op.getCreateNewOrgnization().click();
         
        //STEP 4=ENTER ALL DETAILS & CREATE NEW ORGNIZATION
         
        CreatingNewOrgnisationPage cnop=new CreatingNewOrgnisationPage(driver);
        cnop.createOrg(orgName, phoneNumber);
        
        
		//1.VERIFY HEADER PHONE NUMBERINFORMATION EXPECTED RESULT
        CreateOrgnizationInformationPage coip=new CreateOrgnizationInformationPage(driver);
        String actualPhoneNumber = coip.getPhonemsg().getText();
		if(actualPhoneNumber.contains(phoneNumber))
		{
			System.out.println(phoneNumber+" information is Verified===Pass");
		}
		else
		{
			System.out.println(phoneNumber+" information is not Verified===fail");
		}
		
		// STEP 5=LOGOUT
		hp.logOut();
        driver.quit();
}
}
