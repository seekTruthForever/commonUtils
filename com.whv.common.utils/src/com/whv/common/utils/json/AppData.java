package com.whv.common.utils.json;

public class AppData {

	/*״̬*/
	private int status;

	/*״̬*/
	private String message;

	/*״̬*/
	private String currentTime;

	/*ҵ������*/
	private Object data;
	/*����������*/
	private Object collections;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getCollections() {
		return collections;
	}

	public void setCollections(Object collections) {
		this.collections = collections;
	}
}
