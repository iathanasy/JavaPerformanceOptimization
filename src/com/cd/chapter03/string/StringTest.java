package com.cd.chapter03.string;
/**
 * String �����3������������ �����ԡ���Գ����ص��Ż�����final�Ķ���
 * 
 * String������ڲ��ṹ�� char���顢offset ƫ�ơ�count ����
 * @author cd
 * @date 2019��3��27�� ����2:43:27
 * @desc
 */

public class StringTest {

	public static void main(String[] args) {
		/**
		 * String �ڴ���䷽ʽ
		 * 
		 * ����                �ڴ�ռ�                 ������
		 * 
		 * str1      strʵ��               
		 * str2  -->          ->    123
		 * ------------------------
		 * str3  -->  strʵ��                
		 */
		String str1 = "123";
		String str2 = "123";
		String str3 = new String("123");
		System.out.println(str1 == str2); //true
		System.out.println(str1 == str3); //false
		System.out.println(str1 == str2.intern()); //true
		/**
		 * ����������ӵ����ͬ��ֵʱ������ֻ���ó������е�ͬһ������
		 * str1 ��str2��������ͬ�ĵ�ַ ��str3���¿������ڴ�ռ�
		 * str3�ĳ������е�λ�ú�str1��һ����
		 * intern()����������String�����ڳ������е�����
		 */
		
		
		//��̬�ַ���������+���� ����ʱ����г��׵��Ż�
		String result = "String" + "and" + "String" + "append"; //��̬�ַ����ٶȿ�
		StringBuilder builder = new StringBuilder();
		builder.append("String");
		builder.append("and");
		builder.append("String");
		builder.append("append");
		
		System.out.println(result);
		System.out.println(builder);
		
		String s1 = "String",s2 = "and", s3 = "String", s4 = "append";
		String s = s1 + s2 + s3 + s4; //��StringBuilderͬ��
		System.out.println(s);
		
		
		long begin = System.currentTimeMillis();
	
		//����String����
		for (int i = 0; i < 10000; i++) {
			//����������Ĵ��� ÿ�ζ���newһ��StringBuilder �������ܱȽϵ�
			//s = (new StringBuilder(String.indexOf(s))).append(i).toString();
			s = s + i;
		}
		System.out.println("+: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			result = result.concat(String.valueOf(i));
		}
		System.out.println("concat: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			//�����ֵnew ��һ��StringBuilder
			builder.append(i);
		}
		System.out.println("append: "+ (System.currentTimeMillis() - begin));
		/**
		 *  +: 296
			concat: 110
			append: 0
		 */
		
		/**
		 * ���߳��¿���ʹ�� StringBuffer �̰߳�ȫ
		 * �������̰߳�ȫ��ʹ��StringBuilder Ч�ʸ���
		 * Ĭ������������16�ֽ�
		 */
		//StringBuffer sb = new StringBuffer(); //StringBuffer: 11
		StringBuilder sb = new StringBuilder();//StringBuilder: 10
		begin = System.currentTimeMillis();
		for (int i = 0; i < 500000; i++) {
			builder.append(i);
		}
		System.out.println("StringBuilder: "+ (System.currentTimeMillis() - begin));
		
		
		
	}
	
}
