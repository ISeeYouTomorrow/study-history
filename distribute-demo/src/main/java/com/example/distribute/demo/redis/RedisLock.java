package com.example.distribute.demo.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisCommands;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName RedisLock
 * @Author @lvxile
 * @Date 2019/7/17 15:21
 * @Description TODO
 */
public class RedisLock implements Lock {

    private RedisTemplate redisTemplate;

    private String lockKey;

    public RedisLock(RedisTemplate redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
    }

    public void sleepBySencond(int sencond){
        try {
            Thread.sleep(sencond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        final String key = "lock_key";
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(key, "锁定的资源", "NX", "PX", 3000);
            }
        });
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
