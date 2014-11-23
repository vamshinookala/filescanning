package Actions;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionClass {

	public WebDriver LaunchURL(WebDriver objWebDriver,String URL){
		objWebDriver.get(URL);
		objWebDriver.manage().window().maximize();
		return objWebDriver;
	}
	
	public By getLocator(String locatorType, String locatorValue)
	{
		
		if (locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if (locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if (locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			return null;
		
		

	}
	
	public void ActionWait(WebDriver objWebDriver,int time){
		objWebDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	public void ClearText(WebDriver objWebDriver,String locatorType,String locatorValue){

		try {
			objWebDriver.findElement(getLocator(locatorType, locatorValue)).clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void EnterText(WebDriver objWebDriver,String locatorType,String LocatorValue,String TextData){
	
				try {
					objWebDriver.findElement(getLocator(locatorType,LocatorValue)).sendKeys(TextData);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		}
		
	/**
	 * used to Click on Button or link 
	 * Input parameter Locator value 
	 */
	public void Clicking(WebDriver objWebDriver,String locatorType,String locatorValue){
	
		 objWebDriver.findElement(getLocator(locatorType, locatorValue)).click();
	}
	
	/**
	 * To get the  table data from application using table ID 
	 */
	public ArrayList<String> AppTableData(WebDriver objWebDriver,String LocatorValue){
		
		ArrayList<String> ObjTabelData=new ArrayList<String>();

		// Grab the table 
				WebElement table = objWebDriver.findElement(By.id(LocatorValue)); 

				// Now get all the TR elements from the table 
				List<WebElement> allRows = table.findElements(By.tagName("tr")); 

				// And iterate over them, getting the cells 
				for (WebElement row : allRows) { 
					
					
				    List<WebElement> cells = row.findElements(By.tagName("td")); 

				    // Print the contents of each cell
				    for (WebElement cell : cells) { 
				       // System.out.println(cell.getText());
				        ObjTabelData.add(cell.getText());
				    }
				}
				return ObjTabelData;
		
	}
	/**
	 * To compare the data of two string array list values
	 * Input value: 2 string array list objects
	 * OutPut : Return true then both the array values are same / Return false when any of the array value is not same. 
	 *
	 */
	public boolean AppAndDBCompare(ArrayList<String> ObjAppTabelData,ArrayList<String> ObjDBTabelData){
		Iterator<String> objAppTableIterator=ObjAppTabelData.iterator();
		Iterator<String> objDBTableIterator=ObjDBTabelData.iterator();
		boolean result = false;
		while(objAppTableIterator.hasNext() && objDBTableIterator.hasNext()){
			String AppTableColumnValue=objAppTableIterator.next().toString(); 
			String DBTableColumnValue  =objDBTableIterator.next().toString();
			if(AppTableColumnValue!=null &&  DBTableColumnValue!=null ){
				if(AppTableColumnValue.isEmpty()==false && DBTableColumnValue.isEmpty()==false ){
					if(AppTableColumnValue.equalsIgnoreCase(DBTableColumnValue)){
//						System.out.println("AppTableColumnValue="+AppTableColumnValue);
//						System.out.println("DBTableColumnValue="+DBTableColumnValue);
						result=true;
					}
				}
				else{
					
//					System.out.println("AppTableColumnValue="+AppTableColumnValue);
//					System.out.println("DBTableColumnValue="+DBTableColumnValue);
					return false;
					
				}
				
			}
			
				
			
		}
		return result;
		
	}
	
	public boolean CompareAPPAndDBFieldValue(WebDriver objWebDriver,String LocatorValue,String DBValue){
		boolean result = false;
		String AppValue=objWebDriver.findElement(By.id(LocatorValue)).getAttribute("value");
		if((AppValue==null || AppValue.isEmpty()) && (DBValue==null || DBValue.isEmpty())) {
			return true; 
		}
		if(AppValue.equalsIgnoreCase(DBValue)){
			result=true; 
		}
		else{
			return false;
		}
		return result;
		
	}
	
	/**
	 * To select a value for the drop down list 
	 */
	
	public void SelectValueFromDropDown(WebDriver objWebDriver,String LocatorValue,String LabelValue){
		WebElement DropDown= objWebDriver.findElement(By.id(LocatorValue));
		List<WebElement> DropDownList = DropDown.findElements(By.tagName("option"));
		  for (WebElement dropDownElement : DropDownList) {
			 
			  if(dropDownElement.getText().equalsIgnoreCase(LabelValue)){
				  System.out.println(dropDownElement.getText());
				  dropDownElement.click();
			  }
		  }
	}
	
	public boolean LinkVisible(WebDriver objWebDriver,String LocatorValue,String DBValue) {
		
				if(DBValue.equalsIgnoreCase("Y") && objWebDriver.findElements(By.id(LocatorValue)).size()!=0 )
					
					return true;
				
				if(DBValue.equalsIgnoreCase("N") && objWebDriver.findElements(By.id(LocatorValue)).size()==0 )
					return true;
				else
				return false;

	}
	
	public boolean LinkEnable(WebDriver objWebDriver,String LocatorValue,String DBValue) {
		System.out.println(DBValue);
		System.out.println( (objWebDriver.findElement(By.id(LocatorValue)).getAttribute("disabled")));
				if(DBValue.equalsIgnoreCase("Y") && objWebDriver.findElement(By.id(LocatorValue)).isEnabled() )
					
					return true;
				
				if(DBValue.equalsIgnoreCase("N") && objWebDriver.findElement(By.id(LocatorValue)).getAttribute("disabled").equalsIgnoreCase("true"))
					return true;
				else
					return false;
				
	}

	public void linkClick(WebDriver objWebDriver, String locatorType,String locatorValue) {
		
		objWebDriver.findElement(getLocator(locatorType, locatorValue)).click();
		
		
	}

	public boolean verifyElement(WebDriver objWebDriver,String locatorType,String locatorValue) {
		
		
		 if(objWebDriver.findElement(getLocator(locatorType, locatorValue)).isDisplayed() && objWebDriver.findElement(getLocator(locatorType, locatorValue)).isEnabled())
			return true;
		 else 
			 return false;
				
	}

	public void waitTime(WebDriver objWebDriver,String locatorValue) {
		

		WebDriverWait wait=new WebDriverWait(objWebDriver,45);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
		
	}
    public void takeScreenShot(WebDriver objWebDriver,String testCaseTitle)
    {
    	File scrFile = ((TakesScreenshot)objWebDriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("C:\\Documents and Settings\\abudagala\\workspace\\FilmScanning\\Screenshot\\"+testCaseTitle+".jpg"));
		} catch (IOException e) {
			System.out.println("Failed to take Screenshot");
			e.printStackTrace();
		}
    }
	public boolean verifyGridCount(WebDriver objWebDriver,String LocatorValue, String TestData) {
		
		String firstLocator=LocatorValue.split(";")[0];
		String secondLocator=LocatorValue.split(";")[1];
		WebElement twinCheckGrid=objWebDriver.findElement(By.id(firstLocator));
		int actualCount=Integer.parseInt(objWebDriver.findElement(By.id(secondLocator)).getText());
		List<WebElement> twinCheckRows=twinCheckGrid.findElements(By.tagName("tr"));
		System.out.println("Rows in the TwinCheck Results Grid"+twinCheckRows.size());
		if(twinCheckRows.size()-1==actualCount)
		  return true;
		else
			return false;
		
	}

	public boolean verifyMessage(WebDriver objWebDriver,String locatorType,String locatorValue) throws Exception {
		
		
			if(objWebDriver.findElement(getLocator(locatorType, locatorValue)).isDisplayed())
				return true;
			else
				 return false;
		
	}

	public void quitBrowser(WebDriver objWebDriver) {
		
		objWebDriver.quit();
		
	}

	public boolean verifyValidation(WebDriver objWebDriver,String locatorType,String locatorValue, String TestData) throws InterruptedException {
	
		if(locatorValue.contains(";"))
		{
			String firstLocator=locatorValue.split(";")[0];
			String secondLocator=locatorValue.split(";")[1];
			if(objWebDriver.findElement(getLocator(locatorType, firstLocator)).getAttribute("class").equals(TestData) && objWebDriver.findElement(getLocator(locatorType,secondLocator)).getAttribute("class").equals(TestData))
		    	 return true;
		    else 
		    	return false;
			
		}
		else
		{
			if(objWebDriver.findElement(getLocator(locatorType, locatorValue)).getAttribute("class").equals(TestData))
				return true;
			else
				return false;
		}
		
	}

	public boolean verifyMessageText(WebDriver objWebDriver,String locatorType,String locatorValue, String TestData) {
		
		  
			if(objWebDriver.findElement(getLocator(locatorType, locatorValue)).getText().equals(TestData))
				return true;
			else
				 return false;
		
	}

	public void sleepTime(WebDriver objWebDriver,String locatorType,String locatorValue) {
		
		
		WebDriverWait wait=new WebDriverWait(objWebDriver,45);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorType, locatorValue)));		
		
	}

	public void EnterSpaces(WebDriver objWebDriver,String locatorType,String locatorValue) {
	
		 Actions action=new Actions(objWebDriver);
		 action.sendKeys(objWebDriver.findElement(getLocator(locatorType, locatorValue)), Keys.SPACE).sendKeys(Keys.SPACE).sendKeys(Keys.SPACE).build().perform();
		
		
	}


	public boolean verifyTwinCheckcount(WebDriver objWebDriver,String locatorType, String locatorValue,String testData) {
		
		if(locatorValue.contains(";"))
		{
			StringTokenizer stoken=new StringTokenizer(locatorValue,";");
			System.out.println(stoken.countTokens());
			if(stoken.countTokens()==2)
			{
			String firstLocator=locatorValue.split(";")[0];
			String secondLocator=locatorValue.split(";")[1];
			if(objWebDriver.findElement(getLocator(locatorType, firstLocator)).getText().equals(testData) && objWebDriver.findElement(getLocator(locatorType,secondLocator)).getText().equals(testData))
		    	 return true;
		    else 
		    	return false;
			}
			else
			{
				String firstLocator=locatorValue.split(";")[0];
				String secondLocator=locatorValue.split(";")[1];
				String thirdLocator=locatorValue.split(";")[2];
				if(objWebDriver.findElement(getLocator(locatorType, firstLocator)).getText().equals(testData) && objWebDriver.findElement(getLocator(locatorType,secondLocator)).getText().equals(testData)
						             && objWebDriver.findElement(getLocator(locatorType, thirdLocator)).getText().equals(testData))
			    	 return true;
			    else 
			    	return false;
		      }
			}
		else
		{
			if(objWebDriver.findElement(getLocator(locatorType, locatorValue)).getText().equals(testData))
				return true;
			else
				return false;
		}
	}


	public boolean verifyGridColorCount(WebDriver objWebDriver,String locatorType, String locatorValue,String testData) {
		
		boolean result=false;
		int gridRecordCount=0;
		if(locatorValue.contains(";"))
		{
			String firstLocator=locatorValue.split(";")[0];
			String secondLocator=locatorValue.split(";")[1];
			String summaryCount=objWebDriver.findElement(getLocator(locatorType, firstLocator)).getText();
			int summaryRecCount=Integer.parseInt(summaryCount);
			WebElement dataGrid=objWebDriver.findElement(By.id(secondLocator));
			List<WebElement> twinCheckRows=dataGrid.findElements(By.tagName("tr"));
			for (int i = 1; i <twinCheckRows.size() ; i++) {
				
				if(twinCheckRows.get(i).getCssValue("background-color").equals(testData))
				{
				  gridRecordCount++;
					
				}
				//System.out.println("Rows Background Color"+twinCheckRows.get(i).getCssValue("background-color"));
				
			}
			//System.out.println("Row count"+"  "+gridRecordCount);
			if(gridRecordCount==summaryRecCount)
				result=true;
			else
				result=false;
		}
		
		return result;
		
		
	}
	public int getStatusCode(String linkURL) throws Exception {
	       
		URL url = new URL(linkURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		return connection.getResponseCode();
		

	}

	public boolean openTab(WebDriver objWebDriver, String locatorType,String locatorValue,String testData) throws Exception {
		//System.out.println("at open tab action method"+testData);
		
		if(getStatusCode(testData)==200)
		 return true;	
		
		else
		 return false;
		
	}


	public void pressTab(WebDriver objWebDriver, String locatorType,String locatorValue) {
	
		objWebDriver.findElement(getLocator(locatorType, locatorValue)).sendKeys(Keys.TAB);
		
	}

	public void selectDropDown(WebDriver objWebDriver,String locatorType,String locatorValue,String dropOption) {
		WebElement elementPresence = objWebDriver.findElement(getLocator(locatorType, locatorValue));
		Select s = new Select(elementPresence);
         System.out.println(dropOption.trim()+"hi this is excel data");
		List<WebElement> soptions = s.getOptions();
		for (int i = 0; i < soptions.size(); i++) {
			String actualText =soptions.get(i).getText().trim();
			if (dropOption.trim().equals(actualText)) {
				System.out.println("at dropdown click");
				soptions.get(i).click();
				break;
			}

		}
	}

	
}
