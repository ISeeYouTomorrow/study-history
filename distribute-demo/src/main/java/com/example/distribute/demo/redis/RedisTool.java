package com.example.distribute.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @ClassName RedisTool
 * @Author @lvxile
 * @Date 2019/7/18 9:38
 * @Description TODO
 */
public class RedisTool {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;


    /**
     * 获取分布式锁
     * Redis 从2.6.12版本开始 set 命令支持 NX 、 PX 这些参数来达到 setnx 、 setex 、 psetex 命令的效果，
     * @param jedis
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 在eval命令执行Lua代码的时候，Lua代码将被当成一个命令去执行，并且直到eval命令执行完成，Redis才会执行其他命令。
     * @param jedis
     * @param lockKey
     * @param requestId
     * @return
     */
    public static boolean releaseLock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

}
