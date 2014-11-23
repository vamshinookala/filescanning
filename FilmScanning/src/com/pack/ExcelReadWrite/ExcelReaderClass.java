package com.pack.ExcelReadWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import object.TestDataClass;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;


public class ExcelReaderClass {

	@SuppressWarnings("deprecation")
	public boolean isRowEmpty(HSSFRow row) {

		try {
			for (short c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
				HSSFCell cell = row.getCell(c);

				if (cell != null
						&& row.getCell(c).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
					return true;

				}
							}
		} catch (NullPointerException e) {
			//System.out.println("At row exception" + e.toString());
		}
		return false;
		
		
	}

	public ArrayList<TestDataClass> readExcelData(String InputFileName,
			String InputSheetName) throws IOException {

		FileInputStream objFileInputStream = new FileInputStream(new File(
				InputFileName));
		HSSFWorkbook objHSSFWorkbook = new HSSFWorkbook(objFileInputStream);
		HSSFSheet objHSSFSheet = objHSSFWorkbook.getSheet(InputSheetName);
		int TotalNumberOfRows = objHSSFSheet.getLastRowNum() + 1;
		//System.out.println("Total rows" + TotalNumberOfRows);
		ArrayList<TestDataClass> ObjArryListOfTestDataClass = new ArrayList<TestDataClass>();

		switch (InputSheetName) {

		case "TestCases":
			for (int i = 1; i < TotalNumberOfRows; i++) {
				TestDataClass objTestDataClass = new TestDataClass();
				HSSFRow removingRow = objHSSFSheet.getRow(i);
				if (isRowEmpty(removingRow)) {
					if (objHSSFSheet.getRow(i).getCell(0) != null)
						objTestDataClass.setTestCase_TestCaseID(objHSSFSheet
								.getRow(i).getCell(0).toString());
					if (objHSSFSheet.getRow(i).getCell(1) != null)
						objTestDataClass.setTestCase_Desc(objHSSFSheet
								.getRow(i).getCell(1).toString());
					if (objHSSFSheet.getRow(i).getCell(2) != null)
						objTestDataClass.setTestCase_RunMode(objHSSFSheet
								.getRow(i).getCell(2).toString());
					if (objHSSFSheet.getRow(i).getCell(3) != null)
						objTestDataClass.setTestCase_Browser(objHSSFSheet
								.getRow(i).getCell(3).toString());
					if(objHSSFSheet.getRow(i).getCell(4)!=null)
						objTestDataClass.setTestCase_TestCaseRepeat(objHSSFSheet
								.getRow(i).getCell(4).toString());
					if(objHSSFSheet.getRow(i).getCell(5)!=null)
						objTestDataClass.setTestCase_TestDataSheet(objHSSFSheet
								.getRow(i).getCell(5).toString());
					ObjArryListOfTestDataClass.add(objTestDataClass);
				}

			}
			break;

		case "TestSteps":

			for (int i = 1; i < TotalNumberOfRows; i++) {
				TestDataClass objTestDataClass = new TestDataClass();
				HSSFRow removingRow = objHSSFSheet.getRow(i);
				if(isRowEmpty(removingRow)){
					
					if (objHSSFSheet.getRow(i) != null) {
						if (objHSSFSheet.getRow(i).getCell(0) != null)
							objTestDataClass
									.setTestSteps_TestCaseID(objHSSFSheet
											.getRow(i).getCell(0).toString());
						if (objHSSFSheet.getRow(i).getCell(1) != null)
							objTestDataClass
									.setTestSteps_TestStepID(objHSSFSheet
											.getRow(i).getCell(1).toString());
						if (objHSSFSheet.getRow(i).getCell(2) != null)
							objTestDataClass.setTestSteps_Desc(objHSSFSheet
									.getRow(i).getCell(2).toString());
						if (objHSSFSheet.getRow(i).getCell(3) != null)
							objTestDataClass
									.setTestSteps_ElementType(objHSSFSheet
											.getRow(i).getCell(3).toString());
						if (objHSSFSheet.getRow(i).getCell(4) != null)
							objTestDataClass
									.setTestSteps_LocatorType(objHSSFSheet
											.getRow(i).getCell(4).toString());
						if (objHSSFSheet.getRow(i).getCell(5) != null)
							objTestDataClass
									.setTestSteps_LocatorValue(objHSSFSheet
											.getRow(i).getCell(5).toString());
						if (objHSSFSheet.getRow(i).getCell(6) != null)
							objTestDataClass.setTestSteps_Action(objHSSFSheet
									.getRow(i).getCell(6).toString());
						if (objHSSFSheet.getRow(i).getCell(7) != null)
							objTestDataClass.setTestSteps_TestData(objHSSFSheet
									.getRow(i).getCell(7).toString());
						ObjArryListOfTestDataClass.add(objTestDataClass);
					}
				}
			}

			break;
		case "LoginTestData":
			for (int i = 1; i < TotalNumberOfRows; i++) {
				TestDataClass objTestDataClass = new TestDataClass();
				
				
				HSSFRow removingRow = objHSSFSheet.getRow(i);
				
				
				if (isRowEmpty(removingRow)) {
					if (objHSSFSheet.getRow(i).getCell(0) != null)
						objTestDataClass.setTestData_UserName(objHSSFSheet
								.getRow(i).getCell(0).toString());
					if (objHSSFSheet.getRow(i).getCell(1) != null)
						objTestDataClass.setTestData_Password(objHSSFSheet
								.getRow(i).getCell(1).toString());
					ObjArryListOfTestDataClass.add(objTestDataClass);
				}

			}
			break;
			
		default:
			System.out.println("Invalid sheet name");

		}

		return ObjArryListOfTestDataClass;
	}
	
