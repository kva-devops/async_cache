package com.example.asynccache;

import com.example.asynccache.service.impl.CacheImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AsyncCacheApplicationTestsImpl {

    @Autowired
    CacheImpl cacheImpl;

    @Test
    void showCache() throws InterruptedException {
        AtomicReference<Integer> result = new AtomicReference<>();
        String key = "string";
        Integer value = 1;

        Thread thread1 = new Thread(() -> {
            cacheImpl.writeCache(key, value);
        });
        thread1.start();
        thread1.join();

        Thread thread2 = new Thread(() -> {
            CompletableFuture<Integer> resultCF = cacheImpl.readCache(key);
            try {
                result.set(resultCF.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        thread2.start();
        thread2.join();

        assertEquals(1, result.get());
    }

    @Test
    void emptyCache() throws InterruptedException {
        AtomicReference<Integer> result = new AtomicReference<>();

        Thread thread2 = new Thread(() -> {
            String key = "key";
            CompletableFuture<Integer> resultCF = cacheImpl.readCache(key);
            try {
                result.set(resultCF.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        thread2.start();
        thread2.join();

        assertNull(result.get());
    }
}
