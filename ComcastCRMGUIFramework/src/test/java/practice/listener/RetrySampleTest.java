package practice.listener;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetrySampleTest 
{
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
public void activateSim()
{
	System.out.println("execute createInvoiceTest");
	//Assert.assertEquals(" ", "login");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
}
}

