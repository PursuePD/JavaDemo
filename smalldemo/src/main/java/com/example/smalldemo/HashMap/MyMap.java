package com.example.smalldemo.HashMap;

/**
 * @author: 小崔
 * @create: 2019-11-20 14:38
 * @Description:
 */
public interface MyMap<K,V> {

    public V put(K k,V v);
    public V get(K k);

    interface Entry<K,V>{
        public K getKey();
        public V getValue();
    }
}
