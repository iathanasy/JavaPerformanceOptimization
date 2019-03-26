package com.cd.chapter02.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author cd
 * @date 2019��3��25�� ����5:22:20
 * @desc ��Ԫ����
 */
public class ReportManagerFactory {

	Map<String, IReportManager> financial = new HashMap<String, IReportManager>();
	Map<String, IReportManager> employee = new HashMap<String, IReportManager>();
	
	IReportManager getFinancialReportManager(String tenantId){
		IReportManager r = financial.get(tenantId);
		if(r == null){
			r = new FinancialReportManager(tenantId);
			financial.put(tenantId, r);//ά���Ѵ�������Ԫ����
		}
		return r;
	}
	
	IReportManager getEmployeeReportManager(String tenantId){
		IReportManager r = employee.get(tenantId);
		if(r == null){
			r = new EmployeeReportManager(tenantId);
			employee.put(tenantId, r);
		}
		return r;
	}
}
