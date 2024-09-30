package com.comcast.crm.orgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.comcast.basetest.BaseClass;
import com.omcast.crm.objectrepositoryutility.CreateOrgnizationInformationPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewOrgnisationPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;
import com.omcast.crm.objectrepositoryutility.OrgnizationPage;
@Listeners(com.comcast.crm.listenerutility.LisenerImplementationClass.class)
public class CreateOrgnisationTestTc_01 extends BaseClass
{
@Test
public void createOrg() throws EncryptedDocumentException, IOException, InterruptedException
	{		
	
	 UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String orgName =eLib.getDataFromExcel("org", 1, 2)  +jLib.getRandomNumber();//we can use toString method instead of getStringCellValue		
		// STEP 2=NAVIGATE TO ORGNISATION MODULE
		 UtilityClassObject.getTest().log(Status.INFO, "Navigate to org");
        hp.getOrgnizationLink().click();
		Thread.sleep(1000);

		// STEP 3=CLICK ON "CREATE ORGNISATION" BUTTON
		 UtilityClassObject.getTest().log(Status.INFO, "create Orgnization");
         OrgnizationPage og=new OrgnizationPage(driver);
         og.getCreateNewOrgnization().click();
 		 Thread.sleep(1000);

		// STEP 4=ENTER ALL THE DETAILS & CREATE NEW ORGNISATION
 		 UtilityClassObject.getTest().log(Status.INFO, "create new org page");
         CreatingNewOrgnisationPage cnop=new CreatingNewOrgnisationPage(driver);
         cnop.createOrg(orgName);
        // verify header mssg expected result
         UtilityClassObject.getTest().log(Status.PASS, "passed");
         CreateOrgnizationInformationPage oip=new CreateOrgnizationInformationPage(driver);
         String actualOrgName=oip.getHeaderMsg().getText();
         
         
         
         boolean flag = actualOrgName.contains(orgName);
        Assert.assertTrue(flag);
//         if(actualOrgName.contains(orgName))
//         {
//        	 System.out.println(orgName+" name is verified===pass");
//         }
//         else
//         
//        	 {
//            	 System.out.println(orgName+" name is not verified===fail");
//             }  
	}
@Test
public void createOrgWithindustry() throws EncryptedDocumentException, IOException
{
	 UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
	String orgName=eLib.getDataFromExcel("org", 4, 2)+jLib.getRandomNumber();
	String industryName = eLib.getDataFromExcel("org", 4, 3);//we can use toString method instead of getStringCellValue
	String industryType = eLib.getDataFromExcel("org", 4, 4);

	// STEP 2=NAVIGATE TO ORGNISATION MODULE
	 UtilityClassObject.getTest().log(Status.INFO, "Navigate to org");
			hp.getOrgnizationLink().click();;
			
			// STEP 3=CLICK ON "CREATE ORGNISATION" BUTTON
			UtilityClassObject.getTest().log(Status.INFO, "create Orgnization");
	        OrgnizationPage og=new OrgnizationPage(driver);
	        og.getCreateNewOrgnization().click();
					
			// STEP 4=ENTER ALL THE DETAILS & CREATE NEW ORGNISATION
	        UtilityClassObject.getTest().log(Status.INFO, "create new org page");
	        CreatingNewOrgnisationPage cnop=new CreatingNewOrgnisationPage(driver);
	        cnop.createOrg(orgName, industryName, industryType);
			
	        //1.VERIFY industries and type info
	        CreateOrgnizationInformationPage oip=new CreateOrgnizationInformationPage(driver);
	       // String actualIndustryName = oip.getIndustryMsg().getText();
	        
	       
	       
	        //Assertions
	        //HardAssert for orgName and Industry Name
	        //soft assert for Industry type
	         UtilityClassObject.getTest().log(Status.PASS, "passeds");
	         
	        String actualOrgName = oip.getOrgName().getText();
	        Assert.assertEquals(actualOrgName,orgName);
	        
	        //boolean displayed = oip.getIndustryMsg().isDisplayed();
	       // System.out.println(displayed);
	        String actualIndName = oip.getIndustryMsg().getText();
	        Assert.assertEquals(actualIndName, industryName);
	        
	        String actualIndType = oip.getTypeMsg().getText();
	        SoftAssert sf = new SoftAssert();
	        sf.assertEquals(actualIndType, industryType);
	        sf.assertAll();
	        
	        
	        
	        
//			if(actualIndustryName.equals(industryName))
//			{
//				System.out.println(industryName+" is verified==pass");
//			}
//			else
//			{
//				System.out.println(industryName+" is not verified==fail");
//			}
//			CreateOrgnizationInformationPage oip1=new CreateOrgnizationInformationPage(driver);
//			String actualTypeName = oip1.getTypeMsg().getText();
//			if(actualTypeName.equals(industryType))
//			{
//				System.out.println(industryType+" is verified==pass");
//			}
//			else
//			{
//				System.out.println(industryType+" is not verified==fail");
//			}
//			
}
@Test
public void createOrgWithPhoneNumber() throws EncryptedDocumentException, IOException
{
	String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();//we can use toString method instead of getStringCellValue	
	String phoneNumber = eLib.getDataFromExcelUsingDataFormatter("org", 7, 4);
	
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
    Assert.assertEquals(actualPhoneNumber, phoneNumber);
    System.out.println("*********************");
    
//	if(actualPhoneNumber.contains(phoneNumber))
//	{
//		System.out.println(phoneNumber+" information is Verified===Pass");
//	}
//	else
//	{
//		System.out.println(phoneNumber+" information is not Verified===fail");
//	}
	
}
}
