package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewServiceContractPage 
{
	WebDriver driver;

	public CreatingNewServiceContractPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
@FindBy(id = "subject")
private WebElement subjectTextField;
	

public WebElement getSubjectTextField() {
	return subjectTextField;
}


public WebElement getSaveButton() {
	return saveButton;
}

@FindBy(name = "button")
private WebElement saveButton;
}
