package com.whv.common.utils.cache;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 缓存Map
 * 使用LinkedHashMap实现LRU缓存
 * @author whv
 *
 * @param <K>
 * @param <V>
 */
public class CacheMap<K,V> extends LinkedHashMap<K, V> implements Serializable{
	private static final long serialVersionUID = -5570171865231127226L;
	private final static int DEFAULT_CACHE_SIZE = 100;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private int maxSize = 0;
	public CacheMap() {
        super(DEFAULT_CACHE_SIZE);
        this.maxSize = DEFAULT_CACHE_SIZE;
    }
	public CacheMap(int size) {
        super(size,DEFAULT_LOAD_FACTOR,true);
        this.maxSize = size;
    }
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size()>this.maxSize;
    }
}
