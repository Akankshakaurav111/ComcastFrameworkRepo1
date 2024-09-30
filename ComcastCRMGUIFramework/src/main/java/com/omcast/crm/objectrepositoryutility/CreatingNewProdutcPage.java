package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewProdutcPage
{
	WebDriver driver;
	public CreatingNewProdutcPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement getProductNameTextField() {
		return productNameTextField;
	}

	public WebElement getBtn() {
		return btn;
	}
	@FindBy(name = "productname")
	private WebElement productNameTextField;
	
	@FindBy(name = "button")
	private WebElement btn;
	

}
