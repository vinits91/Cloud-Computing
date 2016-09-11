package com.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;

import com.logging.ServerLog;

public class RandomAccess {
	static int i;
	PropertiesController propertiesController = new PropertiesController();
	String fileName;
	int size;

	public RandomAccess(String fileName, int size) {
		this.fileName = fileName;
		this.size = size;
	}
	//based on read and write calls the respective methods
	public void randomAccess() {
		if (propertiesController.getProperty("readorwrite").equals("read")) {
			readRandom();
		} else if (propertiesController.getProperty("readorwrite").equals("write")) {
			writeRandom();
		}
	}

	//writes randomly to the file by reading the data from one file and writing it to another randomly
	private void writeRandom() {
		RandomAccessFile inRandomAccessFile = null;
		RandomAccessFile outRandomAccessFile = null;
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		File file1 = new File("DiskFile.txt");
		File file2 = new File("RandomAccess.txt");
		long s, d;
		double diff;
		try {
			inRandomAccessFile = new RandomAccessFile(file1, "r");
			outRandomAccessFile = new RandomAccessFile(file2, "rw");
			ByteBuffer dst = ByteBuffer.allocate(size);

			inChannel = inRandomAccessFile.getChannel();
			outChannel = outRandomAccessFile.getChannel();
			while (inChannel.read(dst) != -1) {
				s = System.nanoTime();
				dst.position(0);
				outChannel.write(dst);
				d = System.nanoTime();
				diff = (d - s);
				diff = diff / 1e6;
				writeLog("Write_" + fileName, diff);
				dst.clear();
			}

		} catch (FileNotFoundException e) {
			ServerLog.error("Exception: RandomAccess :: writeRandom " + e.getMessage());
		} catch (IOException e) {
			ServerLog.error("Exception: RandomAccess :: writeRandom " + e.getMessage());
		} finally {
			try {
				inChannel.close();
				outChannel.close();
				inRandomAccessFile.close();
				outRandomAccessFile.close();
			} catch (Exception e) {
			}
		}
	}

	//reads the data from file randomly and logs the time
	private void readRandom() {
		RandomAccessFile randomAccessFile = null;
		File file = new File("DiskFile.txt");
		long s, d;
		double diff;
		try {
			randomAccessFile = new RandomAccessFile(file, "r");
			SeekableByteChannel seekableByteChannel = randomAccessFile.getChannel();
			ByteBuffer dst = ByteBuffer.allocate(size);
			int length;
			while (true) {
				s = System.nanoTime();
				dst.position(0);
				seekableByteChannel.read(dst);
				d = System.nanoTime();
				byte[] b = dst.array();
				String str = new String(b);
				if (str.contains("$"))
					break;
				diff = (d - s);
				diff = diff / 1e6;
				writeLog("Read_" + fileName, diff);
				dst.clear();
			}

		} catch (FileNotFoundException e) {
			ServerLog.error("Exception: RandomAccess :: readRandom " + e.getMessage());
		} catch (IOException e) {
			ServerLog.error("Exception: RandomAccess :: readRandom " + e.getMessage());
		} finally {
			try {
				randomAccessFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeLog(String fileName, double diff) {
		ServerLog.infoRandomAccess(fileName, diff);
	}
}
