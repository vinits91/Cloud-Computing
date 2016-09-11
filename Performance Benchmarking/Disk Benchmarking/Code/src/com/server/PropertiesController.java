package com.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.logging.ServerLog;

public class PropertiesController {

	Properties properties = new Properties();

	public PropertiesController() {
		try {
			InputStream inputStream = new FileInputStream("ServerConfig.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			ServerLog.error("Exception: PropertiesController :: PropertiesController " + e.getMessage());
		}
	}

	public String getProperty(String key) {
		String val = properties.getProperty(key);
		return val;
	}

}
