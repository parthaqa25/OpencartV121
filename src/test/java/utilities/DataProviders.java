package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";  //taking excel file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);  //creating an object for utility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols]; //created for two dimensional array which can store
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from excel and storing it into two dimensional array
		{
			for(int j=0;j<totalcols;j++) //0  //1 is rows and j is col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); //1,0 
			}
		}
		
		return logindata; //returning two dimensional array
	}
	
	//DataProvider2
	
	
	//DataProvider3
	
	
	//DataProvider4

}
