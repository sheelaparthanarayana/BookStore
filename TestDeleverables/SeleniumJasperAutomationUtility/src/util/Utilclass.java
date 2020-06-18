package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Utilclass {

	
	public static String readFromExcel(String filePathName, String WorkSheet, int Row, int Column){		
		String cellvalue=null;
		Workbook workbook=null;
		Boolean isCorrectWorkbook=true;
		FileInputStream file;
	
			try {
				file=new FileInputStream(new File(filePathName));
		
				String fileExtension= FilenameUtils.getExtension(filePathName);
				
				switch(fileExtension.toLowerCase()){
					case "xlsx": 
					workbook = new XSSFWorkbook(file); 
					break;
				
					case "xls": 
					workbook = new HSSFWorkbook(file); 
					break;
					default: isCorrectWorkbook = false; cellvalue="## Incorrect file extension";
					break;	
				}
				if(isCorrectWorkbook==true){
					Sheet sheet = workbook.getSheet(WorkSheet);
				
					
					Cell cell=sheet.getRow(Row-1).getCell(Column-1);
					
				    cell.setCellType(cell.CELL_TYPE_STRING);
				    
					cellvalue=cell.getStringCellValue();
				    		
					workbook.close();
				}				
			}
			catch(NullPointerException e)
			{
				return "";
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		
			return cellvalue;
		}		
	
	public static void writeInExcel(String filePathName, String WorkSheet,int Row ,int Column,String text) throws IOException {
		
		FileInputStream fsIP= new FileInputStream(new File(filePathName));
		
		Workbook wb=new XSSFWorkbook(fsIP); 
		
		Sheet sheet = wb.getSheet(WorkSheet);
		
	Cell cell = null;
		
	
		if (cell == null)
		{
			cell = sheet.getRow(Row-1).createCell(Column-1);
		}	
			
	       cell.setCellType(Cell.CELL_TYPE_STRING);
		   cell.setCellValue(text);		
		
		fsIP.close();
		
		FileOutputStream output_file =new FileOutputStream(new File(filePathName));
		
		wb.write(output_file);
		
		output_file.close();
		
		wb.close();
		
	}
	
	public static ArrayList<String> readColumnValuesFromExcel(String filePathName, String WorkSheet, int Rowstart, int Rowend, int Column){		
		ArrayList<String> cellsvalue = new ArrayList<String>();
		String cellvalue=null;
		Workbook workbook=null;
		Boolean isCorrectWorkbook=true;
		FileInputStream file;
	
			try {
				file=new FileInputStream(new File(filePathName));
		
				String fileExtension= FilenameUtils.getExtension(filePathName);
				
				switch(fileExtension.toLowerCase()){
					case "xlsx": 
					workbook = new XSSFWorkbook(file); 
					break;
				
					case "xls": 
					workbook = new HSSFWorkbook(file); 
					break;
					default: isCorrectWorkbook = false; cellvalue="## Incorrect file extension";
					break;	
				}
				if(isCorrectWorkbook==true){
					Sheet sheet = workbook.getSheet(WorkSheet);
				
				while(Rowstart!=Rowend+1)
				{
					Cell cell=sheet.getRow(Rowstart-1).getCell(Column-1);
					
				    cell.setCellType(cell.CELL_TYPE_STRING);
				    
					cellvalue=cell.getStringCellValue();
				    
					cellsvalue.add(cellvalue);
				    
					Rowstart=Rowstart+1;
				
				}	
				
					workbook.close();
				}				
			}
			catch(NullPointerException e)
			{
				return null;
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		
			return cellsvalue;
		}		
	
	
	
	
	}
		

