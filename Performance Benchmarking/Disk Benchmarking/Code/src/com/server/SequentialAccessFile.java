package com.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.logging.ServerLog;

//reads data sequentially from file
public class SequentialAccessFile {
	PropertiesController propertiesController = new PropertiesController();
	static int i = 0;
	String fileName;
	int size;

	public SequentialAccessFile(String fileName, int size) {
		this.fileName = fileName;
		this.size = size;
	}

	public void sequentailAccess() {
		if (propertiesController.getProperty("readorwrite").equals("read")) {
			readSeq();
		} else if (propertiesController.getProperty("readorwrite").equals("write")) {
			writeSeq();
		}
	}

	//reads the sequence of data from file based on size provided to read
	private void readSeq() {
		long s, d;
		double diff;
		File file = new File("DiskFile.txt");
		FileInputStream fileInputStream = null;
		byte[] bs = new byte[size];
		try {
			int numBytes;
			fileInputStream = new FileInputStream(file);
			while (true) {
				s = System.nanoTime();
				numBytes = fileInputStream.read(bs);
				d = System.nanoTime();
				String str = new String(bs);
				if (str.contains("$"))
					break;
				diff = (d - s);
				diff = diff / 1e6;
				writeLog("_Read" + fileName, diff);
			}
		} catch (FileNotFoundException e) {
			ServerLog.error("Exception: SequentialAccessFile :: readSeq " + e.getMessage());
		} catch (IOException e) {
			ServerLog.error("Exception: SequentialAccessFile :: readSeq " + e.getMessage());
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				ServerLog.error("Exception: SequentialAccessFile :: readSeq " + e.getMessage());
			}
		}

	}
	
	
//write sequentially from file to the file based on the buffer size given
	private void writeSeq() {
		long s, d;
		double diff;
		File file1 = new File("DiskFile.txt");
		File file2 = new File("SequentialAccess.txt");
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		byte[] bs = new byte[size];
		try {
			int numBytes;
			fileInputStream = new FileInputStream(file1);
			fileOutputStream = new FileOutputStream(file2);
			while (fileInputStream.read(bs) != -1) {
				s = System.nanoTime();
				fileOutputStream.write(bs, 0, bs.length);
				d = System.nanoTime();
				diff = (d - s);
				diff = diff / 1e6;
				writeLog("_Write" + fileName, diff);
			}
		} catch (FileNotFoundException e) {
			ServerLog.error("Exception: SequentialAccessFile :: writeSeq " + e.getMessage());
		} catch (IOException e) {
			ServerLog.error("Exception: SequentialAccessFile :: writeSeq " + e.getMessage());
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				ServerLog.error("Exception: SequentialAccessFile :: writeSeq " + e.getMessage());
			}
		}
	}

	public void writeLog(String fileName, double diff) {
		ServerLog.infoSeqAccess(fileName, diff);
	}
}
