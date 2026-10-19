package com.utility;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {

	
	public static String readProperty(Env env, String propertyName) {
		
		File propFile=new File(System.getProperty("user.dir")+"//config//"+env+".properties");
		FileReader fileReader=null;
		Properties prop=new Properties();
		try {
		 fileReader=new FileReader(propFile);
		 prop.load(fileReader);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		String value=prop.getProperty("URL");
		return value;
	}
	
}
