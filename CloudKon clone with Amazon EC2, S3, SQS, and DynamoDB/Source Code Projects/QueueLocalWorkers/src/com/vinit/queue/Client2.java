package com.vinit.queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class Client2 {
	static BufferedReader bufferedReader;
	static LinkedBlockingQueue<Task> inputQueue = new LinkedBlockingQueue<Task>();
	static LinkedBlockingQueue<Task> outputQueue = new LinkedBlockingQueue<Task>();
	static PropertyBuilder propertyBuilder = new PropertyBuilder();
	static long stop, start = 0, totalTimeDifference;
	static double diff;

	public static void main(String[] args) {
		Task outputTask;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Workload File Name");
		String wrkloadfile = scanner.nextLine();
		System.out.println("Enter Number of Threads for worker");
		int numThreads = scanner.nextInt();
		System.out.println("Enter Sleep Task duration to generate Workload File");
		int sleepTask = scanner.nextInt();
		int numberofTaskPerWorker = 0;
		if (sleepTask == 10) {
			numberofTaskPerWorker = 10000;
		} else if (sleepTask == 1000) {
			numberofTaskPerWorker = 100;
		} else if (sleepTask == 10000) {
			numberofTaskPerWorker = 10;
		}

		try {
			File fout = new File(wrkloadfile);
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (int i = 0; i < (numberofTaskPerWorker * numThreads); i++) {
				bw.write("sleep " + sleepTask);
				bw.newLine();
			}

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * int threadsNumber = numThreads; List<Thread> threadpool = new
		 * ArrayList<>(threadsNumber); try { bufferedReader = new
		 * BufferedReader(new FileReader(new File(wrkloadfile))); String task;
		 * long index = 1; for (int i = 0; i < threadsNumber; i++) {
		 * threadpool.add(new Thread(new Local(inputQueue, outputQueue)));
		 * threadpool.get(i).start(); } while ((task =
		 * bufferedReader.readLine()) != null) { String[] strArray = task.split(
		 * " "); Task enqueueTask = new Task(index,
		 * Integer.parseInt(strArray[1]), 1); index++; try {
		 * enqueue(enqueueTask); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } } long outputIndex
		 * = 1; while (true) {
		 * 
		 * try { outputTask = outputQueue.take(); } catch (InterruptedException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * outputIndex++;
		 * 
		 * if (outputIndex == index) { stop = System.nanoTime();
		 * totalTimeDifference = (stop - start); System.out.println(
		 * "----Time in nanoseconds----" + totalTimeDifference); System.exit(0);
		 * } } } catch (IOException e) { e.printStackTrace(); }
		 */
	}

	public static void enqueue(Task enqueueTask) throws InterruptedException {

		if (start == 0) {
			start = System.nanoTime();
		}
		inputQueue.put(enqueueTask);

	}

}
