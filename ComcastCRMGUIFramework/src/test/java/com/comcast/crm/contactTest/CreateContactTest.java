package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
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
import com.comcast.crm.listenerutility.LisenerImplementationClass;
import com.crm.comcast.basetest.BaseClass;
import com.omcast.crm.objectrepositoryutility.ChildWindowPage;
import com.omcast.crm.objectrepositoryutility.ContactInformationPage;
import com.omcast.crm.objectrepositoryutility.ContactPage;
import com.omcast.crm.objectrepositoryutility.CreateOrgnizationInformationPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewOrgnisationPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;
import com.omcast.crm.objectrepositoryutility.OrgnizationPage;
/**
 * @author Dharmendra
 * 
 */
@Listeners(com.comcast.crm.listenerutility.LisenerImplementationClass.class)
public class CreateContactTest extends BaseClass {
	@Test
	public void createContact() throws EncryptedDocumentException, IOException {
		// READ DATA FROM EXCEL FILE
	 	
        UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// STEP 2=NAVIGATE TO CONTACT MODULE
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact module");
//		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		// STEP 3=CLICK ON "CREATE CONTACTS" BUTTON
		UtilityClassObject.getTest().log(Status.INFO, "create contact");
		ContactPage cp = new ContactPage(driver);
		cp.getCreatingNewContactButton().click();

		// STEP 4=ENTER ALL THE DETAILS & CREATE NEW CONTACT
		UtilityClassObject.getTest().log(Status.INFO, "enter all the detail and create new contact");
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameEdit().sendKeys(lastName);
		cncp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.INFO, lastName+"read data from excel");
		
		ContactInformationPage cip = new ContactInformationPage(driver);
		String actHeader = cip.getContactHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);
		
		
		String actLastName = cip.getContactLastName().getText();
		SoftAssert sf=new SoftAssert();
		sf.assertEquals(actLastName, lastName);
        sf.assertAll();
		// VERIFY HEADER INFO MESSAGE EXPECTED RESULT
//		ContactInformationPage cip = new ContactInformationPage(driver);
//		cip.headerMessage(lastName, lastName);
//		String actualHeaderMsg = cip.getContactHeaderMsg().getText();
		// String actualLastName = cip.getContactLastName().getText();
//		if (actualHeaderMsg.contains(lastName)) {
//			System.out.println(lastName + " information is verified==pass");
//		} else {
//			System.out.println(lastName + " information is not verified==fail");
//
//		}
	}

	@Test
	public void createContactWithOrgnisation() throws EncryptedDocumentException, IOException {
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3);

		// STEP 2=NAVIGATE TO ORGNISATION MODULE
		HomePage hp = new HomePage(driver);
		hp.getOrgnizationLink().click();

		// STEP 3=CLICK ON "CREATE ORGNISATION" BUTTON
		OrgnizationPage op = new OrgnizationPage(driver);
		op.getCreateNewOrgnization().click();
		// driver.findElement(By.xpath("//img[@title='Create
		// Organization...']")).click();

		// STEP 4=ENTER ALL THE DETAILS & CREATE NEW ORGNISATION
		CreatingNewOrgnisationPage cnop = new CreatingNewOrgnisationPage(driver);
		cnop.getOrgNameEdit().sendKeys(orgName);
		cnop.getSaveBtn().click();

		CreateOrgnizationInformationPage oip = new CreateOrgnizationInformationPage(driver);
		oip.orgAllVerificationMsg(orgName);
//		String headerInfo = oip.getHeaderMsg().getText();
//		if (headerInfo.contains(orgName)) {
//			System.out.println(orgName + " is created");
//		} else {
//			System.out.println(orgName + " is failed");
//		}
//		// 2.VERIFY BY ORGNISATION NAME IN CREATING ORGNIZATION INFORMATION PAGE
//		String actualOrgnisationName = oip.getOrgName().getText();
//		if (actualOrgnisationName.contains(orgName)) {
//			System.out.println(orgName + " is created===PASS");
//		} else {
//			System.out.println(orgName + " is not created====FAIL");
//		}

		// NAVIGATE TO CONTACT MODULE
		// STEP 5=NAVIGATE TO CONTACT MODULE
		hp.getContactsLink().click();

		// STEP 6=CLICK ON "CREATE CONTACTS" BUTTON
		ContactPage cp = new ContactPage(driver);
		cp.getCreatingNewContactButton().click();

		// STEP 7=ENTER ALL THE DETAILS & CREATE NEW CONTACT
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameEdit().sendKeys(contactLastName);
		cncp.getOrgBtnInContact().click();

		// SWITCH TO CHILD WINDOW
		wLib.switchToTabOnURL(driver, "Accounts&action");
		ChildWindowPage cwp = new ChildWindowPage(driver);
		cwp.getChildTextField().sendKeys(orgName);
		cwp.getChildSubmtBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();// FOR DYNAMIC XPATH WE CREATE POM CLASS

		// SWITCH TO PARENT WINDOW
		wLib.switchToTabOnURL(driver, "Contacts&action");
		cncp.getSaveButton().click();

		ContactInformationPage cip = new ContactInformationPage(driver);
		String headerInfo = cip.getContactHeaderMsg().getText();
		if (headerInfo.contains(contactLastName)) {
			System.out.println(contactLastName + " is created");
		} else {
			System.out.println(contactLastName + " is failed");
		}
		// 2.VERIFY HEADER ORGNISATION NAME INFORMATION EXPECTED inside the contacts page

//		if (actualOrgnisationName.contains(orgName)) {
//			System.out.println(orgName + " is created===PASS");
//		} else {
//			System.out.println(orgName + " is not created====FAIL");
//		}

	}
	@Test
	public void createContactWithSupportDate() throws EncryptedDocumentException, IOException
	{
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();//we can use toString method instead of getStringCellValue

		// STEP 2=NAVIGATE TO ORGNISATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getContactsLink().click();
		
		ContactPage cp=new ContactPage(driver);
        cp.getCreatingNewContactButton().click();
		  String startDate=jLib.getSystemDateYYYYDDMM();
       String endDate=jLib.getRequriedDateYYYYDDMM(30);
       
        driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.id("jscal_field_support_start_date")).clear();
		driver.findElement(By.id("jscal_field_support_start_date")).sendKeys(startDate);
		driver.findElement(By.id("jscal_field_support_end_date")).clear();
		driver.findElement(By.id("jscal_field_support_end_date")).sendKeys(endDate);
		driver.findElement(By.name("button")).click();
		
		String actualSupportStartDate= driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actualSupportStartDate.equals(startDate))
		{
			System.out.println(startDate+" supportStartDate information is Created==pass");
		}
		else
		{
			System.out.println(startDate+" supportStartDate information is not Created==fail");

		}
		//VERIFY SUPPORT END DATE EXPECTED RESULT
	    String actualSupportEndDate= driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actualSupportEndDate.equals(endDate))
		{
			System.out.println(endDate+" supportEndDate information is Created==pass");
		}
		else
		{
			System.out.println(endDate+" supportEndDate information is not Created==fail");

		}
	}
}
