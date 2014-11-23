package com.pack.ExcelReadWrite;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import com.pack.Results.TestResultClass;

public class ExcelWritingClass {

	public void writeExcel(ArrayList<TestResultClass> objArryaListTestResultClass) throws IOException{
		
		HSSFWorkbook objHSSFWorkbook = null;
		FileOutputStream objFileOutputStream = null;
		try{
		objFileOutputStream=new FileOutputStream(new File("C:/Documents and Settings/abudagala/workspace/FilmScanning/TestData/TestResult.xls"));
		objHSSFWorkbook = new HSSFWorkbook();
		HSSFSheet objHSSFSheet=objHSSFWorkbook.createSheet("TestResults");
		
		 HSSFFont font = objHSSFWorkbook.createFont();
		 HSSFCellStyle cellStyle = objHSSFWorkbook.createCellStyle();
		 
		 font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		 font.setColor(HSSFColor.BLUE.index);	 
		 cellStyle.setFont(font);
		 {
			 HSSFRow objRow=objHSSFSheet.createRow(0);
				 
			 HSSFCell _0_objCell=objRow.createCell(0);
				
			 
			 _0_objCell.setCellStyle(cellStyle);
			 _0_objCell.setCellValue("Test CaseID");
			 HSSFCell _1_objCell=objRow.createCell(1);
			 
			 _1_objCell.setCellStyle(cellStyle);
			 _1_objCell.setCellValue("Test StepID");
			 
			 HSSFCell _2_objCell=objRow.createCell(2);
			 
			 _2_objCell.setCellStyle(cellStyle);		 
			 _2_objCell.setCellValue("Test Case Title");
			 
			 HSSFCell _3_objCell=objRow.createCell(3);
			 
			 _3_objCell.setCellStyle(cellStyle);		 
			 _3_objCell.setCellValue("Test Case steps");
			 
			 HSSFCell _4_objCell=objRow.createCell(4);
			 
			 _4_objCell.setCellStyle(cellStyle);
			 _4_objCell.setCellValue("Result");
			 
			 HSSFCell _5_objCell=objRow.createCell(5);
			 
			 _5_objCell.setCellStyle(cellStyle);
			 _5_objCell.setCellValue("Error Message");
			
		 
	 }
		
		
		 Iterator<TestResultClass> ir=objArryaListTestResultClass.iterator();
		 int RowNum=1;
		 while(ir.hasNext()) {
				HSSFRow objRow=objHSSFSheet.createRow(RowNum++);
			 TestResultClass objTestResultClass=ir.next();
			 
			 HSSFCell _0_objCell=objRow.createCell(0);
			 _0_objCell.setCellValue(objTestResultClass.getTestCaseID());
			 HSSFCell _1_objCell=objRow.createCell(1);
			 _1_objCell.setCellValue(objTestResultClass.getTestStepID());
					 
			 HSSFCell _2_objCell=objRow.createCell(2);
			 _2_objCell.setCellValue(objTestResultClass.getTestCaseDesc());
			 
			 HSSFCell _3_objCell=objRow.createCell(3);
			 _3_objCell.setCellValue(objTestResultClass.getTestStepDesc());		 
			 
			 HSSFCell _4_objCell=objRow.createCell(4);
			 if(objTestResultClass.getTestResult().equalsIgnoreCase("Passed")){
				 HSSFFont font1 = objHSSFWorkbook.createFont();
				 HSSFCellStyle cellStyle1 = objHSSFWorkbook.createCellStyle();
				 font1.setColor(HSSFColor.GREEN.index);	
				 cellStyle1.setFont(font1);
				 _4_objCell.setCellStyle(cellStyle1);
				 _4_objCell.setCellValue(objTestResultClass.getTestResult());
			 }
			 else{
				 HSSFFont font2 = objHSSFWorkbook.createFont();
				 HSSFCellStyle cellStyle2 = objHSSFWorkbook.createCellStyle();
				 font2.setColor(HSSFColor.RED.index);	
				 cellStyle2.setFont(font2);
				 _4_objCell.setCellStyle(cellStyle2);
				 _4_objCell.setCellValue(objTestResultClass.getTestResult());
				 
			 }
			 HSSFCell _5_objCell=objRow.createCell(5);
			 _5_objCell.setCellValue(objTestResultClass.getTestResultException());
			
			 
		 }
		}
		catch(Exception e){
			System.out.println("Exception Writing data to Excel file:: "+e.getMessage());
			e.printStackTrace();
		}
		finally{
		objHSSFWorkbook.write(objFileOutputStream);
		objFileOutputStream.flush();
		objFileOutputStream.close();
		}
		
		
	}
	
	
}
