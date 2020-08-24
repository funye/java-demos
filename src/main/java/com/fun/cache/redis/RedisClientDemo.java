package com.fun.cache.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 * @author huanye
 * @date: 2017/7/25 上午10:45
 */
public class RedisClientDemo {

    public static void main(String[] args) {
//        JedisPool pool = new JedisPool(new JedisPoolConfig(),"120.77.179.58",6379,2000,"123123");
//        JedisPool pool = new JedisPool(new JedisPoolConfig(),"172.28.3.21",6379,2000,"password");
//        Jedis jedis = pool.getResource();
//        jedis.select(0);
//
//        Set<String> keys = jedis.keys("user_session_openid_key_*");
//        keys.forEach(e->{
//            System.out.println("key=" + e);
//            jedis.del(e);
//        });
//
//        long count = jedis.del("wechat_access_token_".getBytes());
//        jedis.close(); // 返回连接资源
//        System.out.println(count);


        List<JedisShardInfo> shardInfos = new ArrayList<>();
        JedisShardInfo info0 = new JedisShardInfo("120.77.179.58",6379,2000);
        info0.setPassword("123123");
        shardInfos.add(info0);
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(new GenericObjectPoolConfig(),shardInfos);
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            String type = shardedJedis.type("name");
            System.out.println("key name type=" + type);
            String name = shardedJedis.get("name");
            System.out.println(name);
            shardedJedis.exists("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shardedJedis.close();
        }
    }
}
