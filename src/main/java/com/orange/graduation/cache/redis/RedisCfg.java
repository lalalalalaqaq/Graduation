package com.orange.graduation.cache.redis;

import com.alibaba.fastjson2.JSON;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.script.DigestUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author orange.zhang
 * @date 2022/11/20 01:15
 */
@Configuration
public class RedisCfg {


    @Bean
    public JedisPool jedisPoolCfg() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20); // max conn
        config.setMaxIdle(10);  // max free conn
        config.setMinIdle(5);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        return new JedisPool(config,"43.138.217.93",6379);
    }


}
