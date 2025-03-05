package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
Data is valid--- login success----test passed----logout
                 login failed----test failed

Data is invalid---login success----test failed----logout
                  login failed-----test passed

*/


public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven") //getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("********** Starting TC003_LogingDDT ***********");
		
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
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
				
		//MyAccountPage
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				map.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				map.clickLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
	  }
	   
	  catch(Exception e)
	  {
		  Assert.fail();
	  }
		logger.info("********** Finished TC003_LogingDDT ***********");
	}

}
