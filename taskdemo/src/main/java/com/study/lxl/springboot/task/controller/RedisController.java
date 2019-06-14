package com.study.lxl.springboot.task.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * redis分布式锁实例
 * @author Lukas
 * @since 2019/6/14 9:24
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String LOCK_KEY = "good_info";
    private AtomicInteger count = new AtomicInteger(100);

    @Bean
    public RedissonClient redissionConfig() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        config.useClusterServers().addNodeAddress("redis://127.0.0.1:7181");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

    @RequestMapping("/lock")
    @ResponseBody
    public String testRedis() {
        RLock rLock = redissonClient.getLock(LOCK_KEY);
        try {
            rLock.lock();//加锁
            Integer val = (Integer) redisTemplate.opsForValue().get("product_total_count");
            if (val <= 0) {
                System.out.println("库存为0");
                return "下单失败，库存已经没有";
            }
            long result = redisTemplate.opsForValue().decrement("product_total_count");
            /**
             * 加锁2
             * 自动释放锁
             */
//            rLock.lock(10, TimeUnit.SECONDS);
            //加锁3
//            if(rLock.tryLock()){
//                // to do ..
//            }
//            Thread.sleep(1000L);

//            int result = count.decrementAndGet();
            if (result <= 0) {
                System.out.println("库存扣减失败!");
            }else {
                System.out.println("剩余商品个数: "+result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }

        return "success";
    }


}
