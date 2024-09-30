package com.comcast.crm.opportunity;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.comcast.basetest.BaseClass;
import com.omcast.crm.objectrepositoryutility.CreatingNewOpportunityPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;
import com.omcast.crm.objectrepositoryutility.OpportunityInformationPage;
import com.omcast.crm.objectrepositoryutility.OpportunityPage;

public class CreateOpportunity extends BaseClass
{
@Test
public void createOpportunity() throws EncryptedDocumentException, IOException
{
	String opportunityName = eLib.getDataFromExcel("opportunity", 1, 2);
	String relatedToDD = eLib.getDataFromExcel("opportunity", 1, 3);
	String assignedToDD = eLib.getDataFromExcel("opportunity", 1, 4);
	String salesStageDD = eLib.getDataFromExcel("opportunity", 1, 5);
	String lastName = eLib.getDataFromExcel("opportunity", 1, 6);


	HomePage hp=new HomePage(driver);
	hp.getOpportunityLink().click();
	
	OpportunityPage op=new OpportunityPage(driver);
	op.getCreateNewOpportunityBtn().click();
	
	 
	CreatingNewOpportunityPage cnop=new CreatingNewOpportunityPage(driver);
	cnop.creatingNewOpportunityPage(opportunityName, lastName, relatedToDD, assignedToDD, salesStageDD);
//    driver.findElement(By.xpath(("//a[text()='Singh']"))).click();
	
	OpportunityInformationPage oip=new OpportunityInformationPage(driver);
	String actualHeaderInfo = oip.getHeaderMsg().getText();
	if(actualHeaderInfo.contains(opportunityName))
	{
		System.out.println("pass");
	}
	else
	{
		System.out.println("fail");
	}

}
}
