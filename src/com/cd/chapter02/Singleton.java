package com.cd.chapter02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author cd
 * @date 2019年3月25日 上午11:29:34
 * @desc 1.单例模式
 * 注意： 序列化和反序列化可能会破坏单例。
 */
public class Singleton {

	private Singleton(){
		//没有使用单例类还是被创建出来了 out 
		//Singleton is create
		//createString is Singleton
		//Singleton.createString();
		System.out.println("Singleton is create");//创建单例的过程可能会比较慢
	}
	
	private static Singleton instance = new Singleton();
	public static Singleton getInstance(){
		return instance;
	}
	
	public static void createString(){
		//这是模拟单例类扮演其它角色
		System.out.println("createString is Singleton");
	}
	
	public static void main(String[] args) {
		/*for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new Main());
			t.start();
		}*/
		try {
			SerSingleton.test();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class Main implements Runnable{
	long beginTime = System.currentTimeMillis();
	public void run() {
		for(int i = 0; i < 100000; i++){
			//Singleton.getInstance(); //spend: 6 ms
			//延迟加载 引用的同步关键字反而降低了系统的性能 有点得不偿失 需要对其改进
			//LazySingleton.getInstance();// spend: 29 ms
			StaticSingleton.getInstance();
		}
		System.out.println("spend: "+ (System.currentTimeMillis() - beginTime));
		
	}
}

/**
 * 
 * @author cd
 * @date 2019年3月25日 上午11:37:24
 * @desc 2.单例延迟加载  创建时耗大于第一种
 */
class LazySingleton{
	private LazySingleton(){
		System.out.println("LazySingleton is create");
	}
	
	private static LazySingleton instance = null;
	
	/**
	 * synchronized 必须使用同步,否则多线程环境下
	 * 当线程1正新建单例时，完成赋值操作前，线程2可能判断为instance = null ，
	 * 故线程2也将启动新建单例程序，而导致多个实例被创建。
	 * @return
	 */
	public static synchronized LazySingleton getInstance(){
		if(instance != null) instance = new LazySingleton();
		return instance;
	}
}

/**
 * 
 * @author cd
 * @date 2019年3月25日 下午2:07:42
 * @desc 3.单例模式 内部类来维护单例的实例 
 * 使用内部类 既可以做到延迟加载 也不必使用同步关键字 是一种比较完善的实现
 */
class StaticSingleton{
	private StaticSingleton(){
		System.out.println("StaticSingleton is create");
	}
	/**
	 * 内部类模式 
	 * 当StaticSingleton被加载时，其内部类不会被初始化，
	 * 故StaticSingleton 类被载入JVM时，不会初始化单例
	 * 调用getInstance() 方法被调用时，才会加载SingletonHolder,从而初始化instance
	 * 由于实例是在类加载时完成，故天生对多线程友好,不必使用 synchronized
	 */
	private static class SingletonHolder{
		private static StaticSingleton instance = new StaticSingleton();
	}
	
	public static StaticSingleton getInstance(){
		return SingletonHolder.instance;
	}
}

/**
 * 测试
 * @author cd
 * @date 2019年3月25日 下午2:44:35
 * @desc
 */
class SerSingleton implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String name;
	
	private SerSingleton(){
		System.out.println("SerSingleton is create");
		name = "SerSingleton";
	}
	
	private static SerSingleton instance = new SerSingleton();
	public static SerSingleton getInstance(){
		return instance;
	}
	
	public static void createString(){
		System.out.println("createString is SerSingleton");
	}
	
	private Object readResolve(){//阻止生成新的实例，总是返回当前对象
		return instance;
	}
	
	
	public static void test() throws IOException, ClassNotFoundException{
		SerSingleton ser = null;
		SerSingleton s = SerSingleton.getInstance();
		//现将实例串行化到文件
		FileOutputStream fos = new FileOutputStream("SerSingleton.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s);
		oos.flush();
		oos.close();
		//从文件读出原有的单例类
		FileInputStream fis = new FileInputStream("SerSingleton.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ser = (SerSingleton) ois.readObject();
		
		System.out.println("ser:"+ ser +",s: "+ ser);
	}
}
