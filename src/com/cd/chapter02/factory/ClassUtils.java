package com.cd.chapter02.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @description: 工具类
 * @author: Mr.Wang
 * @create: 2019-03-27 21:30
 **/
public class ClassUtils {

	/**
	 * 
	 * @param c
	 *            接口
	 * @return 返回这个接口的所有实现类
	 */
	public static List<Class> getAllClassByinterface(Class c) {
		List<Class> classList = new ArrayList<Class>();

		if (c.isInterface()) {// 如果不是接口则不处理
			String packageName = c.getPackage().getName();// 当前包名

			try {
				// 当前包下 以及子包下的所有类
				List<Class> allClass = getClasses(packageName);
				// System.out.println(allClass);
				// 判断是否是同一个接口
				for (int i = 0; i < allClass.size(); i++) {
					if (c.isAssignableFrom(allClass.get(i))) {// 判断是不是一个接口
						if (!c.equals(allClass.get(i)))// 本身不加进去
							classList.add(allClass.get(i));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return classList;
	}

	/**
	 * 从一个包中查找出所有的类， 在Jar包中的不查找
	 * 
	 * @param packageName
	 * @return
	 */
	private static List<Class> getClasses(String packageName)
			throws IOException, ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}

		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;
	}

	/**
	 * 
	 * @param directory
	 *            文件
	 * @param packageName
	 *            包名
	 * @return
	 */
	private static List<Class> findClasses(File directory, String packageName)
			throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists())
			return classes;
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName
						+ "."
						+ file.getName().substring(0,
								file.getName().length() - 6)));
			}
		}

		return classes;
	}

	public static void main(String[] args) {
		System.out.println(getAllClassByinterface(Human.class));
	}
}