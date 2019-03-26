package com.cd.chapter02.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author cd
 * @date 2019年3月25日 下午5:22:20
 * @desc 享元工厂
 */
public class ReportManagerFactory {

	Map<String, IReportManager> financial = new HashMap<String, IReportManager>();
	Map<String, IReportManager> employee = new HashMap<String, IReportManager>();
	
	IReportManager getFinancialReportManager(String tenantId){
		IReportManager r = financial.get(tenantId);
		if(r == null){
			r = new FinancialReportManager(tenantId);
			financial.put(tenantId, r);//维护已创建的享元对象
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
