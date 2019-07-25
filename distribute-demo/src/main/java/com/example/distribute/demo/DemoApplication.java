package com.example.distribute.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RequestMapping("/demo")
@RestController
public class DemoApplication {

    @Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/lock")
    public void lock() {
        redisTemplate.opsForValue().set("test","test", 100, TimeUnit.SECONDS);
        String token = (String) redisTemplate.opsForValue().get("test");
        System.out.println("toke = "+token);
    }
}
