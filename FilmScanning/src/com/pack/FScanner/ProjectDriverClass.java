package com.pack.FScanner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import object.DBConnectionClass;
import object.TestDataClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Actions.ActionClass;

import com.pack.ExcelReadWrite.ExcelReaderClass;
import com.pack.ExcelReadWrite.ExcelWritingClass;
import com.pack.Results.TestResultClass;


public class ProjectDriverClass {

	
	public static WebDriver objWebDriver;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
				
		 ExcelReaderClass objExcelReaderClass=new ExcelReaderClass();
		 ArrayList<TestDataClass> ObjArryListOfTestCasesClass= objExcelReaderClass.readExcelData("C:/Documents and Settings/abudagala/workspace/FilmScanning/TestData/TestCases.xls","TestCases");
		 ArrayList<TestDataClass> ObjArryListOfTestStepsClass= objExcelReaderClass.readExcelData("C:/Documents and Settings/abudagala/workspace/FilmScanning/TestData/TestCases.xls","TestSteps");
		 ArrayList<TestResultClass> objArryaListTestResultClass=new ArrayList<TestResultClass>();
		 TestDataClass objTestCasesTestData;
	     int repeat=0;
		 ActionClass objActionClass=new ActionClass();
		 
		 Iterator<TestDataClass> objIteratorForTestCases= ObjArryListOfTestCasesClass.iterator();
		 		 
		 objWebDriver=new FirefoxDriver();
		  
