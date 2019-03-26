package com.cd.chapter02.observer;
/**
 * 
 * @author cd
 * @date 2019��3��26�� ����11:05:05
 * @desc �۲�Ķ��� �û���Ϣ
 */
public class User {
	private long id;
	private String name;
	
	
	public User(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
