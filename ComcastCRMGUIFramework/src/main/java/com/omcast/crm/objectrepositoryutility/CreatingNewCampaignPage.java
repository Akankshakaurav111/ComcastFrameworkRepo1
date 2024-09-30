package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewCampaignPage 
{
	WebDriver driver;
	
	
public CreatingNewCampaignPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

@FindBy(name = "campaignname")
private WebElement campaignTextField;

@FindBy(xpath = "//input[@value='T']")
private WebElement assignToRdioBtn;

@FindBy(name = "assigned_group_id")
private WebElement assignToDD;

@FindBy(name = "campaigntype")
private WebElement campaignType;

@FindBy(id = "campaign_no")
private WebElement campaignNoTxtField;


@FindBy(id = "targetaudience")
private WebElement target;

@FindBy(name = "campaignstatus")
private WebElement campaignStatusDD;

@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']")
private WebElement productBtn;

@FindBy(name = "closingdate")
private WebElement closingDate;

//@FindBy(id = "targetsize")
//private WebElement targetTxtFld;
//
//@FindBy(name = "productname")
//private WebElement productNameTextField;
//
//@FindBy(name = "button")
//private WebElement btn;

@FindBy(id = "search_txt")
private WebElement childWindowTextField;

@FindBy(name = "search")
private WebElement childwindowSearch;

@FindBy(name = "button")
private WebElement campaignSaveBtn;

public void creatingNewCampaignPage(String campaign,String assignDD,String campaignTypeDD,String CampaignStatusDD,String product) throws InterruptedException
{
    
	campaignTextField.sendKeys(campaign);
	assignToRdioBtn.click();
	
	WebDriverUtility wLib=new WebDriverUtility();
	wLib.select(assignToDD, assignDD);
	
	wLib.select(campaignType, campaignTypeDD);
	
	Actions act=new Actions(driver);
	act.sendKeys(campaignNoTxtField, Keys.TAB).perform();
	//campaignNoTxtField.clear();
	act.sendKeys(campaignNoTxtField, "avbd" ).perform();;
	
	
	//target.sendKeys(CampaignNo);
	
    wLib.select(campaignStatusDD, CampaignStatusDD);
    
    productBtn.click(); 
    
    wLib.switchToTabOnURL(driver, "Products&action");
    childWindowTextField.sendKeys(product);
    childwindowSearch.click();
    driver.findElement(By.xpath("//a[text()='"+product+"']")).click();
    
    wLib.switchToTabOnURL(driver, "Campaigns&action");
    JavaUtility jLib=new JavaUtility();
    String date = jLib.getRequriedDateYYYYDDMM(15);
    System.out.println(date);
    closingDate.clear();
    closingDate.sendKeys(date);
    
    campaignSaveBtn.click();
    
    
//    
    
    
}
}
