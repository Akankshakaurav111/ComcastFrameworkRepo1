package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityInformationPage
{
	WebDriver driver;
public OpportunityInformationPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
@FindBy(xpath = "//span[@class='dvHeaderText']")
private WebElement headerMsg;
public WebElement getHeaderMsg() {
	return headerMsg;
}
}
