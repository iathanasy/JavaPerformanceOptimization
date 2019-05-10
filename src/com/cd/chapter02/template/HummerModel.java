package com.cd.chapter02.template;

/**
 * @description: Hummer Model是悍马车辆模型的意思，不是悍马美女车模
 * @author: Mr.Wang
 * @create: 2019-04-01 21:26
 **/
public abstract class HummerModel {
	/*
	 * 首先，这个模型要能够被发动起来，别管是手摇发动，还是电力发动，反正 是要能够发动起来，那这个实现要在实现类里了
	 */
	protected abstract void start();

	// 能发动，那还要能停下来，那才是真本事
	protected abstract void stop();

	// 喇叭会出声音，是滴滴叫，还是哔哔叫
	protected abstract void alarm();

	// 引擎会轰隆隆的响，不响那是假的
	protected abstract void engineBoom();

	// 那模型应该会跑吧，别管是人推的，还是电力驱动，总之要会跑
	final public void run() {
		// 先发动汽车
		this.start();

		// 引擎开始轰鸣
		this.engineBoom();

		// 然后就开始跑了，跑的过程中遇到一条狗挡路，就按喇叭
		if (this.isAlarm()) {
			this.alarm();
		}
		// 到达目的地就停车
		this.stop();
	}

	// 钩子方法，默认喇叭是会响的
	// 钩子方法模式是由抽象类来实现的，子类可以重写的，H2 型号的悍马是不会叫的，喇叭是个摆设，
	protected boolean isAlarm() {
		return true;
	}
}
