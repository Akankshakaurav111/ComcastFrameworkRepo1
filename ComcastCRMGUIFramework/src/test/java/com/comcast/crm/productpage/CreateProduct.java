package com.comcast.crm.productpage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.omcast.crm.objectrepositoryutility.CreatingNewCampaignPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;

public class CreateProduct 
{
public static void main(String[] args) throws IOException
{
	WebDriver driver=null;
	WebDriverUtility wLib=new WebDriverUtility();
//	JavaUtility jLib=new JavaUtility();
//	ExcelUtility eLib=new ExcelUtility();
	FileUtility fLib=new FileUtility();
	
	//READ DATA FROM PROPERTYFILE
	String BROWSER = fLib.getDataFromPropertiesFile("browser");
	String URL = fLib.getDataFromPropertiesFile("url");
	String USERNAME = fLib.getDataFromPropertiesFile("username");
	String PASSWORD = fLib.getDataFromPropertiesFile("password");
	HomePage hp=new HomePage(driver);
    
	if(BROWSER.contains("chrome"))
		driver=new ChromeDriver();
	else
		driver=new EdgeDriver();
	
	//LOGIN TO APPLICATION
	
	wLib.waitForPageToLoad(driver);
	wLib.maximizeTheWindow(driver);
    LoginPage lp=new LoginPage(driver);
    lp.loginToApplication(URL, USERNAME, PASSWORD);
    
   // HomePage hp=new HomePage(driver);
    hp.getProductLink().click();

}
}
