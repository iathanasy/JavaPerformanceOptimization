package com.cd.chapter07.t_03.wait_notify_size3;

import java.util.ArrayList;
import java.util.List;

public class MyList {
	private static List list = new ArrayList();
	public static void add(){
		list.add("any");
	}
	public static int size(){
		return list.size();
	}
}
