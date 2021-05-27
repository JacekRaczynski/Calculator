package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
	private static String path = "src/config.properties";
	private static FileInputStream fileInput;
	private static Properties prop;
	
	
	public static void  setLocalizationOfProperiesFile(String path) throws IOException  {
		try {
			fileInput = new FileInputStream(path);	
			prop = new Properties();
			prop.load(fileInput);
			
		}
		catch(FileNotFoundException err)
		{
			System.out.println("Not content available");
			}
	}
	public static String getProperty(String name) throws IOException
	{
		if(prop == null)
			setLocalizationOfProperiesFile(path);
		return prop.getProperty(name);
	}

}
