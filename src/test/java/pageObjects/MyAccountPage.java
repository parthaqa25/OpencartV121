package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//NOTE:- F2 is the short cart key for rename any class 
public class MyAccountPage extends BasePage {

	//constructor
	public MyAccountPage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	//Find the MyAccountPage locators WebElement
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") //MyAccount page Header
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Logout']")  //added in step6 for data driven testing
	WebElement lnkLogout;
	
	//Corresponding actions methods of MyAccountPage
	
	public boolean isMyAccountPageExists()
	{
		try
		{
		return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}


}
