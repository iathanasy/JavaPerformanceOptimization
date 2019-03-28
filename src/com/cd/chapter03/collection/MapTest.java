package com.cd.chapter03.collection;

import java.util.*;

import com.cd.chapter03.collection.MapTest.Student;
/**
 * @see HashMap 实现Map接口  通过hash算法可以快速的进行put() 和get()操作
 * @see LinkedHashMap 继承HashMap 在内部存放了一个链表，用以存放元素的顺序. 提供两种顺序：1.元素插入时顺序   2.元素最近访问的顺序 accessOrder = true
 * @see TreeMap 实现SortedMap接口  可以对元素进行排序 性能略低于HashMap. 内部基于红黑树实现 
 * @see Hashtable 实现Map接口 与HashMap差异不大。  Hashtable做了同步，线程安全、不允许key和value出现null值、而HashMap可以。
 * 			在内部算法上  key的hash算法和hash值到内存索引的映射算法不同
 * @author cd
 * @date 2019年3月28日 下午3:25:26
 * @desc
 */
public class MapTest {

	public static void main(String[] args) {
		String[] arr = {"aa","bb","cc","dd"};
		LinkedHashMap<String, Object> linked = new LinkedHashMap<String, Object>(16,0.75f,true);
		HashMap<String, Object> map = new HashMap<String, Object>();
		Hashtable<String, Object> tab = new Hashtable<String, Object>();
		
		for (int i = 0; i < arr.length; i++) {
			map.put(String.valueOf(i), arr[i]);
			tab.put(String.valueOf(i), arr[i]);
			linked.put(String.valueOf(i), arr[i]);
			
		}
		
		System.out.println("--------------HashMap--------------------");
		map.put(null, "123");
		map.put("null", null);//不会出错
		for(Iterator<String> it = map.keySet().iterator(); it.hasNext();){
			String key = it.next();
			System.out.println(key + "->" + map.get(key));
		}
		
		
		/*System.out.println("--------------------LinkedHashMap-------------------");
		for(Iterator<String> it = linked.keySet().iterator(); it.hasNext();){
			String key = it.next();
			*//**
			 * new LinkedHashMap<String, Object>(16,0.75f,true);
			 * 指定设置true 按照访问时间排序  map.get会去修改集合中的结构，所以会抛出异常
			 * java.util.ConcurrentModificationException 
			 * 不要在迭代器模式中修改被迭代的集合 会抛出上面的异常  这个特性适用于所有集合类  
			 * 包括HashMap、Vector、ArrayList
			 *//*
			System.out.println(key + "->" + linked.get(key)); 
		}*/
		
		
		System.out.println("---------------Hashtable------------------");
		//tab.put("123", null);
		//tab.put(null, "123");//抛出异常  java.lang.NullPointerException
		for(Iterator<String> it = tab.keySet().iterator(); it.hasNext();){
			String key = it.next();
			System.out.println(key + "->" + tab.get(key));
		}
		
		System.out.println("-------------------TreeMap--------------------------");
		TreeMap tree = new TreeMap();
		Student s1 = new Student("a",70);
		Student s2 = new Student("b",85);
		Student s3 = new Student("c",63);
		Student s4 = new Student("d",90);
		
		tree.put(s1, new StudentDetailInfo(s1));
		tree.put(s2, new StudentDetailInfo(s2));
		tree.put(s4, new StudentDetailInfo(s4));
		tree.put(s3, new StudentDetailInfo(s3));
		
		//筛选出c 和 d之间的所有学生
		Map map1 = ((TreeMap)tree).subMap(s3, s4);
		for (Iterator iterator = map1.keySet().iterator(); iterator.hasNext();) {
			Student key = (Student) iterator.next();
			System.out.println(key + "->" + map1.get(key)); 
			
		}
		System.out.println("suMap end");
		
		//筛选出成绩低于b的学生
		map1 = ((TreeMap)tree).headMap(s2);
		for (Iterator iterator = map1.keySet().iterator(); iterator.hasNext();) {
			Student key = (Student) iterator.next();
			System.out.println(key + "->" + map1.get(key)); 
			
		}
		System.out.println("headMap end");
		
		//筛选出大于b的学生
		map1 = ((TreeMap)tree).tailMap(s2);
		for (Iterator iterator = map1.keySet().iterator(); iterator.hasNext();) {
			Student key = (Student) iterator.next();
			System.out.println(key + "->" + map1.get(key)); 
			
		}
		System.out.println("tailMap end");
	}
	
	
	
	/**
	 * 学生信息
	 * @author cd
	 * @date 2019年3月28日 下午3:39:28
	 * @desc
	 */
	public static class Student implements Comparable<Student>{
		String name;
		int score;
		
		public Student(String name, int s){
			this.name = name;
			this.score = s;
		}
		
		/**
		 * 这是必须实现的 告诉TreeMap如何进行排序
		 */
		@Override
		public int compareTo(Student o) {
			if(o.score < this.score)
				return 1;
			else if(o.score > this.score)
				return -1;
			return 0;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", score=" + score + "]";
		}
	}
	
	/**
	 * 学生详细信息
	 * @author cd
	 * @date 2019年3月28日 下午3:40:11
	 * @desc
	 */
	public static class StudentDetailInfo{
		Student s;
		
		public StudentDetailInfo(Student s){
			this.s = s;
		}
		
		@Override
		public String toString() {
			
			return s.name + "'s detail";
		}
	}
}
