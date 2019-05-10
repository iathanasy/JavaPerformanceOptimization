package com.cd.chapter03.string;

/**
 * String 对象的3个基本特征： 不变性、针对常量池的优化、类final的定义
 * 
 * String对象的内部结构： char数组、offset 偏移、count 长度
 * 
 * @author cd
 * @date 2019年3月27日 下午2:43:27
 * @desc
 */

public class StringTest {

	public static void main(String[] args) {
		/**
		 * String 内存分配方式
		 * 
		 * 变量 内存空间 常量池
		 * 
		 * str1 str实例 str2 --> -> 123 ------------------------ str3 --> str实例
		 */
		String str1 = "123";
		String str2 = "123";
		String str3 = new String("123");
		System.out.println(str1 == str2); // true
		System.out.println(str1 == str3); // false
		System.out.println(str1 == str2.intern()); // true
		/**
		 * 当两个对象拥有相同的值时，他们只引用常量池中的同一个拷贝 str1 和str2引用了相同的地址 而str3重新开辟了内存空间
		 * str3的常量池中的位置和str1是一样的 intern()方法返回了String对象在常量池中的引用
		 */

		// 静态字符串的连接+操作 编译时会进行彻底的优化
		String result = "String" + "and" + "String" + "append"; // 静态字符串速度快
		StringBuilder builder = new StringBuilder();
		builder.append("String");
		builder.append("and");
		builder.append("String");
		builder.append("append");

		System.out.println(result);
		System.out.println(builder);

		String s1 = "String", s2 = "and", s3 = "String", s4 = "append";
		String s = s1 + s2 + s3 + s4; // 和StringBuilder同等
		System.out.println(s);

		long begin = System.currentTimeMillis();

		// 超大String对象
		for (int i = 0; i < 10000; i++) {
			// 反编译回来的代码 每次都会new一个StringBuilder 所以性能比较低
			// s = (new StringBuilder(String.indexOf(s))).append(i).toString();
			s = s + i;
		}
		System.out.println("+: " + (System.currentTimeMillis() - begin));

		begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			result = result.concat(String.valueOf(i));
		}
		System.out.println("concat: " + (System.currentTimeMillis() - begin));

		begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			// 而这个值new 了一个StringBuilder
			builder.append(i);
		}
		System.out.println("append: " + (System.currentTimeMillis() - begin));
		/**
		 * +: 296 concat: 110 append: 0
		 */

		/**
		 * 多线程下可以使用 StringBuffer 线程安全 不考虑线程安全的使用StringBuilder 效率更高 默认容量参数是16字节
		 */
		// StringBuffer sb = new StringBuffer(); //StringBuffer: 11
		StringBuilder sb = new StringBuilder();// StringBuilder: 10
		begin = System.currentTimeMillis();
		for (int i = 0; i < 500000; i++) {
			builder.append(i);
		}
		System.out.println("StringBuilder: "
				+ (System.currentTimeMillis() - begin));

	}

}
