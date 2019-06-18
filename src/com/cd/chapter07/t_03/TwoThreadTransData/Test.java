package com.cd.chapter07.t_03.TwoThreadTransData;
/**
 * 不停while轮询，浪费cpu资源
 * @author cd
 * @date 2019年6月18日 上午10:42:18
 * @desc
 */
public class Test {

	public static void main(String[] args) {
		MyList list = new MyList();
		ThreadA a = new ThreadA(list);
		a.setName("A");
		a.start();
		ThreadB b = new ThreadB(list);
		b.setName("B");
		b.start();
				
	}
}
