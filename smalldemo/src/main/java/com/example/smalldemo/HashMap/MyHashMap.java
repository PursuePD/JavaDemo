package com.example.smalldemo.HashMap;

/**
 * @author: 小崔
 * @create: 2019-11-21 16:48
 * @Description:
 */
public class MyHashMap<K,V> implements MyMap{

    /**
     * 默认长度
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * 默认阈值
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    private int defaultInitSize;
    private float defaultLoadFactor;


    /**
     * map中entry的数量
     */
    private int entryUseSize;

    private Entry<K,V>[] table = null;

    /**
     *
     * @param initialCapacity 初始容量
     * @param defaultLoadFactor 负载因子
     */
    public MyHashMap(int initialCapacity, float defaultLoadFactor) {
	if (initialCapacity < 0){
	    throw new IllegalArgumentException("Illegal initial capacity: " +
		    initialCapacity);
	}
	if (initialCapacity > MAXIMUM_CAPACITY){
	    initialCapacity = MAXIMUM_CAPACITY;
	}
	if (defaultLoadFactor <= 0 || Float.isNaN(defaultLoadFactor)){
	    throw new IllegalArgumentException("Illegal load factor: " +
		    defaultLoadFactor);
	}

	this.defaultLoadFactor = defaultLoadFactor;
	this.defaultInitSize = initialCapacity;
	table = new Entry[this.defaultInitSize];
    }


    @Override
    public Object put(Object o, Object o2) {
	return null;
    }

    @Override
    public Object get(Object o) {
	return null;
    }
}
