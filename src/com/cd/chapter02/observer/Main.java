package com.cd.chapter02.observer;

public class Main {

	public static void main(String[] args) {
		ConcreteSubject s = new ConcreteSubject(); //主题
		
		User u = new User(1,"a");
		User u1 = new User(2,"b");
		User u2 = new User(3,"c");
		
		IObserver o = new ConcreteObserver();//观察者
		s.attach(o);
		s.setUser(u);
		s.detach(o);
		s.setUser(u1);
		s.setUser(u2);
		
	}
}
