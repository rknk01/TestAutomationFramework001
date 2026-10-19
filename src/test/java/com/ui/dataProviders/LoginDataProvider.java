package com.ui.dataProviders;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;

public class LoginDataProvider {

	@DataProvider(name="LoginTestDataProvider")
	public Iterator<Object[]> loginDataProvider() {
		Gson gson=new Gson();
		
		File jsonFIle=new File(System.getProperty("user.dir")+"//testData//loginData.json");
		FileReader fileReader = null;
		try {
			 fileReader=new FileReader(jsonFIle);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		TestData testData=gson.fromJson(fileReader, TestData.class);
		List<Object[]> dataToReturn=new ArrayList<Object[]>();
		
		for(User user:testData.getData()) {
			dataToReturn.add(new Object[] {user});
		}
		
		return dataToReturn.iterator();
		
		
	}
	
	@DataProvider(name="LoginTestCSVDataProvider")
	public Iterator<User> loginCSVDataProvider() {
		return CSVReaderUtility.readCSVFile("loginData.csv");
	}
	
	@DataProvider(name="LoginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() {
		return ExcelReaderUtility.readExcelFile("loginData.xlsx");
	}
}
