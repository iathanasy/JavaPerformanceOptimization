package com.cd.chapter02.flyweight;
/**
 * 
 * @author cd
 * @date 2019��3��25�� ����5:14:11
 * @desc ������Ԫ�� 
 */
public class FinancialReportManager implements IReportManager{
	protected String tenantId = null;//�⻧ID
	//���񱨱�
	public FinancialReportManager(String tenantId){
		this.tenantId = tenantId;
	}

	public String createReport() {
		
		return "this is a financial report";
	}

}
