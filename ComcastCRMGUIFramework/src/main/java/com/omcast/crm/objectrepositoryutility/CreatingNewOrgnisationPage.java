package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrgnisationPage
{
	WebDriver driver;
	public CreatingNewOrgnisationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(name = "accountname")
private WebElement orgNameEdit;

public WebElement getOrgNameEdit() {
	return orgNameEdit;
}

public WebElement getSaveBtn() {
	return saveBtn;
}

//IF WE ARE CREATING BUSINESS METHODS THEN NO NEED OF CALL GETTER METHOD FOR PRIVATE KEYWORDS


@FindBy(name = "button")
private WebElement saveBtn;

@FindBy(name="industry")
private WebElement industryDropDown;

@FindBy(name="accounttype")
private WebElement typeDropDown;



@FindBy(id = "phone")
private WebElement phoneEdit;

public WebElement getPhoneEdit() {
	return phoneEdit;
}

public void createOrg(String orgName)
{
	orgNameEdit.sendKeys(orgName);
	saveBtn.click();
}

public void createOrg(String orgName,String industry,String type)
{
	orgNameEdit.sendKeys(orgName);
	Select select=new Select(industryDropDown);
	select.selectByVisibleText(industry);
	Select sel=new Select(typeDropDown);
	sel.selectByVisibleText(type);
	saveBtn.click();
}
public void createOrg(String orgName,String phoneno)
{
	orgNameEdit.sendKeys(orgName);
	phoneEdit.sendKeys(phoneno);
	saveBtn.click();
}

}

