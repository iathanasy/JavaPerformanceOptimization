package com.cd.chapter03.collection;
import java.util.*;
/**
 * Set集合中的元素是不能重复的
 * @see HashSet 对应HashMap  、基于hash的快速元素插入，元素间无顺序
 * @see LinkedHashSet LinkedHashMap \ 基于hash的快速元素插入、同时维护者元素插入集合时的先后顺序。遍历集合时，按照先进先出的顺序排序
 * @see TreeSet  TreeMap 基于红黑树实现，有着高效的基于元素key的排序算法
 * @author cd
 * @date 2019年3月28日 下午4:20:16
 * @desc
 */
public class SetTest {
	public static void main(String[] args) {
		Integer[] arr = {100,10,4,33,9};
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedHashSet<Integer> linkedSet = new LinkedHashSet<Integer>();
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
			linkedSet.add(arr[i]);
			treeSet.add(arr[i]);
		}

		System.out.println("-----------HashSet------------");
		System.out.print("HashSet: ");
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Integer key = (Integer) iterator.next();
			System.out.print(key + ",");
		}
		System.out.println("\n");
		
		System.out.println("-----------LinkedHashSet------------");
		System.out.print("LinkedHashSet: ");
		for (Iterator iterator = linkedSet.iterator(); iterator.hasNext();) {
			Integer key = (Integer) iterator.next();
			System.out.print(key + ",");
		}
		System.out.println("\n");
		System.out.println("-----------TreeSet------------");
		System.out.print("TreeSet: ");
		for (Iterator iterator = treeSet.iterator(); iterator.hasNext();) {
			Integer key = (Integer) iterator.next();
			System.out.print(key + ",");
		}
		System.out.println("\n");
		
		
		/**
		 * 测试数据如下：
		 * HashSet: 33,100,4,9,10
		 * LinkedHashSet: 100,10,4,33,9
		 * TreeSet: 4,9,10,33,100
		 * 
		 * HashSet 毫无规律可言
		 * LinkedHashSet 输出顺序和输入顺序完全一致
		 * TreeSet 从小到大排序
		 */
		
	}
}
