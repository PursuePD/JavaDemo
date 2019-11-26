package com.example.smalldemo.HashMap;

import com.example.位运算.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: 小崔
 * @create: 2019-11-21 16:48
 * @Description:
 */
public class MyHashMap<K,V> {

    private static final Logger log = LoggerFactory.getLogger(MyHashMap.class);

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

    static class Entry<K,V> {
	private K key;
	private V value;
	private Entry<K,V> next;

	public Entry(){

	}

	public Entry(K key, V value,Entry<K,V> next) {
	    this.key = key;
	    this.value = value;
	    this.next = next;
	}

	public K getKey() {
	    return key;
	}

	public V getValue() {
	    return value;
	}
    }


    public V put(K k, V v) {
	V oldValue = null;
	//判断是否要扩容
	if(entryUseSize >= defaultInitSize*defaultLoadFactor){
	    resize(2*defaultInitSize);
	}

	int index = hash(k) & (defaultInitSize - 1);
	log.info("hash(k):{},(defaultInitSize - 1):{},index:{}",hash(k),(defaultInitSize - 1),index);
	if(table[index] == null){
	    table[index] = new Entry(k,v,null);
	    ++entryUseSize;
	}else{
	    //遍历单链表
	    Entry<K,V> entry = table[index];
	    Entry<K,V> e = entry;
	    while (e.next != null){
	        //判断并覆盖
		if(k == e.getKey() || k.equals(e.getKey())){
		    oldValue = e.value;
		    e.value = v;
		    return  oldValue;
		}
		//新插入
		e = e.next;
	    }
	    table[index] = new Entry<K,V>(k,v,entry);//头插
	    ++entryUseSize;
	}
	return oldValue;
    }

    public V get(K k) {

	int index = hash(k) & (defaultInitSize - 1);

	if(table[index] == null){
	    return null;
	}else{
	    Entry<K,V> entry = table[index];
	    do{
		if(k == entry.getKey() || k.equals(entry.getKey())){
		    return  entry.value;
		}
		entry = entry.next;
	    }while (entry != null);
	}
	return null;
    }


    private void resize(int i){
        Entry[] newTable = new Entry[i];

        defaultInitSize = i;
        entryUseSize = 0;
        rehash(newTable);
    }

    private void rehash(Entry<K,V>[] newTable){
	List<Entry<K,V>> entryList = new ArrayList<>();
	for (Entry<K, V> entry : table) {
	    if(entry != null){
	        do{
	            entryList.add(entry);
	            entry = entry.next;
		}while (entry != null);
	    }
	}

	if(newTable.length > 0){
	    table = newTable;
	}

	//重新hash（put）
	for (Entry<K, V> entry : entryList) {
	    put(entry.getKey(), entry.value);
	}
    }

    static final int hash(Object key) {
	int h = 0;
//	int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//	log.info("key:{},code:{},h:{},h >>> 16:{}",key.toString(),hash,h,(h >>> 16));
	return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public int getEntryUseSize() {
	return entryUseSize;
    }
}
