package com.cd.chapter05.cache;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存
 * @author cd
 * @date 2019年5月24日 上午9:33:28
 * @desc
 */
public class Ehcache {
	
	private final static Logger logger = LoggerFactory.getLogger(Ehcache.class);

	static CacheManager manager = null;
	static String cacheName = "cache";

    static {
        try {
            manager = CacheManager.create("ehcache.xml");
        } catch (CacheException e) {
            logger.error("获取ehcache.xml失败", e.getMessage());
        }
    }
/*    
    private volatile static Ehcache instance = null;
    
    public static Ehcache getInstance(){
    	if(instance == null){
    		synchronized (Ehcache.class) {
    			if(instance == null){
    				instance = new Ehcache();
    			}
			}
    	}
    	return instance;
    }*/
    
    /**
     * 存入
     *
     * @param <T>       the type parameter
     * @param key       键
     * @param value     值
     */
    public static <T extends Serializable> void put(String key, T value) {
        Cache cache = checkCache(cacheName);
        Element e = new Element(key, value);
        cache.put(e);
        cache.flush();
    }
    
    /**
     * 存入
     *
     * @param <T>       the type parameter
     * @param cacheName the cache name
     * @param key       键
     * @param value     值
     */
    public static <T extends Serializable> void put(String cacheName, String key, T value) {
        Cache cache = checkCache(cacheName);
        Element e = new Element(key, value);
        cache.put(e);
        cache.flush();
    }

    /**
     * 存入 并设置元素是否永恒保存
     *
     * @param <T>       the type parameter
     * @param cacheName the cache name
     * @param key       键
     * @param value     值
     * @param eternal   对象是否永久有效，一但设置了，timeout将不起作用
     */
    public static <T extends Serializable> void put(String cacheName, String key, T value, boolean eternal) {
        Cache cache = checkCache(cacheName);
        Element element = new Element(key, value);
        element.setEternal(eternal);
        cache.put(element);
        cache.flush();
    }

    /**
     * 存入
     *
     * @param <T>               the type parameter
     * @param cacheName         the cache name
     * @param key               键
     * @param value             值
     * @param timeToLiveSeconds 最大存活时间
     * @param timeToIdleSeconds 最大访问间隔时间
     */
    public static <T extends Serializable> void put(String cacheName, String key, T value, int timeToLiveSeconds, int timeToIdleSeconds) {
        Cache cache = checkCache(cacheName);
        Element element = new Element(key, value);
        element.setTimeToLive(timeToLiveSeconds);
        element.setTimeToIdle(timeToIdleSeconds);
        cache.put(element);
        cache.flush();
    }

    /**
     * Get object.
     *
     * @param key       the key
     * @return the object
     */
    public static Object get(String key) {
        Cache cache = checkCache(cacheName);
        Element e = cache.get(key);
        if (e != null) {
            return e.getObjectValue();
        }
        return null;
    }
    
    /**
     * Get object.
     *
     * @param cacheName the cache name
     * @param key       the key
     * @return the object
     */
    public static Object get(String cacheName, String key) {
        Cache cache = checkCache(cacheName);
        Element e = cache.get(key);
        if (e != null) {
            return e.getObjectValue();
        }
        return null;
    }

    /**
     * Remove.
     *
     * @param key       the key
     */
    public static void remove(String key) {
        Cache cache = checkCache(cacheName);
        cache.remove(key);
    }
    
    /**
     * Remove.
     *
     * @param cacheName the cache name
     * @param key       the key
     */
    public static void remove(String cacheName, String key) {
        Cache cache = checkCache(cacheName);
        cache.remove(key);
    }

    /**
     * Remove all.
     *
     * @param cacheName the cache name
     * @param keys      the keys
     */
    public static void removeAll(String cacheName, Collection<String> keys) {
        Cache cache = checkCache(cacheName);
        cache.removeAll(keys);
    }

  /**
     * Clears the contents of all caches in the CacheManager, but without
     * removing any caches.
     * <p/>
     * This method is not synchronized. It only guarantees to clear those elements in a cache at 
     * the time that the
     * {@link Ehcache#removeAll()} mehod on each cache is called.
     */
    public static void clearAll() {
        manager.clearAll();
    }

    private static Cache checkCache(String cacheName) {
        Cache cache = manager.getCache(cacheName);
        if (null == cache) {
            throw new IllegalArgumentException("name=["+cacheName+"],不存在对应的缓存组,请查看ehcache.xml");
        }
        return cache;
    }
    
    public static void close(){
    	if(manager != null){
    		manager.shutdown();
    	}
    }
    
    public static void main(String[] args) {
    	initLogRecord();
    	String cacheName = "cache";
    	
    	try {
    		/*for (int i = 1; i <= 100; i++) {
    			Ehcache.put(cacheName, "a"+i, "value"+i);
			}*/
			
			//Ehcache.remove(cacheName, "a");
			Object obj = Ehcache.get(cacheName, "a1");
			logger.info(obj.toString());
		} catch (Exception e) {
			logger.error("cache:"+ e.getMessage());
		}finally{
			Ehcache.close();
		}
		
	}
    
    private static void initLogRecord(){
        Properties props = null;  
        FileInputStream fis = null;  
        try {  
            props = new Properties();  
            fis = new FileInputStream("log4j.properties");  
            props.load(fis);  
            PropertyConfigurator.configure(props);
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (fis != null)  
                try {  
                    fis.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            fis = null;  
        }  
}
}
