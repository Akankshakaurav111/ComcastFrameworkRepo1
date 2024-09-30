package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.omcast.crm.objectrepositoryutility.ChildWindowPage;
import com.omcast.crm.objectrepositoryutility.ContactInformationPage;
import com.omcast.crm.objectrepositoryutility.ContactPage;
import com.omcast.crm.objectrepositoryutility.CreateOrgnizationInformationPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewOrgnisationPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;
import com.omcast.crm.objectrepositoryutility.OrgnizationPage;

public class CreateContactWithOrgnisation 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		WebDriverUtility wLib=new WebDriverUtility();
		
		WebDriver driver;
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL =fLib.getDataFromPropertiesFile("url");
		String USERNAME =fLib.getDataFromPropertiesFile("username");
		String PASSWORD =fLib.getDataFromPropertiesFile("password");

		String orgName=eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNumber();
		String contactLastName=eLib.getDataFromExcel("contact", 7, 3);

		
		if (BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		} else {
			driver = new EdgeDriver();
		}
		// LOGIN INTO APP
		wLib.waitForPageToLoad(driver);
		//driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.loginToApplication(URL,USERNAME, PASSWORD);
		// STEP 2=NAVIGATE TO ORGNISATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getOrgnizationLink().click();
		//driver.findElement(By.linkText("Organizations")).click();
		
		// STEP 3=CLICK ON "CREATE ORGNISATION" BUTTON
		OrgnizationPage op=new OrgnizationPage(driver);
		op.getCreateNewOrgnization().click();
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
        
		// STEP 4=ENTER ALL THE DETAILS & CREATE NEW ORGNISATION
		CreatingNewOrgnisationPage cnop=new CreatingNewOrgnisationPage(driver);
		cnop.getOrgNameEdit().sendKeys(orgName);
		cnop.getSaveBtn().click();
		//after creating new  orgnisation we will verify two things
				//1.VERIFY HEADER INFO MESSAGE EXPECTED RESULT
				//String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		        CreateOrgnizationInformationPage oip=new CreateOrgnizationInformationPage(driver);
		        String headerInfo=oip.getHeaderMsg().getText();
		        if(headerInfo.contains(orgName))
				{
					System.out.println(orgName+" is created");
				}
				else
				{
					System.out.println(orgName+" is failed");
				}
				//2.VERIFY BY ORGNISATION NAME IN CREATING ORGNIZATION INFORMATION PAGE
		        //String actualOrgnisationName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		        String actualOrgnisationName = oip.getOrgName().getText();
		        if(actualOrgnisationName.contains(orgName))
				{
					System.out.println(orgName+" is created===PASS");
				}
				else
				{
					System.out.println(orgName+" is not created====FAIL");
				}

		// NAVIGATE TO CONTACT MODULE
		// STEP 5=NAVIGATE TO CONTACT MODULE
		hp.getContactsLink().click();		
		
		// STEP 6=CLICK ON "CREATE CONTACTS" BUTTON
		//driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
        ContactPage cp=new ContactPage(driver);
        cp.getCreatingNewContactButton().click();
		
		
		
        // STEP 7=ENTER ALL THE DETAILS & CREATE NEW CONTACT
//		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
//		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")).click();//BEFORE CLICKING SAVE BUTTON WE WILL ADD ORGNISATION NAME IN CONTACT MODULE        
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.getLastNameEdit().sendKeys(contactLastName);
		cncp.getOrgBtnInContact().click();
		
		
		
		//SWITCH TO CHILD WINDOW
		wLib.switchToTabOnURL(driver, "Accounts&action");
		ChildWindowPage cwp=new ChildWindowPage(driver);
		cwp.getChildTextField().sendKeys(orgName);
		cwp.getChildSubmtBtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();//FOR DYNAMIC XPATH WE CREATE POM CLASS
//		driver.findElement(By.name("search_text")).sendKeys(orgName);
//        driver.findElement(By.name("search")).click();
//        driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();//HERE WE CANT WRITE STATIC XPATH BECAUSE XPATH IS DYNAMIC SO WE WILL CONCAT WITH ORGNISATION BECAUSE EVERY TIME ORGNISATION NAME WILL BE CHANGE
		
		
		//SWITCH TO PARENT WINDOW
		wLib.switchToTabOnURL(driver, "Contacts&action");
		cncp.getSaveButton().click();
	
       // driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
      //1.VERIFY HEADER INFO MESSAGE EXPECTED RESULT
      		// headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
      		
		ContactInformationPage cip=new ContactInformationPage(driver);
		headerInfo=cip.getContactHeaderMsg().getText();
		if(headerInfo.contains(contactLastName))
      		{
      			System.out.println(contactLastName+" is created");
      		}
      		else
      		{
      			System.out.println(contactLastName+" is failed");
      		}
      		//2.VERIFY HEADER ORGNISATION NAME INFORMATION EXPECTED inside the contacts page 
      	    //actualOrgnisationName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				
		if(actualOrgnisationName.contains(orgName))
      		{
      			System.out.println(orgName+" is created===PASS");
      		}
      		else
      		{
      			System.out.println(orgName+" is not created====FAIL");
      		}
        
        // STEP 8=LOGOUT
		hp.logOut();
		driver.quit();
	}
}
