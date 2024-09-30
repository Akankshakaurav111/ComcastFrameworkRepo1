package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	WebDriver driver;

	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastNameEdit;

	@FindBy(name = "button")
	private WebElement saveButton;

	public WebElement getLastNameEdit() {
		return lastNameEdit;
	}

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement OrgBtnInContact;

	public WebElement getOrgBtnInContact() {
		return OrgBtnInContact;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	@FindBy(id = "jscal_field_support_start_date")
	private WebElement startDatetxtField;

	public WebElement getStartDatetxtField() {
		return startDatetxtField;
	}

	public WebElement getEndDatetxtField() {
		return endDatetxtField;
	}

	@FindBy(id = "jscal_field_support_end_date")
	private WebElement endDatetxtField;
}
