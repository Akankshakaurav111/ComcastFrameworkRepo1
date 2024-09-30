package practiceTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoDataDrivenTest 
{
@Test(dataProvider = "getData")
public void getProductInfoTest(String brandName, String productName) throws InterruptedException
{
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("https://www.amazon.in/");
	
	//SEARCH PRODUCT
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
	
	
	//CAPTURE PRODUCT INFORMATION
	String price = driver.findElement(By.xpath("//span[text()='"+productName+"']/../../../../descendant::span[@class='a-price-whole']")).getText();
//2nd approch
//	String x="//span[text()='Apple iPhone 14 (128 GB) - Midnight']/../../../../div[3]/div[1]/div/div[1]/div[1]/div/a/span/span[2]/span[2]";
//	String price = driver.findElement(By.xpath(x)).getText();
	System.out.println(price);
	Thread.sleep(1000);
	driver.quit();
}

@DataProvider
public Object[][] getData() throws EncryptedDocumentException, IOException
{
	ExcelUtility eLib=new ExcelUtility();
	int rowCount = eLib.getRowCount("product");
	
	Object[][] objArr=new Object[rowCount][2];
	
	for(int i=0;i<rowCount;i++)
	{
		int cellCount = eLib.getCellCount("product", rowCount);
		for(int j=0;j<cellCount;j++)
	objArr[i][j]=eLib.getDataFromExcel("product", i+1, j);
	}
	return objArr;
}

}
