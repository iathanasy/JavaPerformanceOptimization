package com.cd.chapter07.t_01.t_02.safe;

public class Run {

	public static void main(String[] args) {
		ALogin a = new ALogin();
		a.start();
		BLogin b = new BLogin();
		b.start();
		/**
		 * 没加synchronized 数据出现错乱
		 *  name=[b], pass=[bb]
			name=[b], pass=[aa]
			
			//添加就会正常
			name=[b], pass=[bb]
			name=[a], pass=[aa]
		 */
	}
}
