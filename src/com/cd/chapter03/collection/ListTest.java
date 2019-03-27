package com.cd.chapter03.collection;
import java.util.*;
/**
 * List����
 * @author cd
 * @date 2019��3��27�� ����5:47:17
 * @desc 
 */
public class ListTest {

	public static void main(String[] args) {
		/**
		 * arr: 101
		 * linked: 6
		 *  add()
		 * ArrayList ����ʵ�� -> Ҫ������������  Խ��ǰ��������ʱ��Խ�� 
		 * LinkedList ����ʵ�� -> ������λ�ò������ݶ���һ����
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

		// ArrayList �����б�
		begin = System.currentTimeMillis();
		String tmp;
		for (Object s : arr) { 
			//�������� foreach ����� iterator
			//ֻ���ڸ�ֵ�� ʱ�����һ��
			//tmp = iterator.next();
			//String s1 = tmp;//����ĸ�ֵ ����������
			tmp = (String) s; //foreach  foreach arr: 6
		}
		System.out.println(" foreach arr: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		for (Iterator<String> iterator = arr.iterator(); iterator.hasNext();) {
			tmp = iterator.next(); //������  Iterator arr: 4
		}
		System.out.println(" Iterator arr: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		int size = arr.size();
		for (int i = 0; i < size; i++) {
			tmp = arr.get(i); //forѭ�� ʹ���������  for arr: 2
		}
		System.out.println(" for arr: "+ (System.currentTimeMillis() - begin));
		
		
		// LinkedList �����б�
		begin = System.currentTimeMillis();
		String tmp1;
		for (Object s : linked) {
			tmp1 = (String) s; //foreach  foreach linked: 5
		}
		System.out.println(" foreach linked: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		for (Iterator<String> iterator = linked.iterator(); iterator.hasNext();) {
			tmp1 = iterator.next(); //������  Iterator linked: 2
		}
		System.out.println(" Iterator linked: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		int size1 = linked.size();
		for (int i = 0; i < size1; i++) {
			tmp1 = linked.get(i); //forѭ�� ʹ���������   for linked: 3670
		}
		System.out.println(" for linked: "+ (System.currentTimeMillis() - begin));
		
		//ArrayListɾ��
		begin = System.currentTimeMillis();
		/*while(arr.size() > 0){
			arr.remove(0);//ͷ��ɾ�� arr rm: 180
		}*/
		/*while(arr.size() > 0){
			arr.remove(arr.size() >> 1);//�м�ɾ�� arr rm: 98
		}*/
		while(arr.size() > 0){
			arr.remove(arr.size() - 1);//β��ɾ�� arr rm: 3
		}
		
		System.out.println("arr rm: "+ (System.currentTimeMillis() - begin));
		
		//LinkedListɾ��
		begin = System.currentTimeMillis();
		/*while(arr.size() > 0){
			arr.remove(0);//ͷ��ɾ�� linked rm: 0
		}*/
		/*while(arr.size() > 0){
			arr.remove(arr.size() >> 1);//�м�ɾ�� linked rm: 1
		}*/
		while(arr.size() > 0){
			arr.remove(arr.size() - 1);//β��ɾ�� linked rm: 0
		}
		
		System.out.println("linked rm: "+ (System.currentTimeMillis() - begin));
		
		
		
		/***
		 * ArrayList��������Ǻܿ��  ��ɾ�Ļᵼ������ ����
		 * LinkedList ��������ʵ�� ����������ܺܲ� ��ɾ�ĺܿ�
		 * 
		 * ��ѯ������  LinkedList forѭ���Ǻ����� ���ܺܲ�
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
