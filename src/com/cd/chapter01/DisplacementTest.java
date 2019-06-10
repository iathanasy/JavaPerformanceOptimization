package com.cd.chapter01;

/**
 * 位移操作
 * @author cd
 * @date 2019年6月10日 上午10:48:55
 * @desc
 * 
 * 移位操作符只可用来处理整数类型，左移位操作符（<<）能按照操作符右侧指定的位数将操作符左边的操作数向左移动（在低位补0），
 * “有符号”右移位操作符（>>）则按照操作符右侧指定的位数将操作符左边的操作数向右移。
 * “有符号”右移位操作符使用“符号扩展”；若符号位正，则在高位插入0；若符号位负。
 * 则在高位插入1。java中增加了一种“无符号”右移位操作符（>>>）,他使用“零扩展”；
 * 无论正负，都在高位插入0。
 */
public class DisplacementTest {

	public static void main(String[] args) {
		//5的二进制是   0000 0000 0000 0101
        //左移两位          0000 0000 0001 0100
		System.out.println(Integer.toBinaryString(5));
		System.out.println(Integer.toBinaryString(2));
		System.out.println(5 << 2);
		
		//右移两位         0000 0000 0000 0001
		System.out.println(5 >> 2);
		
		//-5的二进制是   1111 1111 1111 1011
        //右移两位             1111 1111 1111 1110 转十进制，例5反着来，先-1，然后取反
		System.out.println(-5 >> 2);
		
		System.out.println();
		System.getProperties().list(System.out);
		
		System.out.println("----------------");
		System.out.println(System.getProperty("java.library.path"));
	}
}

