package automationLabsMain.playwright;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class configFiles {
	public Properties properties;
	public String word;
	public String value;
	public String filePath = System.getProperty("user.dir") + "//src/main//java//config.properties";
	
	public String url = readData(filePath, "url");
	public String browserName = readData(filePath, "browserName");
	public String Username = readData(filePath, "username");
	public String Password = readData(filePath, "password");
	
	public String readData(String file, String key) {
		try {
			FileInputStream fileIn = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileIn);
			fileIn.close();

			FileReader reader = new FileReader(file);
			properties.load(reader);
			value = properties.getProperty(key);
		} catch (FileNotFoundException e) {
			System.out.println("Config file not locatable");

		} catch (IOException e) {
			System.out.println("Config file not readable");
		}
		return value;
	}
}
