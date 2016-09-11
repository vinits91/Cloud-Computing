package com.server;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComputeDiskEvaluation implements Runnable {
	PropertiesController propertiesController = new PropertiesController();
	String fileName = null;
	int size;
	final CyclicBarrier cyclicBarrier = new CyclicBarrier(
			Integer.parseInt(propertiesController.getProperty("numberofthreads")));

	public ComputeDiskEvaluation(String fileName, int size) {
		this.size = size;
		this.fileName = fileName;
	
	
	//based on the data transferd invokes the Random and Sequential access.
	@Override
	public void run() {
		
		if (propertiesController.getProperty("randomorseq").equals("random")) {
			RandomAccess randomAccessFile = new RandomAccess("Random" + fileName + ".csv", size);
			randomAccessFile.randomAccess();
		} else if (propertiesController.getProperty("randomorseq").equals("seq")) {
			SequentialAccessFile sequentialAccessFile = new SequentialAccessFile("Seq" + fileName + ".csv", size);
			sequentialAccessFile.sequentailAccess();
		}
	}

}
