package com.cd.chapter02.flyweight;

/**
 * 
 * @author cd
 * @date 2019年3月25日 下午5:14:11
 * @desc 具体享元类
 */
public class FinancialReportManager implements IReportManager {
	protected String tenantId = null;// 租户ID

	// 财务报表
	public FinancialReportManager(String tenantId) {
		this.tenantId = tenantId;
	}

	public String createReport() {

		return "this is a financial report";
	}

}
