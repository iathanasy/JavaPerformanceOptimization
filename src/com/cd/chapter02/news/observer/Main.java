package com.cd.chapter02.news.observer;

public class Main {

	public static void main(String[] args) {
		SubjectImpl subject = new SubjectImpl();
		
		CurrentObserver current = new CurrentObserver(subject);
		StatisticsObserver statis = new StatisticsObserver(subject);
		subject.setMessage("zz");
		
	}
}
