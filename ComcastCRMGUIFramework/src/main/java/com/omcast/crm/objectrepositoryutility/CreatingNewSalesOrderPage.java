package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewSalesOrderPage
{
	WebDriver driver;
public CreatingNewSalesOrderPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
@FindBy(name = "subject")
private WebElement subjectTxtFld;

@FindBy(name = "button")
private WebElement save;
public WebElement getSubjectTxtFld() {
	return subjectTxtFld;
}

@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[4]")
private WebElement orgBtn;

@FindBy(id = "search_txt")
private WebElement orgChildWndowTxt;

@FindBy(name = "search")
private WebElement searchInOrgChldWnd;

@FindBy(name = "bill_street")
private WebElement billingAddressTxtField;

@FindBy(name = "ship_street")
private WebElement shipAddressTxtFld;

@FindBy(xpath = "//img[@src='themes/images/products.gif']")
private WebElement itemIcon;

@FindBy(id = "search_txt")
private WebElement productChildWndTxtFld;

@FindBy(id = "qty1")
private WebElement qtyTxtFld;

@FindBy(name = "search")
private WebElement searchInProductChldWnd;

public WebElement getSave() {
	return save;
}

public void createNewSalesOrder(String org,String billaddress,String shipaddress,String product) throws InterruptedException
{
	orgBtn.click();
	WebDriverUtility wLib=new WebDriverUtility();
    wLib.switchToTabOnURL(driver, "Accounts&action");
    orgChildWndowTxt.sendKeys(org);
	searchInOrgChldWnd.click();
	driver.findElement(By.xpath("//a[contains(text(),'"+org+"')]")).click();
	driver.switchTo().alert().accept();
	Thread.sleep(2000);
	wLib.switchToTabOnURL(driver, "SalesOrder&action");
	Thread.sleep(2000);
	billingAddressTxtField.sendKeys(billaddress);
	shipAddressTxtFld.sendKeys(shipaddress);
	
	itemIcon.click();
	wLib.switchToTabOnURL(driver, "Products&action");
	productChildWndTxtFld.sendKeys(product);
	searchInProductChldWnd.click();
	driver.findElement(By.xpath("//a[contains(text(),'"+product+"')]")).click();
	//driver.switchTo().alert().accept();
	wLib.switchToTabOnURL(driver, "module=SalesOrder&action");
	qtyTxtFld.sendKeys("50");
	
}
}
