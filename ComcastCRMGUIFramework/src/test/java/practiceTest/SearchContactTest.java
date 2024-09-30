package practiceTest;
/**
 * @author Dharmendra
 */

import org.testng.annotations.Test;

import com.crm.comcast.basetest.BaseClass;
import com.omcast.crm.objectrepositoryutility.LoginPage;

public class SearchContactTest extends BaseClass
{
	/**
	 * scenario 1: login()==> navigateContact ===>createContact ===>verify
	 */
@Test
public void searchcontactTest()
{
	/*login to app*/
	LoginPage lp=new LoginPage(driver);
	lp.loginToApplication("url", "username", "password");
	
}
}
