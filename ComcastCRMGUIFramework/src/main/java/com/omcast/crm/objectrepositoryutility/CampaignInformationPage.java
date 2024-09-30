package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInformationPage 
{
WebDriver driver;
public CampaignInformationPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
@FindBy(xpath = "//span[@class='dvHeaderText']")
private WebElement headerMsg;


public void headerMsg(String compaign)
{
	String actHeaderMsg = headerMsg.getText();
	if(actHeaderMsg.contains(compaign))
	{
		System.out.println("Passed");
	}
	else
	{
		System.out.println("Failed");
	}
}
}
