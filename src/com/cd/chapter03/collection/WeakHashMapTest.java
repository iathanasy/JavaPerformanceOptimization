package com.cd.chapter03.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 
 * @author cd
 * @date 2019年3月29日 下午5:25:12
 * @desc WeakHashMap 测试
 */
public class WeakHashMapTest {

	public static void main(String[] args) {
		Map map;
		// 配置 VM -Xmx5M
		map = new WeakHashMap();// 使用WeakHashMap 正常运行
		List l = new ArrayList();
		for (int i = 0; i < 10000; i++) {
			Integer ii = new Integer(i);
			// l.add(ii); //对Key 进行强引用 退化为普通HashMap 最终抛出异常
			// java.lang.OutOfMemoryError: Java heap space
			map.put(ii, new byte[i]);
		}

		// 抛出异常 java.lang.OutOfMemoryError: Java heap space
		map = new HashMap(); // 使用HashMap();
		for (int i = 0; i < 10000; i++) {
			Integer ii = new Integer(i);
			map.put(ii, new byte[i]);
		}

		/**
		 * 可见 WeakHashMap会在系统内存紧张的时候使用弱引用 ，自动释放掉使用持有弱引用的内存数据。
		 * 如果需要在系统中通过WeakHashMap自动清理数据，就尽量不要在系统的其它地方强引用WeakHashMap的key,
		 * 否则key就不会被回收，无法正常释放它们所占用的表项
		 */
	}
}
