package ru.otus.cachehw;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
    Map<K, V> weakHashMap = new WeakHashMap<>();
    List<HwListener<K, V>> hwListenerList = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        weakHashMap.put(key, value);
        hwListenerList.forEach(s -> s.notify(key, value, "put"));
    }

    @Override
    public void remove(K key) {
        var value = weakHashMap.remove(key);
        hwListenerList.forEach(s -> s.notify(key, value, "remove"));
    }

    @Override
    public V get(K key) {
        var value = weakHashMap.get(key);
        hwListenerList.forEach(s -> s.notify(key, value, "get"));
        return value;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        hwListenerList.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        hwListenerList.remove(listener);
    }
}
