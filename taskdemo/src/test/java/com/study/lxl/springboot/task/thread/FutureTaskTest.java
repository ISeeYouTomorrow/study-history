package com.study.lxl.springboot.task.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Lukas
 * @since 2019/5/31 0:56
 **/
public class FutureTaskTest {
    Random random = new Random();


    public int getRandom(int bound) {
        return random.nextInt(bound);
    }

    @Test
    public void traceResult() {
        try {
            int result = test();
            System.out.println("result ------> "+result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Integer test() throws ExecutionException, InterruptedException {
        Callable<Integer> c1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return test1();
            }
        };

        Callable<Integer> c2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return test2();
            }
        };

        Callable<Integer> c3 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return test3();
            }
        };

        List<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(c1);
        tasks.add(c2);
        tasks.add(c3);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        Integer result = executor.invokeAny(tasks);
        executor.shutdown();
        return result;
    }

    public int test1(){
        int bound = (int)(Math.random()*10000);
        bound = getRandom(bound);
        try {
            Thread.sleep(getRandom(bound));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("方法test1执行耗时: "+bound);
        return bound;

    }

    public int test2() {
        int bound = (int)(Math.random()*10000);
        bound = getRandom(bound);
        try {
            Thread.sleep(getRandom(bound));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("方法test2执行耗时: "+bound);
        return bound;
    }


    public int test3() {
        int bound = (int)(Math.random()*10000);
        bound = getRandom(bound);
        try {
            Thread.sleep(getRandom(bound));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("方法test3执行耗时: "+bound);
        return bound;
    }


}
