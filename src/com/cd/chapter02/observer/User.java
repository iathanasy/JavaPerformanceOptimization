package com.cd.chapter02.observer;

/**
 * 
 * @author cd
 * @date 2019年3月26日 上午11:05:05
 * @desc 观察的对象 用户信息
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
