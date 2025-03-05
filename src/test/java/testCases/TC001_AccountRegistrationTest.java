package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("************** Starting TC001_AccountRegistrationTest **************"); //info() method
		                                                                     //print the starting information
	   try
	   {
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link....");
		hp.clickRegister();
		logger.info("Clicked on Register Link....");
		
		AccountRegistrationPage  regpage=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details....");
		regpage.setFirstName(randomeString().toUpperCase()); //randomeString() & toUpperCase() method generate and convert String into upperCase
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com"); //randomeString() user defined method randomly generated String
		regpage.setTelephone(randomeNumber());
		
		String password=randomeAlphaNumberic(); //randomeAlphaNumberic() method value store into variable and pass it into 
		regpage.setPassword(password);         //setPassword() and setConfirmPassword() method as a parameter
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message....");
		String confmsg=regpage.getConfirmationMsg();
		/*if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed"); //if exception comes error logs message will display
			logger.debug("debug logs...."); //if debug log is necessary
			Assert.assertTrue(false);
		}*/
		
		Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");
		
	  }
	  catch(Exception e)
	  {
		Assert.fail(); //if validation through any mismatch/exception in try block then Assert.fail()
	  }               //method make test case as failed
		
		logger.info("************** Finished TC001_AccountRegistrationTest **************");
	}

}
