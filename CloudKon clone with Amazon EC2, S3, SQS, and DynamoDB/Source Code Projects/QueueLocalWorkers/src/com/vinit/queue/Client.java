package com.vinit.queue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

//client file for in-memory Queue, which creates pool of worker threads and reads tasks from workload file
//and schedules workers
public class Client {
	static BufferedReader bufferedReader;
	static LinkedBlockingQueue<Task> inputQueue = new LinkedBlockingQueue<Task>();//input quque for tasks
	static LinkedBlockingQueue<Task> outputQueue = new LinkedBlockingQueue<Task>();//response quque
	static PropertyBuilder propertyBuilder = new PropertyBuilder();
	static long stop, start = 0, totalTimeDifference;
	static double diff;

	public static void main(String[] args) {
		Task outputTask;
		String workload_file = args[6];
		List<Thread> threadpool = new ArrayList<>(Integer.parseInt(args[4]));
		try {
			bufferedReader = new BufferedReader(new FileReader(new File(workload_file)));
			String task;
			long index = 1;
			//starts pool of threads which wil wait for tasks in queue in polling state
			for (int i = 0; i < Integer.parseInt(args[4]); i++) {
				threadpool.add(new Thread(new Local(inputQueue, outputQueue)));
				threadpool.get(i).start();
			}
			//reads tasks from workload file and put the tasks on queue
			while ((task = bufferedReader.readLine()) != null) {
				String[] strArray = task.split(" ");
				Task enqueueTask = new Task(index, Integer.parseInt(strArray[1]), 1);
				index++;
				try {
					enqueue(enqueueTask);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			long outputIndex = 1;
			while (true) {
				//Reading the response from workers threads about exceution of tasks
				try {
					outputTask = outputQueue.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				outputIndex++;

				if (outputIndex == index) {
					stop = System.nanoTime();
					totalTimeDifference = (stop - start);
					System.out.println("----Time in nanoseconds----" + totalTimeDifference);
					System.exit(0);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//putting tasks on quque
	public static void enqueue(Task enqueueTask) throws InterruptedException {

		if (start == 0) {
			start = System.nanoTime();
		}
		inputQueue.put(enqueueTask);

	}

}
