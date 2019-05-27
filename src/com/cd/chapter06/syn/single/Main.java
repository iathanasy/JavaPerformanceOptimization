package com.cd.chapter06.syn.single;
/**
 * 创建一个门(Gate) 并让3个人(UserThread)不断的通过
 * @author cd
 * @date 2019年5月16日 下午3:24:23
 * @desc
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Testing Gate, hit Ctrl+C to exit.");
		Gate gate = new Gate();
		new UserThread(gate,"A", "A1").start();
		new UserThread(gate,"B", "B2").start();
		new UserThread(gate,"C", "C3").start();
		
		//************BROKEN*************Gate [counter=147279, name=C, address=B2]
		//说明Gate这个类并不安全 因为pass方法不是线程安全 
		//在pass和toString方法中加上synchronized 现在无论等多久都不会显示 BROKEN 了
	}
}
