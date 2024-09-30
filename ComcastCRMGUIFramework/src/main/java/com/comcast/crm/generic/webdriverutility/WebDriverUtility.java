package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void maximizeTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void minimizeTheWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}

	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForAllPresemtElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void waitForElementSelectable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}

	public void waitForElementSelectable1(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void switchToTabOnURL(WebDriver driver, String partialURL) {

		Set<String> childWindow = driver.getWindowHandles();
		Iterator<String> iterator = childWindow.iterator();
		while (iterator.hasNext())// in that interface we have methods hasNext ,next===hasNext pointing the index
									// and next method giving the value of index
		{
			String windowId = iterator.next();
			driver.switchTo().window(windowId);
			String actualCurrentUrl = driver.getCurrentUrl();
			if (actualCurrentUrl.contains("Accounts&action")) {
				break;
			}
		}
	}

	public void switchToTabOnTitle(WebDriver driver, String partialURL) {

		Set<String> childWindow = driver.getWindowHandles();
		Iterator<String> iterator = childWindow.iterator();
		while (iterator.hasNext())// in that interface we have methods hasNext ,next===hasNext pointing the index
									// and next method giving the value of index
		{
			String windowId = iterator.next();
			driver.switchTo().window(windowId);
			String actualCurrentUrl = driver.getCurrentUrl();
			if (actualCurrentUrl.contains("Accounts&action")) {
				break;
			}
		}
	}

	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrame(WebDriver driver, String nameId) {
		driver.switchTo().frame(nameId);
	}

	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchToAlertAndcancle(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void select(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void select(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void mouseMoveOnElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void doubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	public void sendData(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.sendKeys(element).perform();
	}		

	public void dragAndDrop(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.dragAndDrop(element, element);
	}

	public void scrollByAmount(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.scrollByAmount(0, 0);
	}
}
