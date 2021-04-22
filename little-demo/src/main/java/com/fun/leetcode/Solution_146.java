package com.fun.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU 缓存机制
 */
public class Solution_146 {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }

    static class LRUCache {

        private LinkedHashMap<Integer, Integer> cache;

        public LRUCache(int capacity) {
            cache = new LinkedHashMap<>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                return cache.get(key);
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }
    }
}
