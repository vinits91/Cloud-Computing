package com.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


//used as a helper to get data from config file.
public class PropertiesController {

	Properties properties = new Properties();

	public PropertiesController() {
		try {
			InputStream inputStream = new FileInputStream("ClientConfig.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			ClientLog.error("Exception: PropertiesController :: PropertiesController " + e.getMessage());
		}
	}

	public String getProperty(String key) {
		String val = properties.getProperty(key);
		return val;
	}

}
