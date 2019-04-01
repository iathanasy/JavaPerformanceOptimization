package com.cd.chapter04.guarded;

public class Request {

	private String name;
	private Data response; //参考Future模式中实现
	
	public synchronized Data getResponse() {
		return response;
	}


	public synchronized void setResponse(Data response) {
		this.response = response;
	}


	public Request(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return "Request [name=" + name + "]";
	}
	
	
}