		  while (objIteratorForTestCases.hasNext()) 
		  {
			  String sheetName = null;
			  objTestCasesTestData=objIteratorForTestCases.next();
			  String repeatCondition=objTestCasesTestData.getTestCase_TestCaseRepeat();
			  String rptCondValue=repeatCondition.split(";")[0];
		      int rptCnt=0;
			  //System.out.println("Repeat Condition"+rptCondValue);
			  if(rptCondValue.equals("Y"))
			  {
				 rptCnt=Integer.parseInt(repeatCondition.split(";")[1]); 
				 repeat=1; 
			     sheetName=objTestCasesTestData.getTestCase_TestDataSheet();
			     System.out.println("The Size of the Arraylist of Test data"+rptCnt);
			  }
			  else
			  {
				  rptCnt=1;
				  repeat=0;
			  }
			  for (int i = 1; i <=rptCnt; i++) {
			  
			 //System.out.println("Loop Repeatition " +i);  
			 Iterator<TestDataClass> objIterator=ObjArryListOfTestStepsClass.iterator();
			  
			  //Iterator<TestDataClass> objIterator=ObjArryListOfTestDataClass.iterator();
		    
			 while(objIterator.hasNext()){
				 
				 TestDataClass objTestDataClass=objIterator.next();
				  //System.out.println("Next Iteration");
				 
				 //System.out.println(objTestCasesTestData.getTestCase_TestCaseID()+"\t"+objTestCasesTestData.getTestCase_RunMode()+"\t"+objTestCasesTestData.getTestCase_RunMode());
				 
			 
				 if(objTestCasesTestData.getTestCase_TestCaseID().equalsIgnoreCase(objTestDataClass.getTestSteps_TestCaseID()) && objTestCasesTestData.getTestCase_RunMode().equalsIgnoreCase("Y")) {
					  
					  //System.out.println("In IF condition"+objTestCasesTestData.getTestCase_TestCaseID());
					 switch(objTestDataClass.getTestSteps_Action()){
					 	
					 	case "Launch":
					 		try{	
					 			objWebDriver=objActionClass.LaunchURL(objWebDriver,objTestDataClass.getTestSteps_TestData());
					 			TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
					 		}
					 		catch(Exception e){
					 			TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		break;
					 		
					 
					 	case "EnterTxt": 
					 			try{
					 				String testData=null;
					 				if(repeat==1)
					 				{
					 					int colNum=Integer.parseInt(objTestDataClass.getTestSteps_TestData());
					 					testData=objExcelReaderClass.excelData("C:/Documents and Settings/abudagala/workspace/FilmScanning/TestData/SampleTestCases.xls",sheetName,i,colNum);
					 					System.out.println("Test Data is "+testData);
					 					
					 					
					 				}
					 				else
					 				{
					 					testData=objTestDataClass.getTestSteps_TestData();
					 				
					 				}
					 				
					 			    objActionClass.EnterText(objWebDriver, objTestDataClass.getTestSteps_LocatorType(),objTestDataClass.getTestSteps_LocatorValue(),testData);
					 				TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Passed");
						 			objArryaListTestResultClass.add(objTestResultClass);
					 			}
					 			catch(Exception e){
					 				TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed");
						 			objTestResultClass.setTestResultException(e.getMessage());
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
						 						 				
					 			}
					 		break;
					 		
					 	case "OpenTab":
					 		try{
				 				if(objActionClass.openTab(objWebDriver, objTestDataClass.getTestSteps_LocatorType(),objTestDataClass.getTestSteps_LocatorValue(),objTestDataClass.getTestSteps_TestData()))
				 				{
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 				}
				 				else
				 				{
				 					TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
				 				}
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		break;
					 		
					 	case "VerifyValidation":
					 		try{
					 			
					 			if(objActionClass.verifyValidation(objWebDriver,objTestDataClass.getTestSteps_LocatorType(), objTestDataClass.getTestSteps_LocatorValue(),objTestDataClass.getTestSteps_TestData()))
				 				{
				 					//System.out.println("In true");
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 				}
				 				else
				 				{
				 				    //System.out.println("In False");	
				 					TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
						 						 				
				 					
				 				}
				 				
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		break;
					 		
					 	case "PressTab":
					 		try{
				 				objActionClass.pressTab(objWebDriver, objTestDataClass.getTestSteps_LocatorType(),objTestDataClass.getTestSteps_LocatorValue());
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		break;
					 		
					 	case "VerifyGridColorCount":
					 		
					 		try{
					 			
				 				if(objActionClass.verifyGridColorCount(objWebDriver,objTestDataClass.getTestSteps_LocatorType(), objTestDataClass.getTestSteps_LocatorValue(),objTestDataClass.getTestSteps_TestData()))
				 				{
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 				}
				 				else
				 				{
				 				    //System.out.println("In False");	
				 					TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Mismatched the record count in the summary details count and grid records count");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
						 						 				
				 					
				 				}
				 				
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		
					 		break;
					 		
					 	case "EnterSpaces":
					 		
					 		try{
				 				objActionClass.EnterSpaces(objWebDriver, objTestDataClass.getTestSteps_LocatorType(),objTestDataClass.getTestSteps_LocatorValue());
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		break;
					 		
					 	case "VerifyMessageText":
					 		try{
				
				 				if(objActionClass.verifyMessageText(objWebDriver, objTestDataClass.getTestSteps_LocatorType(),objTestDataClass.getTestSteps_LocatorValue(),objTestDataClass.getTestSteps_TestData()))
				 				{
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 				}
				 				else
				 				{
				 					TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
						 						 				
				 				}
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		
					 		break;
					 		
					 	case "VerifyElement": 
				 			try{
				 				if(objActionClass.verifyElement(objWebDriver,objTestDataClass.getTestSteps_LocatorType(), objTestDataClass.getTestSteps_LocatorValue()))
				 				{
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 				}
				 				else
				 				{
				 					TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
				 				}
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
				 		break;
				 		
			
				 		
					 	case "Click":
					 		try{
					 			
					 			objActionClass.Clicking(objWebDriver,objTestDataClass.getTestSteps_LocatorType(), objTestDataClass.getTestSteps_LocatorValue());
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 			
					 		break;
					 		
					 	case "LinkClick":
					 		try{
					 			//System.out.println("At click case");
					 			objActionClass.linkClick(objWebDriver,objTestDataClass.getTestSteps_LocatorType(), objTestDataClass.getTestSteps_LocatorValue());
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 			
					 		break;
					 	case "SelectFromDropDown":
					 		try{
					 			objActionClass.SelectValueFromDropDown(objWebDriver, objTestDataClass.getTestSteps_LocatorValue(),objTestDataClass.getTestSteps_TestData());
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		break;
					 		
					 	case "Wait":
					 		try{
					 			objActionClass.waitTime(objWebDriver,objTestDataClass.getTestSteps_LocatorValue());
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		
					 		break;
					 		
					 	case "Sleep":
					 		
					 		try{
					 			objActionClass.sleepTime(objWebDriver,objTestDataClass.getTestSteps_LocatorType(),objTestDataClass.getTestSteps_LocatorValue());
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		break;
					 		
					 	case "VerifyGrid":
					 		try{
					 			if(objActionClass.verifyGridCount(objWebDriver,objTestDataClass.getTestSteps_LocatorValue(),objTestDataClass.getTestSteps_TestData()))
					 			{
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
					 			}
					 			else
					 			{
					 				TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
					 			}
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		
					 		break;
					 	
					 	case "CompareAppAndDataBaseTableValues":
					 		ArrayList<String> ObjAppTabelData=objActionClass.AppTableData(objWebDriver,objTestDataClass.getTestSteps_LocatorValue());
					 		{
					 		 DBConnectionClass objDBConnectionClass=new DBConnectionClass();
										 
								try {
									objDBConnectionClass.DataBaseConnectionClass();
									ArrayList<String> objDBTableData=objDBConnectionClass.executeStatement(objTestDataClass.getTestSteps_TestData());
									if(objActionClass.AppAndDBCompare(ObjAppTabelData, objDBTableData)==true) { 
										System.out.println("both are same");
										TestResultClass objTestResultClass=new TestResultClass();
							 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
							 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
							 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
							 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
							 			objTestResultClass.setTestResult("Passed");
							 			objArryaListTestResultClass.add(objTestResultClass);
									}
									else{
										System.out.println("Both are not same");
										TestResultClass objTestResultClass=new TestResultClass();
							 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
							 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
							 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
							 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
							 			objTestResultClass.setTestResult("Failed");
							 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
							 			objTestResultClass.setTestResultException("Both are not same");
							 	        objArryaListTestResultClass.add(objTestResultClass);
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed");
						 			objTestResultClass.setTestResultException(e.getMessage());
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
								}
								finally{
									try {
										objDBConnectionClass.closeDataBase();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
					 		}
							 break;
							 
					 	
					 	case "CompareAPPAndDBFieldValue":
					 	{
					 		 DBConnectionClass objDBConnectionClass=new DBConnectionClass();
					 		
					 		try {
								objDBConnectionClass.DataBaseConnectionClass();
								String DBvalue=objDBConnectionClass.executeStatementForSingleValue(objTestDataClass.getTestSteps_TestData());
								if(objActionClass.CompareAPPAndDBFieldValue(objWebDriver, objTestDataClass.getTestSteps_LocatorValue(), DBvalue)){
									TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Passed");
						 			objArryaListTestResultClass.add(objTestResultClass);
								}
								else{
									TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed");
						 			objTestResultClass.setTestResultException("Both are not same");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
							}
					 		finally{
								try {
									objDBConnectionClass.closeDataBase();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
					 	}
					 		break;
					 	
					 	case "SelectValueFromDropDown":
					 		try{
					 			objActionClass.SelectValueFromDropDown(objWebDriver, objTestDataClass.getTestSteps_LocatorValue(),objTestDataClass.getTestSteps_TestData());
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		break;
					 		
					 	case "VerifyMessage":
					 		
					 		try{
					 			if(objActionClass.verifyMessage(objWebDriver, objTestDataClass.getTestSteps_LocatorType(),objTestDataClass.getTestSteps_LocatorValue()))
					 			{
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
					 			}
					 			else
					 			{
					 				TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed Due to Success Message is not displayed");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
						 				
					 			}
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
					 		
					 		
					 		break;
					 		
					 	case "VerifyTwinCheckCount":
					 	{   
					 		DBConnectionClass objDbConnectionClass=new DBConnectionClass();
					 		try
					 		{
					 			objDbConnectionClass.DataBaseConnectionClass();
					 			int count=objDbConnectionClass.rowCount(objTestDataClass.getTestSteps_TestData());
					 			String testData = Integer.toString(count);
					 			objDbConnectionClass.closeDataBase();
					 			if(objActionClass.verifyTwinCheckcount(objWebDriver,objTestDataClass.getTestSteps_LocatorType(),objTestDataClass.getTestSteps_LocatorValue(),testData))
					 			{
					 				TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Passed");
						 			objArryaListTestResultClass.add(objTestResultClass);
					 			}
					 			else
					 			{
					 				TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed Due to Mismatch Total Batch order count,Twin check folder count");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
						 				
					 			}
					 		}
					 		 catch(Exception e)
					 		 {
					 			TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass); 
					 		 }
					 		finally{
								try {
									objDbConnectionClass.closeDataBase();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
					 	}	
					 		break;
					 		
					 	case "LinkVisible":
					 	{
					 			DBConnectionClass objDBConnectionClass=new DBConnectionClass();
					 		try{
					 			objDBConnectionClass.DataBaseConnectionClass();
								String DBvalue=objDBConnectionClass.executeStatementForSingleValue(objTestDataClass.getTestSteps_TestData());
						 		if(objActionClass.LinkVisible(objWebDriver,objTestDataClass.getTestSteps_LocatorValue(),DBvalue)) {
						 			TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Passed");
						 			objArryaListTestResultClass.add(objTestResultClass);
						 		}
						 		else{
						 			TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
						 		}
					 		}
					 		catch (Exception e) {
								// TODO Auto-generated catch block
								TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 		}
					 	}
					 		break;
					 		
						case "LinkEnable":
						{
				 			DBConnectionClass objDBConnectionClass=new DBConnectionClass();
					 		try{
					 			objDBConnectionClass.DataBaseConnectionClass();
								String DBvalue=objDBConnectionClass.executeStatementForSingleValue(objTestDataClass.getTestSteps_TestData());
						 		if(objActionClass.LinkEnable(objWebDriver,objTestDataClass.getTestSteps_LocatorValue(),DBvalue)) {
						 			TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Passed");
						 			objArryaListTestResultClass.add(objTestResultClass);
						 		}
						 		else{
						 			TestResultClass objTestResultClass=new TestResultClass();
						 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
						 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
						 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
						 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
						 			objTestResultClass.setTestResult("Failed");
						 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
						 			objArryaListTestResultClass.add(objTestResultClass);
						 		}
					 		}
					 		catch (Exception e) {
								// TODO Auto-generated catch block
								TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 		}
						}
				 		break;
				 		
						case "Quit":
							try{
					 			objActionClass.quitBrowser(objWebDriver);
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
				 			}
				 			catch(Exception e){
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
					 						 				
				 			}
							
							break;
							
						case "Clear":
							try
							{
								objActionClass.ClearText(objWebDriver,objTestDataClass.getTestSteps_LocatorType(), objTestDataClass.getTestSteps_LocatorValue());
				 				TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Passed");
					 			objArryaListTestResultClass.add(objTestResultClass);
							}
							catch(Exception e)
							{
								TestResultClass objTestResultClass=new TestResultClass();
					 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
					 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
					 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
					 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
					 			objTestResultClass.setTestResult("Failed");
					 			objTestResultClass.setTestResultException(e.getMessage());
					 			objActionClass.takeScreenShot(objWebDriver, objTestDataClass.getTestSteps_TestCaseID()+"_"+objTestDataClass.getTestSteps_TestStepID()+"_"+objTestDataClass.getTestSteps_Desc());
					 			objArryaListTestResultClass.add(objTestResultClass);
							}
							break;
				 		
						case "":
				 			
				 			TestResultClass objTestResultClass=new TestResultClass();
				 			objTestResultClass.setTestCaseID(objTestDataClass.getTestSteps_TestCaseID());
				 			objTestResultClass.setTestCaseDesc(objTestCasesTestData.getTestCase_Desc());
				 			objTestResultClass.setTestStepID(objTestDataClass.getTestSteps_TestStepID());
				 			objTestResultClass.setTestStepDesc(objTestDataClass.getTestSteps_Desc());
				 			objTestResultClass.setTestResult("Failed");
				 			objTestResultClass.setTestResultException("Action Keyword is missing");
				 			objArryaListTestResultClass.add(objTestResultClass);
				 	  

					 }				 
				 
				 
				 }
				
			}
			    }	 
		 } 
		  
		  try
		  {
		 ExcelWritingClass objExcelWritingClass=new ExcelWritingClass();
		 objExcelWritingClass.writeExcel(objArryaListTestResultClass);
		  }
		  catch(Exception e)
		  {
			  System.out.println("Exception occured is"+e.toString());
		  }

		 
		
		 
		 
	}

}
