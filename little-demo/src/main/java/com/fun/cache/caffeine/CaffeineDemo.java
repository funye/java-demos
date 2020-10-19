package com.fun.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CaffeineDemo {

    public static void main(String[] args) {
        Cache<String, TestData> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();

        TestData data = new TestData("张三",new Date(),25);

             // Insert or update an entry
        cache.put("key1", data);

        // Lookup an entry, or null if not found
        data = cache.getIfPresent("key1");
        System.out.println("key1===========" + data);

        // Lookup and compute an entry if absent, or null if not computable
        data = cache.get("key2", k -> new TestData("李四",new Date(),25));
        System.out.println("key1===========" + data);

//         Remove an entry
        cache.invalidate("key1");
        data = cache.getIfPresent("key1");
        System.out.println("key1===========" + data);

        long l = 10_000;
        System.out.println(l);

        // test map
        Map<String, TestData> map = new HashMap<>();
        map.put("test1", new TestData("张三",new Date(),25));
        map.put("test2", new TestData("李四",new Date(),25));
        cache.putAll(map);

        Cache<String, TestData> cacheMap = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        cacheMap.putAll(map);
        TestData test = cacheMap.getIfPresent("test1");
        System.out.println("test1============" + test);

        Map<String, TestData> m = cacheMap.getAllPresent(map.keySet());
        System.out.println("all============" + m);
        cacheMap.cleanUp(); // 只是清楚过期的key而已
        m = cacheMap.getAllPresent(map.keySet());
        System.out.println("after clean up============" + m);
        cacheMap.invalidateAll(); // 只是key失效
        m = cacheMap.getAllPresent(map.keySet());
        System.out.println("after invalidate all============" + m);


    }


    @Data
    static class TestData {
        private String name;
        private Date birthday;
        private int age;

        public TestData(String name, Date birthday, int age) {
            this.name = name;
            this.birthday = birthday;
            this.age = age;
        }
    }
}
