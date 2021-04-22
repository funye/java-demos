package com.fun.cache.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonDemo {

    public static void main(String[] args) throws Exception {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                RMap<String,Object> map = redisson.getMap("test-map");
//                map.get("name");
//                System.out.println("===========thread get end");
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                RMap<String, Object> map = redisson.getMap("test-map");
                map.isEmpty();
                System.out.println("============thread isEmpty end");
            }
        }, "isEmpty-thread").start();

        System.out.println("=====================main last line");

        RLock lock = redisson.getLock("lock-key");
        lock.lock();
        test();
        lock.unlock();
    }

    public static void test() {

    }
}
