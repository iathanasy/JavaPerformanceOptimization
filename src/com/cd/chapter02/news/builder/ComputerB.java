package com.cd.chapter02.news.builder;

/**
 * 
 * @author cd
 * @date 2019年6月4日 下午2:21:11
 * @desc 替代多参数构造函数的建造者模式，以组装电脑为例子
 * 
 * 
 * 代码注释非常详细，乍一看好像和建造者模式没有毛关系，但是我们细细一分析这个确实是一个建造者模式，
 * 我们看一看：产品是-->ComputerB,具体的建造者是一个静态内存类-->ComputerBuilder,
 * 但是没有抽象的建造者和指挥者「其实 ComputerB 充当的就是指挥者的角色」，我们说过建造者模式中指挥者和抽象建造者都不是必须的，
 * 所以这是一个典型的建造者模式
 */
public class ComputerB {

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
	
	//ComputerB 自己充当Director
	public ComputerB(ComputerBuilder builder){
		this.mainBoard = builder.mainBoard ;
        this.cpu = builder.cpu ;
        this.hd = builder.hd ;
        this.powerSupplier = builder.powerSupplier ;
        this.grahicsCard = builder.grahicsCard ;

        this.mouse = builder.mouse ;
        this.computerCase = builder.computerCase ;
        this.mousePad = builder.mousePad ;
        this.other = builder.other ;
	}
	
	/**
	 * 声明一个静态内存类 Builder
	 * @author cd
	 * @date 2019年6月4日 下午2:28:52
	 * @desc
	 */
	public static class ComputerBuilder{
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
		
		//这里声明一些必须要传的参数「规定这些参数是必须传的，这里只是举例，再实中可能参数都是可选的」
		public ComputerBuilder(String mainBoard, String cpu, String hd,
				String powerSupplier, String grahicsCard) {
			super();
			this.mainBoard = mainBoard;
			this.cpu = cpu;
			this.hd = hd;
			this.powerSupplier = powerSupplier;
			this.grahicsCard = grahicsCard;
		}

		public ComputerBuilder setMainBoard(String mainBoard) {
			this.mainBoard = mainBoard;
			return this;
		}

		public ComputerBuilder setCpu(String cpu) {
			this.cpu = cpu;
			return this;
		}

		public ComputerBuilder setHd(String hd) {
			this.hd = hd;
			return this;
		}

		public ComputerBuilder setPowerSupplier(String powerSupplier) {
			this.powerSupplier = powerSupplier;
			return this;
		}

		public ComputerBuilder setGrahicsCard(String grahicsCard) {
			this.grahicsCard = grahicsCard;
			return this;
		}

		public ComputerBuilder setMouse(String mouse) {
			this.mouse = mouse;
			return this;
		}

		public ComputerBuilder setComputerCase(String computerCase) {
			this.computerCase = computerCase;
			return this;
		}

		public ComputerBuilder setMousePad(String mousePad) {
			this.mousePad = mousePad;
			return this;
		}

		public ComputerBuilder setOther(String other) {
			this.other = other;
			return this;
		}
		
		
		//生成最终的产品
		public ComputerB build(){
			return new ComputerB(this);
		}
		
	}

	@Override
	public String toString() {
		return "ComputerB [mainBoard=" + mainBoard + ", cpu=" + cpu + ", hd="
				+ hd + ", powerSupplier=" + powerSupplier + ", grahicsCard="
				+ grahicsCard + ", mouse=" + mouse + ", computerCase="
				+ computerCase + ", mousePad=" + mousePad + ", other=" + other
				+ "]";
	}
	
	
}
