package com.cd.chapter02.flyweight;

/**
 * 
 * @author cd
 * @date 2019��3��25�� ����5:28:56
 * @desc
 */
public class Main {

	public static void main(String[] args) {
		ReportManagerFactory factory = new ReportManagerFactory();
		IReportManager re = factory.getEmployeeReportManager("A");
		System.out.println(re.createReport());
	}
}
