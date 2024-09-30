package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
public WebElement getOrgnizationLink() {
		return orgnizationLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}
@FindBy(linkText = "Organizations")
private WebElement orgnizationLink;

@FindBy(linkText = "Contacts")
private WebElement contactsLink;

public WebElement getMoreLink() {
	return moreLink;
}
public WebElement getCampaaignLink() {
	return campaignLink;
}
@FindBy(linkText = "More")
private WebElement moreLink;


@FindBy(linkText = "Campaigns")
private WebElement campaignLink;

public WebElement getAdminImg() {
	return adminImg;
}

public WebElement getSignOutLink() {
	return signOutLink;
}

@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
private WebElement adminImg;

@FindBy(linkText = "Sign Out")
private WebElement signOutLink;

@FindBy(name = "Service Contracts")
private WebElement serviceLink;

public WebElement getServiceLink() {
	return serviceLink;
}

@FindBy(linkText = "Opportunities")
private WebElement opportunityLink;

@FindBy(linkText = "Products")
private WebElement productLink;

public WebElement getProductLink() {
	return productLink;
}
public WebElement getOpportunityLink() {
	return opportunityLink;
}

@FindBy(linkText = "Invoice")
private WebElement invoiceLink;

@FindBy(linkText = "Sales Order")
private WebElement salesorderLink;

public void naviagteToCompaginPage()
{
	Actions act=new Actions(driver);
	act.moveToElement(moreLink).perform();
	act.moveToElement(campaignLink).click().perform();
	
}

public void naviagteToInvoicePage()
{
	Actions act=new Actions(driver);
	act.moveToElement(moreLink).perform();
	act.moveToElement(invoiceLink).click().perform();
}

public void naviagteToSalesOrderPage()
{
	Actions act=new Actions(driver);
	act.moveToElement(moreLink).perform();
	act.click(salesorderLink).perform();
}

public void logOut()
{
	Actions action=new Actions(driver);
	action.moveToElement(adminImg).perform();
	signOutLink.click();
}
}
