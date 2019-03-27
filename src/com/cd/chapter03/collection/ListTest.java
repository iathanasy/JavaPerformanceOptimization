package com.cd.chapter03.collection;
import java.util.*;
/**
 * List测试
 * @author cd
 * @date 2019年3月27日 下午5:47:17
 * @desc 
 */
public class ListTest {

	public static void main(String[] args) {
		/**
		 * arr: 101
		 * linked: 6
		 *  add()
		 * ArrayList 数组实现 -> 要进行数组重组  越往前插入重组时间越长 
		 * LinkedList 链表实现 -> 在任意位置插入数据都是一样的
		 */
		long begin = System.currentTimeMillis();
		ArrayList<String> arr = new ArrayList();
		for (int i = 0; i < 50000; i++) {
			arr.add(0, i+"");
		}
		
		System.out.println("arr: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		LinkedList<String> linked = new LinkedList<String>();
		for (int i = 0; i < 50000; i++) {
			linked.add(0, i+"");
		}
		System.out.println("linked: "+ (System.currentTimeMillis() - begin));

		// ArrayList 遍历列表
		begin = System.currentTimeMillis();
		String tmp;
		for (Object s : arr) { 
			//反编译会把 foreach 编译成 iterator
			//只是在赋值的 时候多了一步
			//tmp = iterator.next();
			//String s1 = tmp;//多余的赋值 降低了性能
			tmp = (String) s; //foreach  foreach arr: 6
		}
		System.out.println(" foreach arr: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		for (Iterator<String> iterator = arr.iterator(); iterator.hasNext();) {
			tmp = iterator.next(); //迭代器  Iterator arr: 4
		}
		System.out.println(" Iterator arr: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		int size = arr.size();
		for (int i = 0; i < size; i++) {
			tmp = arr.get(i); //for循环 使用随机访问  for arr: 2
		}
		System.out.println(" for arr: "+ (System.currentTimeMillis() - begin));
		
		
		// LinkedList 遍历列表
		begin = System.currentTimeMillis();
		String tmp1;
		for (Object s : linked) {
			tmp1 = (String) s; //foreach  foreach linked: 5
		}
		System.out.println(" foreach linked: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		for (Iterator<String> iterator = linked.iterator(); iterator.hasNext();) {
			tmp1 = iterator.next(); //迭代器  Iterator linked: 2
		}
		System.out.println(" Iterator linked: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		int size1 = linked.size();
		for (int i = 0; i < size1; i++) {
			tmp1 = linked.get(i); //for循环 使用随机访问   for linked: 3670
		}
		System.out.println(" for linked: "+ (System.currentTimeMillis() - begin));
		
		//ArrayList删除
		begin = System.currentTimeMillis();
		/*while(arr.size() > 0){
			arr.remove(0);//头部删除 arr rm: 180
		}*/
		/*while(arr.size() > 0){
			arr.remove(arr.size() >> 1);//中间删除 arr rm: 98
		}*/
		while(arr.size() > 0){
			arr.remove(arr.size() - 1);//尾部删除 arr rm: 3
		}
		
		System.out.println("arr rm: "+ (System.currentTimeMillis() - begin));
		
		//LinkedList删除
		begin = System.currentTimeMillis();
		/*while(arr.size() > 0){
			arr.remove(0);//头部删除 linked rm: 0
		}*/
		/*while(arr.size() > 0){
			arr.remove(arr.size() >> 1);//中间删除 linked rm: 1
		}*/
		while(arr.size() > 0){
			arr.remove(arr.size() - 1);//尾部删除 linked rm: 0
		}
		
		System.out.println("linked rm: "+ (System.currentTimeMillis() - begin));
		
		
		
		/***
		 * ArrayList随机访问是很快的  增删改会导致数组 重组
		 * LinkedList 基于链表实现 随机访问性能很差 增删改很快
		 * 
		 * 查询的数据  LinkedList for循环是很慢的 性能很差
		 *   foreach arr: 6
			 Iterator arr: 4
			 for arr: 2
			 foreach linked: 5
			 Iterator linked: 2
			 for linked: 3670
		 * 
		 * 
		 */
	}
}
