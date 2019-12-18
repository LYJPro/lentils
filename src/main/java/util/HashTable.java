package util;

import java.util.TreeMap;

public class HashTable<K, V> {
	private TreeMap<K, V>[] hashtable;
	private int M = 31; // 素数
	
	private int upperTol = 10; // 每个地址链元素数量超过上界，就扩容
	private int lowerTol = 2; // 每个地址链元素数小于下界，就缩容
	private int initCapacity = 7; // 初始值
	
	private int size;

	@SuppressWarnings("unchecked")
	public HashTable(int M) {
		this.M = M;
		this.hashtable = new TreeMap[M];
		for (int i = 0; i < hashtable.length; i++) {
			hashtable[i] = new TreeMap<>();
		}
	}
	
	public HashTable() {
		this(97);
	}
	
	public void add(K key, V value) {
		TreeMap<K, V> map = hashtable[hash(key)];
		
		if (map.containsKey(key)) {
			map.put(key, value);
		} else {
			map.put(key, value);
			size++;
			
			if (size >= upperTol * M) {
				resize(M * 2);
			}
		}
	}
	
	public V remove(K key) {
		TreeMap<K, V> map = hashtable[hash(key)];
		
		V ret = null;
		if (map.containsKey(key)) {
			ret = map.remove(key);
			size--;
			
			if (size < lowerTol * M && M / 2 >= initCapacity) {
				resize(M / 2);
			}
		}
		return ret;
	}
	
	public void set(K key, V value) {
		TreeMap<K, V> map = hashtable[hash(key)];
		
		if (map.containsKey(key)) {
			map.put(key, value);
		}
	}
	
	public boolean contains(K key) {
		return hashtable[hash(key)].containsKey(key);
	}
	
	public V get(K key) {
		return hashtable[hash(key)].get(key);
	}
	
	public int size() {
		return size;
	}
	
	private int hash(K key) {
		return (key.hashCode() & 0xffffffff) % M ;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int newM) {
		TreeMap<K, V>[] newHashtable = new TreeMap[newM];
		for (int i = 0; i < newHashtable.length; i++) {
			newHashtable[i] = new TreeMap<>();
		}
		
		int oldM = M;
		this.M = newM;
		for (int i = 0; i < oldM; i++) {
			TreeMap<K, V> map = hashtable[i];
			for (K key : map.keySet()) {
				newHashtable[hash(key)].put(key, map.get(key));
			}
		}
		
		this.hashtable = newHashtable;
	}
}
