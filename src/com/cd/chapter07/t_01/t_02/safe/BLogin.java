package com.cd.chapter07.t_01.t_02.safe;

public class BLogin extends Thread{

	@Override
	public void run() {
		LoginServlet.doPost("b", "bb");
	}
}
