package com.example.asynccache.repositories;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class for store data
 */
@Repository
public class DataStore {

    /**
     * Store
     */
    private final ConcurrentHashMap<String, Integer> data = new ConcurrentHashMap<>();

    /**
     * Write method
     * @param key - key
     * @param value - value
     */
    public void write(String key, Integer value) {
        data.put(key, value);
    }

    /**
     * Read method
     * @param key - key
     * @return - value
     */
    public Integer read(String key) {
        return data.get(key);
    }

}
