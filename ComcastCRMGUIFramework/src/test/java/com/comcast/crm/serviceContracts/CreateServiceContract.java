package com.comcast.crm.serviceContracts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.omcast.crm.objectrepositoryutility.CreatingNewServiceContractPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;
import com.omcast.crm.objectrepositoryutility.ServiceContractPage;

public class CreateServiceContract 
{
public static void main(String[] args) throws IOException 
{
	WebDriver driver=null;
	//CREATE OBJECT
	FileUtility fLib=new FileUtility();
	ExcelUtility eLib=new ExcelUtility();
	JavaUtility jLib=new JavaUtility();
	WebDriverUtility wLib=new WebDriverUtility();
	
	//READ DATA FROM PROPERTY FILE
	String BROWSER = fLib.getDataFromPropertiesFile("browser");
	String URL = fLib.getDataFromPropertiesFile("url");
	String USERNAME = fLib.getDataFromPropertiesFile("username");
	String PASSWORD = fLib.getDataFromPropertiesFile("password");
	
	String subjectName = eLib.getDataFromExcel("servicecontract", 1, 2);
	
	if(BROWSER.equals("chrome"))
		driver=new ChromeDriver();
	else
		driver=new EdgeDriver();

	//LOGIN TO APPLICATION
	wLib.waitForPageToLoad(driver);
	//driver.get(URL);
	LoginPage lp=new LoginPage(driver);
	lp.loginToApplication(URL,USERNAME, PASSWORD);
	
	HomePage hp=new HomePage(driver);
	hp.getMoreLink().click();
	hp.getServiceLink().click();
	
    ServiceContractPage sc=new ServiceContractPage(driver);
    sc.getServiceContractButton().click();
    
    CreatingNewServiceContractPage cnsp=new CreatingNewServiceContractPage(driver);
    cnsp.getSubjectTextField().sendKeys(subjectName);
    cnsp.getSaveButton().click();
	
}
}
