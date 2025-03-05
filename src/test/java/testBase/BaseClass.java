package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j Manager
import org.apache.logging.log4j.Logger;     //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties p;
	
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"}) //here parameter name we have to pass under @Parameter annotation same as
	                             //given in master xml file, parameter name is case sensitive 
	public void setData(String os, String br) throws IOException
	{
		
		//loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties"); //for read the file by creating special class FileReader object
		p=new Properties(); //to load the file from properties we have to create properties class object
		p.load(file); //load() method loading the file by passing file as a parameter and by created p object variable
		
		logger=LogManager.getLogger(this.getClass()); //here LogManager is a default class where getLogger()
		                                             //method can find the log of the particular class by
		                                         //default with the help of this keyword by getClass() method
		                                        //and store it into logger variable
		
		//If execution environment is remote then we have to do Grid setup
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os found");
			}
			
			//browser
			
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default:     System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		//If execution environment is local
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			//For serial or parallel execution in local system from the xml file with a multiple browser
			switch(br.toLowerCase())
			{
			case "chrome": driver=new ChromeDriver(); break;
			case "edge"  : driver=new EdgeDriver(); break;
			case "firefox":driver=new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return;
			}
		}
		

		driver.manage().deleteAllCookies(); //here deleteAllCookies() method delete all the Cookies in a page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL2")); //reading URL from properties file by getProperty() method
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() //for closing the application need for all test case
	{
		driver.quit(); 
	}
	
	
	
	//user defined methods may need for all the test cases for generate String and number
	public String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(6); //RandomStringUtils is a default commons.lang3 class and
		return generatedstring;                                      //randomAlphabetic() is a method, both together generate
	}                                                               //random String
	
	public String randomeNumber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);//RandomStringUtils is a default commons.lang3 class and
		return generatednumber;                                   //randomNumeric() is a method, both together generate
	}                                                            //random number
	
	public String randomeAlphaNumberic()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return (generatedstring+"$"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		File sourceFile =takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}

}
