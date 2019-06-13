package com.study.lxl.springboot.task.thread;

import com.study.lxl.springboot.task.proxy.Bird;
import com.study.lxl.springboot.task.proxy.Fly;

import java.util.Random;

/**
 * @author Lukas
 * @since 2019/6/7 9:33
 **/
public class ThreadLocalTest {

    static ThreadLocal<Fly> threadLocal = new ThreadLocal();
    static Random random = new Random();
    //允许子线程访问父线程的线程本地变量
    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        inheritableThreadLocal.set("hello");
        for (int i=0;i<10;i++) {
            Thread t = new Thread(() -> {
                Fly fly = new Bird();
                ((Bird) fly).setColor(random.nextInt(255));
                ((Bird) fly).setName("name"+random.nextInt(20));

                System.out.println("子线程:"+inheritableThreadLocal.get());

                threadLocal.set(fly);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Fly f = threadLocal.get();
                System.out.println("f.color "+f.toString()+" "+f.hashCode());
            });
            t.start();
        }
    }
}
