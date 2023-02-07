package com.example.asynccache.service.impl;

import com.example.asynccache.repositories.DataStore;
import com.example.asynccache.service.interfaces.Cache;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Cache Service
 */
@Service
@AllArgsConstructor
public class CacheImpl implements Cache {

    /**
     *  DAO for saving cache
     */
    DataStore dataStore;

    /**
     * Async method for write to cache
     * @param key - key
     * @param value - value
     */
    @Async
    public void writeCache(String key, Integer value) {
        dataStore.write(key, value);
    }

    /**
     * Async method for read from cache
     * @param key - key
     * @return - CompletableFuture object with result
     */
    @Async
    public CompletableFuture<Integer> readCache(String key) {
        return CompletableFuture.completedFuture(dataStore.read(key));
    }
}
