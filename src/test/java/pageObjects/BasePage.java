package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {  // this BasePage class is extended into every pageObject class as this is a parent of every 
                        // pageObject classes because for every pageObject classes the constructor is same
	
	WebDriver driver;
	
	//parent constructor of every pageObject classes constructor
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
