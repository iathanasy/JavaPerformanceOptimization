package com.cd.chapter02.template;

/**
 * @description: 悍马车是每个越野者的最爱，其中H1最接近军用系列
 * @author: Mr.Wang
 * @create: 2019-04-01 21:28
 **/
public class HummerH1Model extends HummerModel {
	private boolean alarmFlag = true;// 是否要响喇叭

	@Override
	public void start() {
		System.out.println("悍马H1发动...");
	}

	@Override
	public void stop() {
		System.out.println("悍马H1停车...");
	}

	@Override
	public void alarm() {
		System.out.println("悍马H1鸣笛...");
	}

	@Override
	public void engineBoom() {
		System.out.println("悍马H1引擎声音是这样在...");
	}

	@Override
	protected boolean isAlarm() {
		return this.alarmFlag;
	}

	// 要不要响喇叭，是由客户来决定的
	public void setAlarm(boolean isAlarm) {
		this.alarmFlag = isAlarm;
	}
}
