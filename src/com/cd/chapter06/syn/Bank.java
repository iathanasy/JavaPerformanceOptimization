package com.cd.chapter06.syn;

public class Bank {

	private int money;
	private String name;
	public Bank(int money, String name) {
		super();
		this.money = money;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	//存
	public synchronized void deposit(int m){
		money += m;
	}
	//取
	public synchronized boolean withdraw(int m){
		if(money >= m){
			money -= m;
			return true;//已取
		}else{
			return false; //余额不足
		}
	}
	@Override
	public String toString() {
		return Thread.currentThread().getName()+ "Bank [money=" + money + ", name=" + name + "]";
	}
	
	
}
