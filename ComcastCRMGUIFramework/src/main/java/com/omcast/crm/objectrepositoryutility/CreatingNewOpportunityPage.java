package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewOpportunityPage {
	WebDriver driver;

	public CreatingNewOpportunityPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
    
	@FindBy(name = "potentialname")
	private WebElement opportunityNameTextField;
     
	@FindBy(id = "related_to_type")
	private WebElement relatedToDropDown;

	@FindBy(xpath = "//input[@id='related_to_display']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement relatedToButton;

	@FindBy(xpath = "//input[@value='T']")
	private WebElement assignToCheckBox;

	@FindBy(name = "assigned_user_id")
	private WebElement assignToDropDown;

	public WebElement getOpportunityNameTextField() {
		return opportunityNameTextField;
	}

	public WebElement getRelatedToDropDown() {
		return relatedToDropDown;
	}

	public WebElement getRelatedToButton() {
		return relatedToButton;
	}

	public WebElement getAssignToCheckBox() {
		return assignToCheckBox;
	}

	public WebElement getAssignToDropDown() {
		return assignToDropDown;
	}

	public WebElement getExpectedClosingDate() {
		return expectedClosingDate;
	}

	public WebElement getSalesStageDropDown() {
		return salesStageDropDown;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getChildWindowTxtFld() {
		return childWindowTxtFld;
	}

	public WebElement getChildwndSearchBtn() {
		return childwndSearchBtn;
	}

	@FindBy(id = "jscal_field_closingdate")
	private WebElement expectedClosingDate;

	@FindBy(name = "sales_stage")
	private WebElement salesStageDropDown;
	@FindBy(name = "button")
	private WebElement submitBtn;

	@FindBy(id = "search_txt")
	private WebElement childWindowTxtFld;

	@FindBy(name = "search")
	private WebElement childwndSearchBtn;

	public void creatingNewOpportunityPage(String opportunity, String lastName, String relatedTo, String assignedTo,
			String salesStage) {
		opportunityNameTextField.sendKeys(opportunity);

		WebDriverUtility wLib = new WebDriverUtility();

		wLib.select(relatedToDropDown, relatedTo);

		relatedToButton.click();

		wLib.switchToTabOnURL(driver, "Contacts&action");
		childWindowTxtFld.sendKeys(lastName);
		childwndSearchBtn.click();
		driver.findElement(By.xpath("//a[contains(text(),'"+lastName+"')]")).click();

		wLib.switchToTabOnURL(driver, "Potentials&action");

		assignToCheckBox.click();

		JavaUtility jLib = new JavaUtility();
        String date = jLib.getRequriedDateYYYYDDMM(5); 
        getExpectedClosingDate().clear();
        getExpectedClosingDate().sendKeys(date);
        wLib.select(salesStageDropDown, salesStage);
		
		submitBtn.click();


	}
}
