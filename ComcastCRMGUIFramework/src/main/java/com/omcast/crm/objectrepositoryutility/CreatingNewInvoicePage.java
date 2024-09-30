package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewInvoicePage 
{
	WebDriver driver;
public CreatingNewInvoicePage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
@FindBy(name = "subject")
private WebElement subjectTxtField;

@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[2]")
private WebElement contactBtn;

@FindBy(id = "search_txt")
private WebElement contactChildWndowTxt;

@FindBy(name = "search")
private WebElement searchInContactChldWnd;

@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[3]")
private WebElement orgnizationBtn;

@FindBy(id = "search_txt")
private WebElement orgChildWndowTxt;

@FindBy(name = "search")
private WebElement searchInOrgChldWnd;

@FindBy(xpath = "//input[@value='T']")
private WebElement assignRdoBtn;

@FindBy(name = "assigned_group_id")
private WebElement assignToDD;

@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[1]")
private WebElement salesOdrBtn;

@FindBy(name = "invoicestatus")
private WebElement statusDD;

public void creatingInvoicePage(String s,String contact,String org,String assigndd,String Statusdd) throws InterruptedException
{
	subjectTxtField.sendKeys(s);
	
	contactBtn.click();
	
	WebDriverUtility wLib=new WebDriverUtility();
	wLib.switchToTabOnURL(driver, "Contacts&action");
	contactChildWndowTxt.sendKeys(contact);
	searchInContactChldWnd.click();
	driver.findElement(By.xpath("//a[contains(text(),'"+contact+"')]")).click();
	driver.switchTo().alert().accept();
Thread.sleep(2000);
	wLib.switchToTabOnURL(driver, "module=Invoice&action");
	
	orgnizationBtn.click();
	wLib.switchToTabOnURL(driver, "Accounts&action");
	orgChildWndowTxt.sendKeys(org);
	searchInOrgChldWnd.click();
	driver.findElement(By.xpath("//a[contains(text(),'"+org+"')]")).click();
	driver.switchTo().alert().accept();
	wLib.switchToTabOnURL(driver, "module=Invoice&action");
	
	assignRdoBtn.click();
	wLib.select(assignToDD, assigndd);
	
	salesOdrBtn.click();
	
	wLib.select(statusDD, Statusdd);
		
}
}
