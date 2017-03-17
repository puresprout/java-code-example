package datastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 1002703 on 2017. 1. 25..
 */
public class LruCache<K, V> {
    private LinkedHashMap<K, V> map;
    private int cacheSize;

    public LruCache(int cacheSize) {
        this.cacheSize = cacheSize;
        map = new LinkedHashMap<K, V>(0, 0.75f, true);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);

        trimToSize();
    }

    private void trimToSize() {
        if (cacheSize < map.size()) {
            // 이렇게 하나만 remove하면 위험할려나?
            Map.Entry<K, V> toEvict = map.entrySet().iterator().next();
            if (toEvict != null) {
                map.remove(toEvict.getKey());
            }
        }
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void clear() {
        map.clear();
    }

    // 최근에 덜 접근된 순서에서 최근에 더 접근된 순서로
    public synchronized Collection<Map.Entry<K, V>> getAll() {
        return new ArrayList<Map.Entry<K, V>>(map.entrySet());
    }
}
