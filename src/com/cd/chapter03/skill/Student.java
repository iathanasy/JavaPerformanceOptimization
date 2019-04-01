package com.cd.chapter03.skill;

import java.util.Vector;

/**
 * 
 * @author cd
 * @date 2019年4月1日 下午2:37:31
 * @desc clone()
 */
public class Student implements Cloneable{

	private int id;
	private String name;
	private Vector vector;
	
	public Student(){
		try {
			Thread.sleep(1000);
			System.out.println("Student create");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector getVector() {
		return vector;
	}
	public void setVector(Vector vector) {
		this.vector = vector;
	}
	
	public Student newInstance(){ //使用clone()创建一个对象
		try {
			return (Student) this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		//对于Vector 对象等普通对象， 克隆对象和原始对象拥有相同的引用，不是值拷贝，而是浅拷贝。
		//深拷贝需要重载Object的clone()
		Student stu = (Student) super.clone();
		Vector v = stu.getVector();
		Vector v1 = new Vector();//创建新的Vector
		for (Object o : v) {
			v1.add(o); //复制原来的Vector
		}
		stu.setVector(v1);//使用新的Vector
		return stu;
	}
	
	
	public static void main(String[] args) {
		Student stu = new Student();	
		Vector cs = new Vector();
		cs.add("Java");
		stu.setId(1);
		stu.setName("A");
		stu.setVector(cs);
		
		Student stu2 = stu.newInstance();//使用clone()方法生成对象 没有调用构造函数
		stu2.setId(2);
		stu2.setName("B");
		stu2.getVector().add("C#");//修改了Vector数据
		
		System.out.println("stu.name: "+ stu.getName());
		System.out.println("stu2.name: "+ stu2.getName());
		//重载了clone()  克隆对象和原始对象持有不同的引用 
		System.out.println(stu.getVector() == stu2.getVector());//两个Student使用同一个Vector
	}
	
}
