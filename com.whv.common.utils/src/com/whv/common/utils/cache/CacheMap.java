package com.whv.common.utils.cache;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * ʹ��LinkedHashMapʵ��LRU����
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
	/**
	 * ����MAP������
	 * ��ʼ�����������������ΪĬ��ֵ
	 */
	public CacheMap() {
        this(DEFAULT_CACHE_SIZE);
    }
	/**
	 * ����MAP������
	 * @param size ��ʼ���������������
	 */
	public CacheMap(int size) {
        this(size,size);
    }
	/**
	 * ����MAP������
	 * @param initialCapacity ��ʼ������
	 * @param maxSize �������
	 */
	public CacheMap(int initialCapacity,int maxSize) {
        this(initialCapacity,maxSize,DEFAULT_LOAD_FACTOR);
    }
	/**
	 * ����MAP������
	 * @param size ��ʼ���������������
	 * @param loadFactor ��������
	 */
	public CacheMap(int size, float loadFactor) {
        this(size,size,loadFactor);
    }
	/**
	 * ����MAP������
	 * @param initialCapacity ��ʼ������
	 * @param maxSize �������
	 * @param loadFactor ��������
	 */
	public CacheMap(int initialCapacity,int maxSize, float loadFactor) {
        super(initialCapacity,loadFactor,true);
        this.maxSize = maxSize;
    }
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size()>this.maxSize;
    }
}
