package com.cd.chapter03.unsafe;

import java.lang.reflect.Field;
import java.util.Map;
import sun.misc.Unsafe;

public class UnsafeTest {

	 public static void main(String[] args){
	        testSize();
	    }

	    static public void testSize(){
	        Unsafe U = getUnsafeInstance();
	        Class<?> ak = Node[].class;
	        int abase = U.arrayBaseOffset(ak);
	        int scale = U.arrayIndexScale(ak);
	        System.out.println(abase);
	        System.out.println(scale);
	    }

	    private static Unsafe getUnsafeInstance() {
	        try {
	            Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
	            theUnsafeInstance.setAccessible(true);
	            return (Unsafe) theUnsafeInstance.get(Unsafe.class);
	        }catch (Exception e){
	            throw new RuntimeException(e);
	        }
	    }

	    static class Node<K,V> implements Map.Entry<K,V> {
	        final int hash;
	        final K key;
	        volatile V val;
	        volatile Node<K,V> next;

	        Node(int hash, K key, V val, Node<K,V> next) {
	            this.hash = hash;
	            this.key = key;
	            this.val = val;
	            this.next = next;
	        }

	        public final K getKey()       { return key; }
	        public final V getValue()     { return val; }
	        public final int hashCode()   { return key.hashCode() ^ val.hashCode(); }
	        public final String toString(){ return key + "=" + val; }
	        public final V setValue(V value) {
	            throw new UnsupportedOperationException();
	        }
	    }


}
