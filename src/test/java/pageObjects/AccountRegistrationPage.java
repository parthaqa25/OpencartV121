package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {
	
	//constructor
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
//NOTE:- If in future more number of test cases need to be tested then need to add that locators along with corresponding
//the methods we have to created in this Account Registration page object class, this is continuous process
	
	//Find the locators WebElement
    @FindBy(xpath="//input[@id='input-firstname']")
    WebElement txtFirstname;
    
    @FindBy(xpath="//input[@id='input-lastname']")
    WebElement txtLastname;
    
    @FindBy(xpath="//input[@id='input-email']")
    WebElement txtEmail;
    
    @FindBy(xpath="//input[@id='input-telephone']")
    WebElement txtTelephone;
    
    @FindBy(xpath="//input[@id='input-password']")
    WebElement txtPassword;
    
    @FindBy(xpath="//input[@id='input-confirm']")
    WebElement txtConfirmPassword;
    
    @FindBy(xpath="//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath="//input[@value='Continue']")
    WebElement btnContinue;
    
    @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;
    
    
    //Corresponding actions methods
    
    public void setFirstName(String fname)
    {
    	txtFirstname.sendKeys(fname);
    }
    
    public void setLastName(String lname)
    {
    	txtLastname.sendKeys(lname);
    }
    
    public void setEmail(String email)
    {
    	txtEmail.sendKeys(email);
    }
    
    public void setTelephone(String tel)
    {
    	txtTelephone.sendKeys(tel);
    }
    
    public void setPassword(String pwd)
    {
    	txtPassword.sendKeys(pwd);
    }
    
    public void setConfirmPassword(String pwd)
    {
    	txtConfirmPassword.sendKeys(pwd);
    }
    
    public void setPrivacyPolicy()
    {
    	chkdPolicy.click();
    }
    
    public void clickContinue()
    {
    	//sol1
    	btnContinue.click();
    	
    	//sol2
    	//btnContinue.submit();
    	
    	//sol3
    	//Actions act=new Actions(driver);
    	//act.moveToElement(btnContinue).click().perform();
    	
    	//sol4
    	//JavascriptExecutor js=(JavascriptExecutor)driver;
    	//js.executeScript("arguments[0].click();", btnContinue);
    	
    	//sol5
    	//btnContinue.sendKeys(Keys.RETURN);
    	
    	//sol6
    	//WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
    	//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }
    
    
    public String getConfirmationMsg()
    {
    	try
    	{
    	return(msgConfirmation.getText()); //get the confirmation message and return as a String value
    	}
    	catch(Exception e)
    	{
    		return(e.getMessage()); //if throws exception then return the exception message
    	}
    }
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
