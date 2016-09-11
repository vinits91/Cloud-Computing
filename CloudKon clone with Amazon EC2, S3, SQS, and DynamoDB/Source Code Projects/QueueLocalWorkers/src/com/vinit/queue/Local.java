package com.vinit.queue;

import java.util.concurrent.LinkedBlockingQueue;

//local workers responsible to execute the task
public class Local implements Runnable {
	LinkedBlockingQueue<Task> inputQueue;
	static LinkedBlockingQueue<Task> outputQueue;

	public Local(LinkedBlockingQueue<Task> queue, LinkedBlockingQueue<Task> outputQueue) {
		this.inputQueue = queue;
		Local.outputQueue = outputQueue;
	}

	@Override
	public void run() {
		Task task = null;
		while (true) {
				try {
					//waits for task in queue
					task = inputQueue.take();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			try {
				//Executes the task
				Thread.sleep(task.getTaskSleepDuration());
				//send the response of task execution
				task.setResult(0);
				enqueue(task);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	//putting response on the response queue
	public static void enqueue(Task enqueueTask) {
		
			try {
				outputQueue.put(enqueueTask);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
