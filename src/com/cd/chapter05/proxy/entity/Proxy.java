package com.cd.chapter05.proxy.entity;

import java.io.Serializable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Proxy implements Delayed, Serializable {
	private static final long serialVersionUID = -7583883432417635332L;
	private long timeInterval;// 任务间隔时间,单位ms
	private String ip;
	private int port;
	private String type;
	private String location;
	private String anonymous;
	private long responseTime;// 代理测试响应时间

	private boolean availableFlag;
	private boolean anonymousFlag;
	private long lastSuccessfulTime;// 最近一次请求成功时间
	private long successfulTotalTime;// 请求成功总耗时
	private int failureTimes;// 请求失败次数
	private int successfulTimes;// 请求成功次数
	private double successfulAverageTime;// 成功请求平均耗时

	public Proxy() {

	}

	public Proxy(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public Proxy(String ip, int port, long timeInterval) {
		this.ip = ip;
		this.port = port;
		this.timeInterval = timeInterval;
		this.timeInterval = TimeUnit.NANOSECONDS.convert(timeInterval,
				TimeUnit.MILLISECONDS) + System.nanoTime();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(boolean availableFlag) {
		this.availableFlag = availableFlag;
	}

	public boolean isAnonymousFlag() {
		return anonymousFlag;
	}

	public void setAnonymousFlag(boolean anonymousFlag) {
		this.anonymousFlag = anonymousFlag;
	}

	public long getTimeInterval() {
		return timeInterval;
	}

	public long getLastSuccessfulTime() {
		return lastSuccessfulTime;
	}

	public void setLastSuccessfulTime(long lastSuccessfulTime) {
		this.lastSuccessfulTime = lastSuccessfulTime;
	}

	public long getSuccessfulTotalTime() {
		return successfulTotalTime;
	}

	public void setSuccessfulTotalTime(long successfulTotalTime) {
		this.successfulTotalTime = successfulTotalTime;
	}

	public void setTimeInterval(long timeInterval) {
		this.timeInterval = TimeUnit.NANOSECONDS.convert(timeInterval,
				TimeUnit.MILLISECONDS) + System.nanoTime();
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(timeInterval - System.nanoTime(),
				TimeUnit.NANOSECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		Proxy element = (Proxy) o;
		if (successfulAverageTime == 0.0d
				|| element.successfulAverageTime == 0.0d) {
			return 0;
		}
		return successfulAverageTime > element.successfulAverageTime ? 1
				: (successfulAverageTime < element.successfulAverageTime ? -1
						: 0);
	}

	public int getFailureTimes() {
		return failureTimes;
	}

	public void setFailureTimes(int failureTimes) {
		this.failureTimes = failureTimes;
	}

	public int getSuccessfulTimes() {
		return successfulTimes;
	}

	public void setSuccessfulTimes(int successfulTimes) {
		this.successfulTimes = successfulTimes;
	}

	public double getSuccessfulAverageTime() {
		return successfulAverageTime;
	}

	public void setSuccessfulAverageTime(double successfulAverageTime) {
		this.successfulAverageTime = successfulAverageTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}

	@Override
	public String toString() {
		return "Proxy [timeInterval=" + timeInterval + ", ip=" + ip + ", port="
				+ port + ", type=" + type + ", location=" + location
				+ ", anonymous=" + anonymous + ", responseTime=" + responseTime
				+ ", availableFlag=" + availableFlag + ", anonymousFlag="
				+ anonymousFlag + ", lastSuccessfulTime=" + lastSuccessfulTime
				+ ", successfulTotalTime=" + successfulTotalTime
				+ ", failureTimes=" + failureTimes + ", successfulTimes="
				+ successfulTimes + ", successfulAverageTime="
				+ successfulAverageTime + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Proxy proxy = (Proxy) o;

		if (port != proxy.port)
			return false;
		return ip.equals(proxy.ip);

	}

	@Override
	public int hashCode() {
		int result = ip.hashCode();
		result = 31 * result + port;
		return result;
	}

	public String getProxyStr() {
		return ip + ":" + port;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
}
