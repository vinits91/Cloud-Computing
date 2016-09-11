package com.vinit.queue;

import java.io.Serializable;

//POJO class for task details
public class Task implements Serializable {

	public Task(long taskId, int taskSleepDuration,int result) {
		super();
		this.taskId = taskId;
		this.taskSleepDuration = taskSleepDuration;
		this.result=result;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long taskId;
	private int taskSleepDuration;
	private int result;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public int getTaskSleepDuration() {
		return taskSleepDuration;
	}

	public void setTaskSleepDuration(int taskSleepDuration) {
		this.taskSleepDuration = taskSleepDuration;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
