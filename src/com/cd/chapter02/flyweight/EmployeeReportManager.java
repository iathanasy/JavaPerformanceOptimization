package com.cd.chapter02.flyweight;

/**
 * 
 * @author cd
 * @date 2019��3��25�� ����5:18:48
 * @desc ������Ԫ��  
 */
public class EmployeeReportManager implements IReportManager{
	protected String tenantId = null;//�⻧Id
	//Ա������
	public EmployeeReportManager(String tenantId){
		this.tenantId = tenantId;
	}

	public String createReport() {
		
		return "this is a employee report";
	}

}
