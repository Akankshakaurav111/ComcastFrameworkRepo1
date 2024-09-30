package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import com.omcast.crm.objectrepositoryutility.ContactPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDate
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		JavaUtility jLib=new JavaUtility();
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		WebDriver driver;
		

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL =fLib.getDataFromPropertiesFile("url");
		String USERNAME =fLib.getDataFromPropertiesFile("username");
		String PASSWORD =fLib.getDataFromPropertiesFile("password");

		
		
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();//we can use toString method instead of getStringCellValue
        
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new EdgeDriver();
		}
		//LOGIN ITO APP
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.loginToApplication(URL,USERNAME, PASSWORD);
//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("submitButton")).click();
		
		
		
		// STEP 2=NAVIGATE TO ORGNISATION MODULE
		HomePage hp=new HomePage(driver);
		hp.getContactsLink().click();
	    //driver.findElement(By.linkText("Contacts")).click();

		// STEP 3=CLICK ON "CREATE ORGNISATION" BUTTON
		//driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
        ContactPage cp=new ContactPage(driver);
         cp.getCreatingNewContactButton().click();
		  String startDate=jLib.getSystemDateYYYYDDMM();
        String endDate=jLib.getRequriedDateYYYYDDMM(30);
//        CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
//        cncp.getLastNameEdit().sendKeys(lastName);
//        Thread.sleep(1000);
//        cncp.getStartDatetxtField().clear();
//        Thread.sleep(1000);
//        cncp.getStartDatetxtField().sendKeys(startDate);
//        Thread.sleep(1000);
//        cncp.getEndDatetxtField().clear();
//        Thread.sleep(1000);
//        cncp.getEndDatetxtField().sendKeys(endDate);
//        cncp.getSaveButton().click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.id("jscal_field_support_start_date")).clear();
		driver.findElement(By.id("jscal_field_support_start_date")).sendKeys(startDate);
		driver.findElement(By.id("jscal_field_support_end_date")).clear();
		driver.findElement(By.id("jscal_field_support_end_date")).sendKeys(endDate);
		driver.findElement(By.name("button")).click();
		
		//VERIFY SUPPORT START DATE EXPECTED RESULT
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
		// STEP 5=LOGOUT
		hp.logOut();
	    driver.quit();

	}
}
