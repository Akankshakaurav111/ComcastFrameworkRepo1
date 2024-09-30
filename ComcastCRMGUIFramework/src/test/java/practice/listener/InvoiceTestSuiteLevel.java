package practice.listener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.basetest.BaseClass;
public class InvoiceTestSuiteLevel extends BaseClass
{

@Test
public void createInvoiceTest()
{
	System.out.println("execute createInvoiceTest");
	String actTitle = driver.getTitle();
	Assert.assertEquals(actTitle, "login");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
}
@Test
public void createInvoiceWithContactTest()
{
	System.out.println("execute createInvoiceWithContact");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
}
}
