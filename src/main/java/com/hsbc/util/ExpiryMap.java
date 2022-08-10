package com.hsbc.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ExpiryMap<K, V> implements Map<K, V> {
    private ConcurrentHashMap workMap;
    private ConcurrentHashMap expiryMap;
    private long EXPIRYTIME = 1000 * 60 * 2;
    private long CIRCULATIONTIME = 1000 * 60 * 5;
    private long DELAY = 1000 * 60;

    public ExpiryMap() {
        this(16, 0);
    }

    public ExpiryMap(long expiryTime) {
        this(16, expiryTime);
    }

    public ExpiryMap(int initialCapacity, long expiryTime) {
        if (expiryTime > 0)
            this.EXPIRYTIME = expiryTime * 1000;
        workMap = new ConcurrentHashMap(initialCapacity);
        expiryMap = new ConcurrentHashMap(initialCapacity);
    }

    @Override
    public V put(K key, V value) {
        expiryMap.put(key, System.currentTimeMillis() + EXPIRYTIME);
        return (V) workMap.put(key, value);
    }

    public V put(K key, V value, long exrity) {
        expiryMap.put(key, System.currentTimeMillis() + exrity * 1000);
        return (V) workMap.put(key, value);
    }

    private void removeExpiryKeys() {
        Set set = expiryMap.keySet();
        for (Object o : set) {
            checkExpiry((K) o, true);
        }
    }

    private boolean checkExpiry(K key, boolean isDelete) {
        Object timeObject = expiryMap.get(key);
        if (timeObject == null) {
            return true;
        }
        long setTime = Long.parseLong(timeObject.toString());
        boolean isExpiry = System.currentTimeMillis() - setTime >= 0;
        if (isExpiry) {
            if (isDelete) {
                expiryMap.remove(key);
                workMap.remove(key);
            }
            return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        boolean isExpiry = checkExpiry((K) key, true);
        if (isExpiry) {
            return null;
        }
        return (V) workMap.get(key);
    }

    @Override
    public int size() {
        removeExpiryKeys();
        return workMap.size();
    }

    @Override
    public boolean isEmpty() {
        removeExpiryKeys();
        return workMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        removeExpiryKeys();
        return workMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        removeExpiryKeys();
        return workMap.containsValue(value);
    }

    @Override
    public V remove(Object key) {
        expiryMap.remove(key);
        return (V) workMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Set<? extends Entry<? extends K, ? extends V>> entries = m.entrySet();
        for (Entry<? extends K, ? extends V> en : entries) {
            expiryMap.put(en.getKey(), System.currentTimeMillis() + EXPIRYTIME);
            workMap.put(en.getKey(), en.getValue());
        }
    }

    @Override
    public void clear() {
        expiryMap.clear();
        workMap.clear();
    }

    @Override
    public Set<K> keySet() {
        removeExpiryKeys();
        return workMap.keySet();
    }

    @Override
    public Collection<V> values() {
        removeExpiryKeys();
        return workMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        removeExpiryKeys();
        return workMap.entrySet();
    }

    public void setCIRCULATIONTIME(long CIRCULATIONTIME) {
        this.CIRCULATIONTIME = CIRCULATIONTIME;
    }

    public void setDELAY(long DELAY) {
        this.DELAY = DELAY;
    }

    public long getCIRCULATIONTIME() {
        return CIRCULATIONTIME;
    }

    public long getDELAY() {
        return DELAY;
    }
}