package com.cd.chapter07.t_01.t_02.safe;

public class LoginServlet {

	private static String name;
	private static String pass;
	
	public static synchronized void doPost(String pname, String tpass){
		try {
			name = pname;
			if(pname.equals("a")) Thread.sleep(5000);
		
			pass = tpass;
			System.out.println(String.format("name=[%s], pass=[%s]", name, pass));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
