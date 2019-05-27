package com.cd.chapter05.dao;

public interface BaseDao<T> {

	void insert(T t);
	
	void delete(Object id);
	
	boolean check(Object id);
}
