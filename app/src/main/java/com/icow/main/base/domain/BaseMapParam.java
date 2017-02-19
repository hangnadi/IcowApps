package com.icow.main.base.domain;

import java.util.HashMap;

/**
 * Created by hangnadi on 2/10/17.
 */
public class BaseMapParam<K, V> extends HashMap<K, V> {

    @Override
    public V put(K key, V value) {
        if (value == null) {
            throw new RuntimeException("value can't be null |KEY = " + key);
        }
        return super.put(key, value);
    }
}
