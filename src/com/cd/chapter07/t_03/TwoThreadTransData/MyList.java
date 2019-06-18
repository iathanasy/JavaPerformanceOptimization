package com.cd.chapter07.t_03.TwoThreadTransData;

import java.util.ArrayList;

public class MyList {

	private ArrayList list = new ArrayList();
	
	public void add(){
		list.add("zz");
	}
	
	public int getSize(){
		return list.size();
	}
}
