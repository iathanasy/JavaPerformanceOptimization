package com.cd.chapter04.master;

import java.util.Map;
import java.util.Set;
/**
 * Master -> 多个Worker
 *         <-
 * @author cd
 * @date 2019年4月1日 下午5:05:54
 * @desc
 */
public class Main {

	public static void main(String[] args) {
		/**
		 * 通过Master创建了5个Worker工作进程和Worker工作实例PlusWorker
		 * 在提交了100个子任务后，便开始了子任务的计算，这些子任务，由生成的5个Worker进程共同完成
		 * Master并不等待所有Worker执行完毕，就开始访问子结果集，进行最终计算，
		 * 直到子结果集中所有的数据都被处理，并且5个活跃的Worker进程全部终止，才会给出最终计算结果
		 */
		Master m = new Master(new PlusWorker(), 5);//固定5个Worker,并指定Worker
		for (int i = 0; i < 100; i++) {
			m.submit(i); //提交100个子任务
		}
		m.execute();//开始计算
		int re = 0;//最终计算结果保存
		Map<String, Object> resultMap = m.getResultMap();
		System.out.println(resultMap);
		while(resultMap.size() > 0 || !m.isComplete()){
			//不需要等待所有的Worker都执行完。
			Set<String> keys = resultMap.keySet();//开始计算最终结果
			String key = null;
			for (String k : keys) {
				key = k;
				break;
			}
			Integer i = null;
			if(key != null)
				i = (Integer) resultMap.get(key);
			if(i != null)
				re += i;//最终结果
			if(key != null)
				resultMap.remove(key);//移除已经被计算的结果
		}
		
		System.out.println(re);
	}
}
