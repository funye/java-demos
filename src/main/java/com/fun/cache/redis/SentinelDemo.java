package com.fun.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Transaction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fun
 *
 * @date 2017/5/18.
 */
public class SentinelDemo {

    public static void main(String[] args) throws Exception {

        Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.206.200:26379");
        sentinels.add("192.168.206.201:26379");
        sentinels.add("192.168.206.202:26379");
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);

        while(true) {
            System.out.println("press enter to continue");
            System.in.read();

            Jedis jedis = pool.getResource();
            System.out.println(pool.getCurrentHostMaster());
            String name = jedis.get("name");
            System.out.println("redis get key name="+name);
            jedis.close();
            jedis.watch("user","age");
            Transaction trans = jedis.multi();
            jedis.set("user","user1");
            jedis.set("age","26");
            trans.exec();
            jedis.unwatch();// unwatch 释放所有的乐观锁

        }

    }

}
