package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChildWindowPage 
{
	WebDriver driver;
public ChildWindowPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
@FindBy(name = "search_text")
private WebElement childTextField;

@FindBy(name = "search")
private WebElement childSubmtBtn;
public WebElement getChildTextField() {
	return childTextField;
}

public WebElement getChildSubmtBtn() {
	return childSubmtBtn;
}
}
