package com.cd.chapter07.t_02.dirtyRead;

public class PublicVar {

	public String name = "A";
	public String pass = "AA";
	synchronized public void setValue(String name, String pass){
		try {
			this.name = name;
			Thread.sleep(5000);
			this.pass = pass;
			System.out.println("setValue method thread name:"+ Thread.currentThread().getName()
					+",name: "+ name +",pass:"+ pass);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	synchronized public void getValue(){
		System.out.println("getValue method thread name:"+ Thread.currentThread().getName()
				+",name: "+ name +",pass:"+ pass);
	}
}
