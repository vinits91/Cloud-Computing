package com.vinit.queue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyBuilder {

	Properties properties = new Properties();

	public PropertyBuilder() {
		try {
			InputStream inputStream = new FileInputStream("QueueConfig.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			
		}
	}

	public  String getProperty(String key) {
		String val = properties.getProperty(key);
		return val;
	}

}