	public ArrayList<String> readTestData(String InputFileName,String InputSheetName) throws IOException 
	{
		
		FileInputStream objFileInputStream = new FileInputStream(new File(
				InputFileName));
		HSSFWorkbook objHSSFWorkbook = new HSSFWorkbook(objFileInputStream);
		HSSFSheet objHSSFSheet = objHSSFWorkbook.getSheet(InputSheetName);
		int TotalNumberOfRows = objHSSFSheet.getLastRowNum() + 1;
		System.out.println("Total rows" + TotalNumberOfRows);
		ArrayList<String> ObjArryListTestDataClass = new ArrayList<String>();
		
		if(InputSheetName=="LoginTestData")
		{
		  
			for (int i = 1; i < TotalNumberOfRows; i++) {
				
				HSSFRow removingRow = objHSSFSheet.getRow(i);
				if (isRowEmpty(removingRow)) {
					if (objHSSFSheet.getRow(i).getCell(0) != null)
						ObjArryListTestDataClass.add(objHSSFSheet
								.getRow(i).getCell(0).toString());
					if (objHSSFSheet.getRow(i).getCell(1) != null)
						ObjArryListTestDataClass .add(objHSSFSheet
								.getRow(i).getCell(1).toString());
					
				}

			}
			
		}
		return ObjArryListTestDataClass;	
		
	}
	public String excelData(String inputFileName,String sheetName,int row,int col) throws IOException
	{ 
		String testData=null;
		DataFormatter df = null;
		FileInputStream objFileInputStream = new FileInputStream(new File(inputFileName));
		HSSFWorkbook objHSSFWorkbook = new HSSFWorkbook(objFileInputStream);
		HSSFSheet objHSSFSheet = objHSSFWorkbook.getSheet(sheetName);
		HSSFRow removingRow = objHSSFSheet.getRow(row);
		if (isRowEmpty(removingRow)) {
			if (objHSSFSheet.getRow(row).getCell(col) != null)
				df=new DataFormatter();
				testData= df.formatCellValue(objHSSFSheet.getRow(row).getCell(col));
						
			
		}
		return testData;
		
	}
	

}
