package com.fun.leetcode;

import java.util.*;

public class Solution_460 {

    public static void main(String[] args) {
        LFUCache lFUCache = new LFUCache(2);
        lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
        lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lFUCache.get(1));      // 返回 1 cache=[1,2], cnt(2)=1, cnt(1)=2
        lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小 cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lFUCache.get(2));      // 返回 -1（未找到）
        System.out.println(lFUCache.get(3));      // 返回 3 cache=[3,1], cnt(3)=2, cnt(1)=2
        lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用  cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lFUCache.get(1));      // 返回 -1（未找到）
        System.out.println(lFUCache.get(3));      // 返回 3 cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lFUCache.get(4));      // 返回 4 cache=[3,4], cnt(4)=2, cnt(3)=3
    }

    static class LFUCache {

        // 插入排序 小到大
        class Node implements Comparable<Node>{
            int key;
            int value;
            int accessCount;
            int time;

            public Node(int key, int value, int accessCount, int time) {
                this.key = key;
                this.value = value;
                this.accessCount = accessCount;
                this.time = time;
            }

            @Override
            public int compareTo(Node o) {
                return this.accessCount == o.accessCount ? this.time - o.time : this.accessCount-o.accessCount;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o instanceof Node) {
                    Node node = (Node) o;
                    return key == node.key && accessCount == node.accessCount && time == node.time;
                }
                return false;
            }

            @Override
            public int hashCode() {
                return accessCount * 1000000007 + time;
            }
        }

        private Map<Integer, Node> cache;
        private int capacity;
        private TreeSet<Node> cnt;
        private int time;

        public LFUCache(int capacity) {
            this.cache = new HashMap<>();
            this.capacity = capacity;
            this.cnt = new TreeSet<>();
            this.time = 0;
        }

        public int get(int key) {
            if (capacity == 0) {
                return -1;
            }

            if (!cache.containsKey(key)) {
                return -1;
            }

            Node node = cache.get(key);
            cnt.remove(node);
            node.accessCount++;
            node.time = ++time;
            cnt.add(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }

            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                cnt.remove(node);
                node.accessCount++;
                node.time = ++time;
                node.value = value;
                cnt.add(node);
            } else {
                Node node = new Node(key, value, 1, ++time);
                if (cache.size() == capacity) {
                    Node rm = cnt.first();
                    cache.remove(rm.key);
                    cnt.remove(rm);
                }
                cache.put(key, node);
                cnt.add(node);

            }
        }

    }
}
