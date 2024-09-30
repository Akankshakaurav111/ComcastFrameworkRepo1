package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrgnizationInformationPage 
{
	WebDriver driver;
	public CreateOrgnizationInformationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(className = "dvHeaderText")
private WebElement headerMsg;

@FindBy(id = "mouseArea_Phone")
private WebElement Phonemsg;


public WebElement getPhonemsg() {
	return Phonemsg;
}

public WebElement getHeaderMsg()
{
	return headerMsg;
}
@FindBy(id = "mouseArea_Industry")
private WebElement industryMsg;

public WebElement getIndustryMsg() {
	return industryMsg;
}

public WebElement getTypeMsg() {
	return typeMsg;
}
@FindBy(id = "dtlview_Type")
private WebElement typeMsg;



public WebElement getOrgName() {
	return orgName;
}
@FindBy(id = "dtlview_Organization Name")
private WebElement orgName;


public void orgAllVerificationMsg(String org)
{
	String actualOrgName = orgName.getText();
	if(actualOrgName.contains(org))
		System.out.println(org+" ORGNISATION NAME VERIFIED===PASS");
	else
		System.out.println(org+" ORGNISATION NAME NOT VERIFIED===FAIL");
//	
//	String actualOrgHeaderMsg = headerMsg.getText();
//	if(actualOrgHeaderMsg.contains(header))
//		System.out.println(header+" HEADER MESSAGE VERIFIED===PASS");
//	else
//		System.out.println(header+" HEADER MESSAGE NOT VERIFIED===FAIL");

}





}