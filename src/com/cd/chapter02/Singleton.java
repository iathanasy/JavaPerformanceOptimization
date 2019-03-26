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
 * @date 2019��3��25�� ����11:29:34
 * @desc 1.����ģʽ
 * ע�⣺ ���л��ͷ����л����ܻ��ƻ�������
 */
public class Singleton {

	private Singleton(){
		//û��ʹ�õ����໹�Ǳ����������� out 
		//Singleton is create
		//createString is Singleton
		//Singleton.createString();
		System.out.println("Singleton is create");//���������Ĺ��̿��ܻ�Ƚ���
	}
	
	private static Singleton instance = new Singleton();
	public static Singleton getInstance(){
		return instance;
	}
	
	public static void createString(){
		//����ģ�ⵥ�������������ɫ
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
			//�ӳټ��� ���õ�ͬ���ؼ��ַ���������ϵͳ������ �е�ò���ʧ ��Ҫ����Ľ�
			//LazySingleton.getInstance();// spend: 29 ms
			StaticSingleton.getInstance();
		}
		System.out.println("spend: "+ (System.currentTimeMillis() - beginTime));
		
	}
}

/**
 * 
 * @author cd
 * @date 2019��3��25�� ����11:37:24
 * @desc 2.�����ӳټ���  ����ʱ�Ĵ��ڵ�һ��
 */
class LazySingleton{
	private LazySingleton(){
		System.out.println("LazySingleton is create");
	}
	
	private static LazySingleton instance = null;
	
	/**
	 * synchronized ����ʹ��ͬ��,������̻߳�����
	 * ���߳�1���½�����ʱ����ɸ�ֵ����ǰ���߳�2�����ж�Ϊinstance = null ��
	 * ���߳�2Ҳ�������½��������򣬶����¶��ʵ����������
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
 * @date 2019��3��25�� ����2:07:42
 * @desc 3.����ģʽ �ڲ�����ά��������ʵ�� 
 * ʹ���ڲ��� �ȿ��������ӳټ��� Ҳ����ʹ��ͬ���ؼ��� ��һ�ֱȽ����Ƶ�ʵ��
 */
class StaticSingleton{
	private StaticSingleton(){
		System.out.println("StaticSingleton is create");
	}
	/**
	 * �ڲ���ģʽ 
	 * ��StaticSingleton������ʱ�����ڲ��಻�ᱻ��ʼ����
	 * ��StaticSingleton �౻����JVMʱ�������ʼ������
	 * ����getInstance() ����������ʱ���Ż����SingletonHolder,�Ӷ���ʼ��instance
	 * ����ʵ�����������ʱ��ɣ��������Զ��߳��Ѻ�,����ʹ�� synchronized
	 */
	private static class SingletonHolder{
		private static StaticSingleton instance = new StaticSingleton();
	}
	
	public static StaticSingleton getInstance(){
		return SingletonHolder.instance;
	}
}

/**
 * ����
 * @author cd
 * @date 2019��3��25�� ����2:44:35
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
	
	private Object readResolve(){//��ֹ�����µ�ʵ�������Ƿ��ص�ǰ����
		return instance;
	}
	
	
	public static void test() throws IOException, ClassNotFoundException{
		SerSingleton ser = null;
		SerSingleton s = SerSingleton.getInstance();
		//�ֽ�ʵ�����л����ļ�
		FileOutputStream fos = new FileOutputStream("SerSingleton.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s);
		oos.flush();
		oos.close();
		//���ļ�����ԭ�еĵ�����
		FileInputStream fis = new FileInputStream("SerSingleton.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ser = (SerSingleton) ois.readObject();
		
		System.out.println("ser:"+ ser +",s: "+ ser);
	}
}
