package com.cd.chapter02.flyweight;

/**
 * 
 * @author cd
 * @date 2019年3月25日 下午5:18:48
 * @desc 具体享元类
 */
public class EmployeeReportManager implements IReportManager {
	protected String tenantId = null;// 租户Id

	// 员工报表
	public EmployeeReportManager(String tenantId) {
		this.tenantId = tenantId;
	}

	public String createReport() {

		return "this is a employee report";
	}

}
