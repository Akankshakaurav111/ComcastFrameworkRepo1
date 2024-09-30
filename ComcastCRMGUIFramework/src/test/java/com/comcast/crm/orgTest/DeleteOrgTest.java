package com.comcast.crm.orgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.omcast.crm.objectrepositoryutility.CreateOrgnizationInformationPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewOrgnisationPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;
import com.omcast.crm.objectrepositoryutility.OrgnizationPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver;
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME =  fLib.getDataFromPropertiesFile("username");
		String PASSWORD =  fLib.getDataFromPropertiesFile("password");

		
		String orgName =eLib.getDataFromExcel("org", 10, 2)  +jLib.getRandomNumber();//we can use toString method instead of getStringCellValue

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
		Thread.sleep(1000);

		

		
//		// STEP 2=NAVIGATE TO ORGNISATION MODULE
        HomePage hp=new HomePage(driver);
        hp.getOrgnizationLink().click();
		Thread.sleep(1000);

		// STEP 3=CLICK ON "CREATE ORGNISATION" BUTTON
         OrgnizationPage og=new OrgnizationPage(driver);
         og.getCreateNewOrgnization().click();
 		Thread.sleep(1000);

		// STEP 4=ENTER ALL THE DETAILS & CREATE NEW ORGNISATION
         CreatingNewOrgnisationPage cnop=new CreatingNewOrgnisationPage(driver);
         cnop.createOrg(orgName);
        // verify header mssg expected result
         
         CreateOrgnizationInformationPage oip=new CreateOrgnizationInformationPage(driver);
         String actualOrgName=oip.getHeaderMsg().getText();
         if(actualOrgName.contains(orgName))
         {
        	 System.out.println(orgName+" name is verified===pass");
         }
         else
         
        	 {
            	 System.out.println(orgName+" name is not verified===fail");
             }
             
         
        
         
         //GO BACK ORG PAGE 
         hp.getOrgnizationLink().click();
         
         //AND SEARCH FOR ORGNIZATION
         og.getSearchEdit().sendKeys(orgName);
         wLib.select(og.getSearchDropDown(), "Organization Name");
         og.getSearchButton().click();

         //IN DYNAMIC WEBTABLE SELECT AND DELETE ORG
         driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();

         //STEP 5=LOGOUT
       //  hp.logOut();
         
      //   driver.quit();
	}
}
