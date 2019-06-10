package com.cd.chapter01;
/**
 * 按位操作符
 * @author cd
 * @date 2019年6月10日 上午10:31:50
 * @desc
 */
public class BitwiseTest {

	public static void main(String[] args) {
		/**
		 *  例1 5&4
        5的二进制是  0000 0000 0000 0101

        4的二进制是  0000 0000 0000 0100

                         则结果是        0000 0000 0000 0100  转为十进制是4。
		 */
		System.out.println(5&4);//与  相同位数都是1，则该位结果是1，否则是0.
		
		System.out.println(5|4); //或   相同位数有一个是1，则该位结果是1，否则是0
		
		//0000 0000 0000 0001 转为十进制是1 
		System.out.println(5^4); //异或  相同位数只有一个是1，则该位结果是1，否则是0
		
		//1111 1111 1111 1010  转为十进制是 -6。
		System.out.println(~5); //非 也称为取反操作符, 二进制的正负是从高位看，最高位如果1则是负数，如果是0则是正数。
		
	}
}
