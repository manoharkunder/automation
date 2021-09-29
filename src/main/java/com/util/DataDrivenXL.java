package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenXL {
	
	public ArrayList<String> dataFromExcel(String sheetName) throws IOException
	{
	ArrayList<String> ar =new ArrayList<String> ();
	FileInputStream fiss=new FileInputStream("/home/niveus/Desktop/datafile.xlsx");

	XSSFWorkbook workbook=new XSSFWorkbook(fiss);
	
	int sheets =workbook.getNumberOfSheets();
		System.out.println(sheets);		
	for(int i=0;i<sheets;i++)
	{
		
		//finding a sheet named Credentials
		if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
	     {
			      XSSFSheet sheet=workbook.getSheetAt(i); //till here
			      // next is finding a coloumn named testcases 
			      Iterator<Row> rows=sheet.iterator(); //sheet is collection of rows
			      Row firstRow=rows.next();  //gets us first row 
			    			      
//			      Iterator<Cell> cell=firstRow.cellIterator(); //here iterating through first row -row is collection of cells
//			      int k=0;
//			      int column=0;
//			      while(cell.hasNext())
//			      {
//			    	  Cell value=cell.next();
//			    	 // if(value.getStringCellValue().equalsIgnoreCase("Testcases")) //getting column number of testcases cloumn
//			    			 // {
//			    		        column=k;
//			    			 // }
//			    	  k++;
//			    	  
//			      }
		//	   Log.info(column);
			      
			      /*while(rows.hasNext())
			      {
			    	  Row r=rows.next();
			    	  if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
			    			  {
			    		       Iterator<Cell> ce=r.cellIterator();
			    		       while(ce.hasNext())
			    		       {
			    		    	  ar.add(ce.next().getStringCellValue()); 
			    		    	 // Log.info(ar.get(0));  
			    		       } } }*/
			      
			           //Row firsttRow=sheet.getRow(0);
			   
			           while(rows.hasNext())
			           {
			        	   Row r=rows.next();
			        	   Iterator<Cell> ce=r.cellIterator();
			        	   while(ce.hasNext())
		    		       {
		    		    	  ar.add(ce.next().getStringCellValue()); 
		    		    	 // Log.info(ar.get(0));  
		    		       }
			        	   
			           }
			      
			              /* int lastRow=sheet.getLastRowNum();
			       
			        	   while(rows.hasNext())
			        	   {
			        	     for(i=1;i<=lastRow;i++)
			        	     {
			        	    	Row r=sheet.getRow(i);
			        	    	//Log.info(sheet.getRow(i));
			                    Iterator<Cell> ce=r.cellIterator();
			    	            while(ce.hasNext())
	    		                {
	    		    	          ar.add(ce.next().getStringCellValue()); 
	    		    	          // Log.info(ar.get(1));
	    		    	         //Log.info(ar.get(2));  
	    		                }
			                  }
			    	 }*/
	     }
	}
	return ar;
	}}
