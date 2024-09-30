package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage 
{
WebDriver driver;
public CampaignPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);	
}

@FindBy(xpath = "//img[@alt='Create Campaign...']")
private WebElement creatingNewCampaignBtn;


public WebElement getCreatingNewCampaignBtn() {
	return creatingNewCampaignBtn;
}
}
