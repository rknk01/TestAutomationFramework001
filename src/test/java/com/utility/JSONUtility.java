package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

public class JSONUtility {

	public static Environment readJSON(Env env) {
		
		File file=new File(System.getProperty("user.dir")+"\\config\\config.json");
		FileReader fileReader=null;
		try {
			 fileReader=new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson=new Gson();
		Config config=gson.fromJson(fileReader, Config.class);
		Environment environment=config.getEnvironment().get("QA");
		return environment;
		
		
		
	}
}
