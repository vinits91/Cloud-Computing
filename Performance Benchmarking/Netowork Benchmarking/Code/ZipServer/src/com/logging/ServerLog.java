package com.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerLog {
	static BufferedWriter bufferedWriter = null;
	static File serverLog = new File("ServerLog.txt");

	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static void error(String errorString) {
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(serverLog, true));
			bufferedWriter.append("[" + dateFormat.format(new Date()) + "] " + errorString + "\n");
		} catch (IOException e) {

		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {

			}
		}
	}

	public static void infoRandomAccess(String fileName, double time) {
		try {
			File randomAccessLog = new File(fileName);
			bufferedWriter = new BufferedWriter(new FileWriter(randomAccessLog, true));
			bufferedWriter.append(new DecimalFormat("#.#################").format(time) + ",");

		} catch (IOException e) {

		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {

			}
		}
	}

	public static void infoSeqAccess(String fileName, double time) {
		try {
			File seqAccessLog = new File(fileName);
			bufferedWriter = new BufferedWriter(new FileWriter(seqAccessLog, true));
			synchronized (bufferedWriter) {
				bufferedWriter.append(new DecimalFormat("#.#################").format(time) + ",");
			}

		} catch (IOException e) {

		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {

			}
		}
	}

	public static void infoCpuEvalution(String fileName, double time) {
		try {
			File cpuEvalutionLog = new File(fileName + ".csv");
			bufferedWriter = new BufferedWriter(new FileWriter(cpuEvalutionLog, true));

			bufferedWriter.append(new DecimalFormat("#.#################").format(time) + ",");

		} catch (IOException e) {

		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {

			}
		}
	}
}
