package fr.grabuges.api.factory;

import fr.grabuges.api.factory.exception.FactoryRuntimeException;

import java.util.HashMap;
import java.util.Map;

public class SimpleAbstractFactoryMap<K, V> {

    private final Map<K, V> objects;

    protected SimpleAbstractFactoryMap() {
        objects = new HashMap<>();
    }

    public void put(K key, V value) {
        if (objects.containsKey(key))
            throw new FactoryRuntimeException("Object (%s) already in object list.", value.getClass().getSimpleName());

        objects.put(key, value);
    }

    public void replace(K key, V value) {
        objects.put(key, value);
    }

    public V getOrDefault(K key, V value) {
        return objects.getOrDefault(key, value);
    }

    public V get(K key) {
        return objects.get(key);
    }

    public Map<K, V> get() {
        return objects;
    }

    public void remove(K key) {
        objects.remove(key);
    }

}
