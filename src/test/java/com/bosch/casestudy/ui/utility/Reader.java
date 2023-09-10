package com.bosch.casestudy.ui.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Reader
{
	public static String readProperty(String pathToFile, String propertyKey)
	{
		Properties properties = Reader.readFile(pathToFile);
        return properties.getProperty(propertyKey);
	}

	public static Properties readFile(String pathToFile)
	{
		File src = new File(pathToFile);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(src);
		} catch (FileNotFoundException fileError) {
			fileError.printStackTrace();
		}
		Properties pro = new Properties();
		try {
			pro.load(fis);
		} catch (IOException propError) {
			propError.printStackTrace();
		}
		return pro;
	}
}
