package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgnizationPage 
{
	WebDriver driver;
	public OrgnizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(xpath="//img[@alt='Create Organization...']")
private WebElement createNewOrgnizationBtn;

public WebElement getCreateNewOrgnization() {
	return createNewOrgnizationBtn;
}
@FindBy(name = "search_text")
private WebElement searchEdit;

public WebElement getSearchEdit() {
	return searchEdit;
}

@FindBy(name = "submit")
private WebElement searchButton;

public WebElement getSearchButton() {
	return searchButton;
}
public WebElement getSearchDropDown() {
	return searchDropDown;
}
@FindBy(name = "search_field")
private WebElement searchDropDown;
}
