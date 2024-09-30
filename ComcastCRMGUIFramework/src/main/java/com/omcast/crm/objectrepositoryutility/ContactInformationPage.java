package com.omcast.crm.objectrepositoryutility;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class ContactInformationPage
{
	WebDriver driver;
public ContactInformationPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement contactLastName;
	public WebElement getContactLastName() {
		return contactLastName;
	}
	public WebElement getContactHeaderMsg() {
		return contactHeaderMsg;
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactHeaderMsg;
	

	
	public void headerMessage(String lastName,String header) throws EncryptedDocumentException, IOException
	{
		String actualLastName= contactLastName.getText();
		if(actualLastName.contains(lastName))
			System.out.println(lastName+" LASTNAME VERIFIED===PASSED");
		else
			System.out.println(lastName+" LASTNAME NOT VERIFIED===FAILED");

		String actualContactHeaderMsg = contactHeaderMsg.getText();
       if(actualContactHeaderMsg.contains(header))
	    	System.out.println(lastName+" HEADER MESSAGE VERIFIED===PASS");
	    else 
	    	System.out.println(lastName+" HEADER MESSAGE NOT VERIFIED===FAIL");
	}
}
