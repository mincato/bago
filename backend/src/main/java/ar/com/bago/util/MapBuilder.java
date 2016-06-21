package ar.com.bago.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapBuilder {

    public static <K, V> Map<K, V> concurrent(K key, V value) {
        Map<K, V> map = new ConcurrentHashMap<>();
        map.put(key, value);
        return map;
    }
}
