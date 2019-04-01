package com.cd.chapter03.skill;

public class Demo01 {
	
	public static int at = 0;

	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		/**
		 * 局部变量访问速度远高于类成员变量
		 * 使用局部变量 a seep: 1
		 */
		int a = 0;
		for (int i = 0; i < 100000000; i++) {
			a++;
		}
		
		System.out.println("a seep: "+ (System.currentTimeMillis() - begin));
		
		/**
		 * 类中静态变量 at seep: 7
		 */
		begin = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			at++;
		}
		
		System.out.println("at seep: "+ (System.currentTimeMillis() - begin));
		
		/////////////////////////////////////////////////////////////
		/**
		 * 位运算替代乘除法
		 */
		long b = 100;
		for (int i = 0; i < 100000000; i++) {
			b*=2;
			b/=2;
		}
		System.out.println(b);
		
		for (int i = 0; i < 100000000; i++) {
			b<<=1; //剩以2
			b>>=1; //除以2
		}
		
		///////////////////////////////////////////////////////////////
		/**
		 * 替代switch
		 */
		
		int re = 0;
		for (int i = 0; i < 100000000; i++) {
			re = switchInt(i);
		}
		
		System.out.println(re);
		
		int[] sw = new int[]{0,3,6,7,8,10,16,18,44};//替代switch逻辑
		
		for (int i = 0; i < 100000000; i++) {
			re = arrayInt(sw, i);
		}
		
		///////////////////////////////////////////////////
		/**
		 * 一维数组代替二维数组
		 */
		//一维数组
		int[] array = new int[1000000];
		int ret = 0;
		int size = array.length;
		for(int k = 0;k < 100; k++)
		for (int i = 0; i < size; i++) {
			array[i] = i;
			ret = array[i];
		}
		System.out.println(ret);
		//二维数组
		int[][] arr2 = new int[1000][1000]; 
		int ret2 = 0;
		int size2 = arr2.length;
		int size22 = arr2[0].length;
		for(int k = 0;k < 100; k++)
		for (int i = 0; i < size2; i++) {
			for (int j = 0; j < size22; j++) {
				arr2[i][j] = i;
				ret2 = arr2[i][j];
			}
			
		}
		System.out.println(ret2);
		
		/////////////////////////////////////////////////
		/**
		 * 提取表达式
		 */
		double d1 = Math.random();
		double a1 = Math.random();
		double b1 = Math.random();
		double e1 = Math.random();
		double t,x,y;
		for (int i = 0; i < 10000000; i++) {//提取表达式
			//有重复的
			//y = d1*a1*b1/3*4*a;
			//x = e1*a1*b1/3*4*a;
			
			//提取重复的
			t = a1*b1/3*4*a;
			y = d1 * t;
			x = e1 * t;
		}
		
		//如果某循环体中需要执行一个耗时业务，而循环体内其结果总是唯一的，也应该提取到循环体外
		for (int i = 0; i < 10; i++) {
			//x[i] = Math.PI * Math.sin(1.0) * i;//该循环体属于业务的逻辑
		}
		//提取复杂、固定结果的业务逻辑处理到循环体外
		double dd = Math.PI * Math.sin(1.0);
		for (int i = 0; i < 10; i++) {
			//x[i] = dd * i;//该循环体属于业务的逻辑
		}
		
		////////////////////////////////////////////////////
		/**
		 * 展开循环
		 */
		int[] arrays = new int[9999999];
		int s = arrays.length;
		for (int i = 0; i < s; i++) {
			arrays[i] = i;
		}
		//展开循环，一个循环体完成原来3个循环的工作
		for (int i = 0; i < s; i += 3) {
			arrays[i] = i;
			arrays[i+1] = i+1;
			arrays[i+2] = i+2;
		}
		
		//////////////////////////////////////////////////////
		/**
		 * 使用arrayCopy()
		 */
		int sizz= 1000000;
		int[] arrs = new int[sizz];
		int[] arrdst = new int[sizz];
		for (int i = 0; i < sizz; i++) { //构造两个数组
			arrs[i] = i;
		}
		
		begin = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			System.arraycopy(arrs, 0, arrdst, 0, sizz); //进行复制 native函数性能要优于普通函数
		}
		System.out.println("arraycopy: " +(System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		
		//自己实现复制
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < size; j++) {
				arrdst[j] = arrs[j]; //进行复制
			}
		}
		System.out.println("copy: " +(System.currentTimeMillis() - begin));
	}
	
	protected static int switchInt(int z){
		int i = z % 10 + 1; //根据操作数不同，返回不同的值
		switch(i){
			case 1:return 3;
			case 2:return 6;
			case 3:return 7;
			case 4:return 8;
			case 5:return 10;
			case 6:return 16;
			case 7:return 18;
			case 8:return 44;
			default: return -1;
		}
	}
	
	protected static int arrayInt(int[] sw, int z){
		
		int i = z % 10 + 1;
		if(i>7 || i<1){
			return -1;
		}else{
			return sw[i];
		}
	}
}
