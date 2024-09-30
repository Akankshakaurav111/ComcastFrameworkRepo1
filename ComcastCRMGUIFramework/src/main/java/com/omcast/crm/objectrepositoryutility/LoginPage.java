package com.omcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * @author Dharmendra
 * 
 * Contains Login Page Element And Business Library like loginToApplication()
 * 
 */
public class LoginPage extends WebDriverUtility //RULE 1:=>CREATE A SEPARATE JAVA CLASS
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  //RULE 3:OBJECT INITIALLIZATION
	}

//RULE 2:=>OBJECT CREATION
	
	@FindBy(name="user_name")
	private WebElement userNameEdit;  //RULE 4:OBJECT ENCAPSULATION
	
	public WebElement getUserNameEdit() {
		return userNameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
/**
 *  login to application based url,username,password
 * @param url
 * @param username
 * @param password
 */

//RULE 5:PROVIDE ACTION	
	public void loginToApplication(String url, String username,String password)//this is only for specigic application
	{
		driver.get(url);
		waitForPageToLoad(driver);//INSTEAD OF CREATING OBJECT TO CALL WEBDRIVER METHOD WE JUST EXTENDING THAT CLASS AND CALLING THE METHOD
		userNameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginButton.click();
		
	}
	
}
