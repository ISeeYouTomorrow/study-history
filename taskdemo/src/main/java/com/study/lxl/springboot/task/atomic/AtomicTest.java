package com.study.lxl.springboot.task.atomic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicTest
 * @Author @lvxile
 * @Date 2019/7/26 17:46
 * @Description TODO
 */
public class AtomicTest {

    private AtomicInteger ai = new AtomicInteger(0);
    @Test
    public void testA() {
        for (int i=0;i<20;i++) {
            new Thread(() -> System.out.println(Thread.currentThread().getName()+" "+ai.getAndIncrement())).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" result ----- "+ ai.get());
    }

    /**
     * CompletableFuture 使用
     */
    @Test
    public void futureTest() {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(()->{
            System.out.println("T1: 洗水壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T1: 烧开水......");
            sleep(15, TimeUnit.SECONDS);
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2: 洗茶壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T2: 洗茶杯...");
            sleep(2, TimeUnit.SECONDS);

            System.out.println("T2: 取茶叶...");
            sleep(1, TimeUnit.SECONDS);

            return "龙井茶";
        });

        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) ->{
            System.out.println("T1: 拿到茶叶: "+tf);

            System.out.println("T1: 泡茶...");

            return "上茶:"+tf;
        });

        System.out.println(f3.join());
    }

    private void sleep(int i, TimeUnit seconds) {
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试CompletionService
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void completionServiceTest() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(service);

        cs.submit(() -> getPrice1());//任务1
        cs.submit(() -> getPrice2());//任务2
        cs.submit(() -> getPrice3());//任务3

        for (int i = 0; i < 3; i++) {
            Integer r = cs.take().get();
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("save to db : "+r);
                }
            });
        }
    }

    /**
     * foring 任意返回结果即可
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void completionServiceAnyTest() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(service);
        List<Future<Integer>> futures = new ArrayList<>(3);
        futures.add(cs.submit(() -> getPrice1()));
        futures.add(cs.submit(() -> getPrice2()));
        futures.add(cs.submit(() -> getPrice3()));

        try {
            for (int i = 0; i < 3; i++) {
                Integer r = cs.take().get();
                if (r != null){
                    System.out.println("返回结果: "+r);
                    break;
                }
            }
        }finally {
            futures.forEach(fu ->
                    fu.cancel(true));
        }
    }


    private Integer getPrice1() {
        sleep(4, TimeUnit.SECONDS);
        return 10;
    }

    private Integer getPrice2() {
        sleep(2, TimeUnit.SECONDS);
        return 20;
    }

    private Integer getPrice3() {
        sleep(3, TimeUnit.SECONDS);
        return 30;
    }
}
