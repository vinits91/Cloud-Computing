package com.server;

public class DiskEvalution {

	int size;

	public DiskEvalution(int size) {
		this.size = size;
	}

	PropertiesController propertiesController = new PropertiesController();
	String blocksize = null;
	String readOrWrite = null;
	String seqOrRandom = null;

	String file1 = "File";
	
	//based on number of threads, it invokes the Disk evaluation.
	public void runDiskEvaluation() {
		if (propertiesController.getProperty("numberofthreads").equals("1")) {
			Thread thread = new Thread(new ComputeDiskEvaluation("SingleThread" + file1 + "_" + size + "Byte", size));
			thread.start();

		}
		if (propertiesController.getProperty("numberofthreads").equals("2")) {
			Thread thread1 = new Thread(new ComputeDiskEvaluation("SingleThread" + file1 + "_" + size + "Byte", size));
			Thread thread2 = new Thread(new ComputeDiskEvaluation("TwoThread" + file1 + "_" + size + "Byte", size));
			thread1.start();
			thread2.start();

		}

	}
}
