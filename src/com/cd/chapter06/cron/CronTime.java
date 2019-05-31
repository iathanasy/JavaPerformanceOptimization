package com.cd.chapter06.cron;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

/**
 * 
 * @author cd
 * @date 2019年5月30日 下午6:08:18
 * @desc 定时任务最近几次运行时间
 * 
 * 依赖
<!-- https://mvnrepository.com/artifact/org.quartz-scheduler/quartz -->
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>2.3.0</version>
</dependency>
 */
public class CronTime {

	public static void main(String[] args) {
		String cron = "0 0 * * * ? *";
		System.out.println(getRecentTriggerTime(cron));
	}
	
	/**
	 * 最近5次运行时间
	 * @param cron 表达式
	 * @param count 次数
	 * @return
	 */
	public static List<String> getRecentTriggerTime(String cron, Integer count) {
	    List<String> list = new ArrayList<String>();
	    try {
	        CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
	        cronTriggerImpl.setCronExpression(cron);
	        // 这个是重点，一行代码搞定
	        List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, count);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        for (Date date : dates) {
	            list.add(dateFormat.format(date));
	        }
	         
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	/**
	 * 最近5次运行时间
	 * @param cron 表达式
	 * @return
	 */
	public static List<String> getRecentTriggerTime(String cron) {
	    List<String> list = new ArrayList<String>();
	    try {
	        CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
	        cronTriggerImpl.setCronExpression(cron);
	        Calendar calendar = Calendar.getInstance();
	        Date now = calendar.getTime();
	        //calendar.add(Calendar.YEAR, 2);//把统计的区间段设置为从现在到2年后的今天（主要是为了方法通用考虑，如那些1个月跑一次的任务，如果时间段设置的较短就不足20条)
	        calendar.add(Calendar.MONDAY, 2);
	        // 这个是重点，一行代码搞定
	        List<Date> dates = TriggerUtils.computeFireTimesBetween(cronTriggerImpl, null, now, calendar.getTime());
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        for (Date date : dates) {
	            list.add(dateFormat.format(date));
	        }
	         
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
