package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {

	public static Iterator<User> readExcelFile(String fileName) {
		// TODO Auto-generated method stub
		
		File excelFile=new File(System.getProperty("user.dir")+"//testData//"+fileName);
		XSSFWorkbook workBook;
		XSSFSheet sheet;
		Row row;
		Cell cell;
		String emailAddress;
		String password;
		List<User> userList = null;
		User userData;
		
		try {
			 workBook=new XSSFWorkbook(excelFile);
			 sheet=workBook.getSheet("LoginTestData");
			 Iterator<Row> rowIterator=sheet.rowIterator();
			 userList=new ArrayList<User>();
			 rowIterator.next();
			 while(rowIterator.hasNext()) {
				 row=rowIterator.next();
				 emailAddress=row.getCell(0).toString();
				 password=row.getCell(1).toString();
				 userData=new User(emailAddress,password);
				 userList.add(userData);
				 
			 }
			 workBook.close();
			 
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList.iterator();
		

	}

}
