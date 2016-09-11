package com.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


//used to write logs to file
public class ClientLog {
	static BufferedWriter bufferedWriter = null;
	static File file = new File("ClientLog.txt");
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	//writing error to ClientLog.txt file
	public static void error(String errorString) {
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.append("[" + dateFormat.format(new Date()) + "] " + errorString + "\n");
		} catch (IOException e) {
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {

			}
		}
	}
	
//writes informations to Log file
	public static void infoLog(String fileName, double time) {
		try {
			File clientLog = new File(fileName + ".csv");
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(clientLog, true));
			synchronized (bufferedWriter) {
				bufferedWriter.append(new DecimalFormat("#.#################").format(time) + ",");
				bufferedWriter.close();
			}

		} catch (IOException e) {

		}
	}
}
