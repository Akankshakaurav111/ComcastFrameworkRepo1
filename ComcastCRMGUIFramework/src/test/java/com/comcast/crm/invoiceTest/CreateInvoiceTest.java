package com.comcast.crm.invoiceTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.comcast.crm.contactTest.CreateContactTest;
import com.crm.comcast.basetest.BaseClass;
import com.omcast.crm.objectrepositoryutility.ContactPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewInvoicePage;
import com.omcast.crm.objectrepositoryutility.CreatingNewOrgnisationPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewProdutcPage;
import com.omcast.crm.objectrepositoryutility.CreatingNewSalesOrderPage;
import com.omcast.crm.objectrepositoryutility.InvoicePage;
import com.omcast.crm.objectrepositoryutility.OrgnizationPage;
import com.omcast.crm.objectrepositoryutility.ProductPage;
import com.omcast.crm.objectrepositoryutility.SalesOrderPage;

public class CreateInvoiceTest extends BaseClass
{
	@Test
	public void createInvoice() throws EncryptedDocumentException, IOException, InterruptedException
{
		ContactPage cp=new ContactPage(driver);
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		OrgnizationPage op=new OrgnizationPage(driver);
		CreatingNewOrgnisationPage cnop=new CreatingNewOrgnisationPage(driver);
	String subjectName = eLib.getDataFromExcelUsingDataFormatter("invoice", 1, 2)+jLib.getRandomNumber();
	String contactName =eLib.getDataFromExcelUsingDataFormatter("invoice", 1, 3)+jLib.getRandomNumber();
	String orgnizationName =eLib.getDataFromExcelUsingDataFormatter("invoice", 1, 4)+jLib.getRandomNumber();
	String assignedDD =eLib.getDataFromExcelUsingDataFormatter("invoice", 1, 5);
	String salesOrder =eLib.getDataFromExcelUsingDataFormatter("invoice", 1, 6);
	String biilingPlace = eLib.getDataFromExcel("invoice", 1, 7);
	String shippingPlace = eLib.getDataFromExcel("invoice", 1, 8);
	String productName=eLib.getDataFromExcel("invoice", 1, 9);
	String statusDD =eLib.getDataFromExcelUsingDataFormatter("invoice", 1, 10);
	
	hp.getContactsLink().click();	
	
	cp.getCreatingNewContactButton().click();;	
	
	cncp.getLastNameEdit().sendKeys(contactName);
	cncp.getSaveButton().click();
	
	hp.getOrgnizationLink().click();	
	
	op.getCreateNewOrgnization().click();	
	
	cnop.getOrgNameEdit().sendKeys(orgnizationName);
	cnop.getSaveBtn().click();
	
	//Thread.sleep(2000);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(hp.getProductLink()));
	hp.getProductLink().click();
	ProductPage pp=new ProductPage(driver);
	pp.getCreatingNewProducts().click();
	CreatingNewProdutcPage cnpp=new CreatingNewProdutcPage(driver);
	cnpp.getProductNameTextField().sendKeys(productName);
	cnpp.getBtn().click();
	Thread.sleep(2000);
	
	hp.naviagteToSalesOrderPage();
	SalesOrderPage sop=new SalesOrderPage(driver);
	sop.getSalesOrderbtn().click();
	CreatingNewSalesOrderPage cnsop=new CreatingNewSalesOrderPage(driver);
	cnsop.getSubjectTxtFld().sendKeys(salesOrder);
	cnsop.createNewSalesOrder(orgnizationName, biilingPlace, shippingPlace, productName);
	
	cnsop.getSave().click();
	Thread.sleep(3000);
	hp.naviagteToInvoicePage();
	
	InvoicePage ip=new InvoicePage(driver);
	ip.getInvoiceBtn().click();
	
	CreatingNewInvoicePage cnip=new CreatingNewInvoicePage(driver);
    cnip.creatingInvoicePage(subjectName, contactName, orgnizationName, assignedDD, statusDD);	
	
	
}
}
