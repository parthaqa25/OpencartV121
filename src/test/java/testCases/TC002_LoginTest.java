package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verify_Login()
	{
		logger.info("************** Starting TC002_LoginTest **************"); //info() method print the
                                                                              //starting information
       try
       {
		//HomePagr
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link....");
		
		hp.clickLogin();
		logger.info("Clicked on Login Link....");
		
		//LoginPagr
		LoginPage lp=new LoginPage(driver);
		
		logger.info("Providing Login details....");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("pwd"));
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountPageExists();
		
		Assert.assertTrue(targetPage);
		
       }
       catch(Exception e)
       {
         Assert.fail();
       }
       
       logger.info("************** Finished TC002_LoginTest **************");
	}

}
