package com.comcast.crm.campaign;

import java.io.IOException;
import java.text.CompactNumberFormat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.comcast.basetest.BaseClass;
import com.omcast.crm.objectrepositoryutility.CampaignInformationPage;
import com.omcast.crm.objectrepositoryutility.CampaignPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewCampaignPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewProdutcPage;
import com.omcast.crm.objectrepositoryutility.HomePage;
import com.omcast.crm.objectrepositoryutility.LoginPage;
import com.omcast.crm.objectrepositoryutility.ProductPage;

public class CreateCampaign extends BaseClass
{
@Test
public void createCampaign() throws IOException, InterruptedException
{
	
	String campaignName = eLib.getDataFromExcel("campaign", 1, 2)+jLib.getRandomNumber();
	String assignedDD = eLib.getDataFromExcel("campaign", 1, 3);
	String campaignType = eLib.getDataFromExcel("campaign", 1, 4);
	String campaignStatsDD=eLib.getDataFromExcel("campaign", 1, 6);
	String products=eLib.getDataFromExcel("campaign", 1, 7)+jLib.getRandomNumber();

    hp.getProductLink().click();
    
    ProductPage pp=new ProductPage(driver);
    pp.getCreatingNewProducts().click();
    
    CreatingNewProdutcPage cnpp=new CreatingNewProdutcPage(driver);
    cnpp.getProductNameTextField().sendKeys(products);
    cnpp.getBtn().click();
    
    hp.naviagteToCompaginPage();
    
    CampaignPage cp=new CampaignPage(driver);
    cp.getCreatingNewCampaignBtn().click();
    
	CreatingNewCampaignPage cncp=new CreatingNewCampaignPage(driver);
    cncp.creatingNewCampaignPage(campaignName,assignedDD, campaignType, campaignStatsDD, products);
      
    CampaignInformationPage cip=new CampaignInformationPage(driver);
    Thread.sleep(2000);
    cip.headerMsg(campaignName);
    

}
}
