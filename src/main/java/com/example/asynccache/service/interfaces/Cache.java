package com.example.asynccache.service.interfaces;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for cache
 */
public interface Cache {
    void writeCache(String key, Integer value);
    CompletableFuture<Integer> readCache(String key);
}
