package com.cd.chapter02.news.builder;

/**
 * 
 * @author cd
 * @date 2019年6月4日 下午2:20:15
 * @desc 原始Computer
 */
public class Computer {

	private String mainBoard;//主板
	private String cpu;//cpu
	private String hd;//硬盘
	private String powerSupplier;//电源
	private String grahicsCard;//显卡
	
	//其它一些可选参数
	private String mouse;//鼠标
	private String computerCase;//机箱
	private String mousePad;//鼠标垫
	private String other;//其它配件
	
	 
	public Computer(String mainBoard, String cpu, String hd,
			String powerSupplier, String grahicsCard, String mouse,
			String computerCase, String mousePad, String other) {
		super();
		this.mainBoard = mainBoard;
		this.cpu = cpu;
		this.hd = hd;
		this.powerSupplier = powerSupplier;
		this.grahicsCard = grahicsCard;
		this.mouse = mouse;
		this.computerCase = computerCase;
		this.mousePad = mousePad;
		this.other = other;
	}


	public Computer(String mainBoard, String cpu, String hd,
			String powerSupplier, String grahicsCard, String mouse,
			String computerCase, String mousePad) {
		super();
		this.mainBoard = mainBoard;
		this.cpu = cpu;
		this.hd = hd;
		this.powerSupplier = powerSupplier;
		this.grahicsCard = grahicsCard;
		this.mouse = mouse;
		this.computerCase = computerCase;
		this.mousePad = mousePad;
	}


	public String getMainBoard() {
		return mainBoard;
	}


	public void setMainBoard(String mainBoard) {
		this.mainBoard = mainBoard;
	}


	public String getCpu() {
		return cpu;
	}


	public void setCpu(String cpu) {
		this.cpu = cpu;
	}


	public String getHd() {
		return hd;
	}


	public void setHd(String hd) {
		this.hd = hd;
	}


	public String getPowerSupplier() {
		return powerSupplier;
	}


	public void setPowerSupplier(String powerSupplier) {
		this.powerSupplier = powerSupplier;
	}


	public String getGrahicsCard() {
		return grahicsCard;
	}


	public void setGrahicsCard(String grahicsCard) {
		this.grahicsCard = grahicsCard;
	}


	public String getMouse() {
		return mouse;
	}


	public void setMouse(String mouse) {
		this.mouse = mouse;
	}


	public String getComputerCase() {
		return computerCase;
	}


	public void setComputerCase(String computerCase) {
		this.computerCase = computerCase;
	}


	public String getMousePad() {
		return mousePad;
	}


	public void setMousePad(String mousePad) {
		this.mousePad = mousePad;
	}


	public String getOther() {
		return other;
	}


	public void setOther(String other) {
		this.other = other;
	}


	@Override
	public String toString() {
		return "Computer [mainBoard=" + mainBoard + ", cpu=" + cpu + ", hd="
				+ hd + ", powerSupplier=" + powerSupplier + ", grahicsCard="
				+ grahicsCard + ", mouse=" + mouse + ", computerCase="
				+ computerCase + ", mousePad=" + mousePad + ", other=" + other
				+ "]";
	}
	
	
	
}
