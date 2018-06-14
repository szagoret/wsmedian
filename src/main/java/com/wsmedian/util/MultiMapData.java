package com.wsmedian.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class MultiMapData<K, V, L> {

    private final Map<K, Map<V, L>> multiMapData = new LinkedHashMap<>();

    public void put(K key, V subKey, L value) {
        this.multiMapData.computeIfAbsent(key, k -> new LinkedHashMap<>()).put(subKey, value);
    }

    public Map<K, Map<V, L>> getMap() {
        return multiMapData;
    }
}
