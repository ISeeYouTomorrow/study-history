package com.test.concurrent.study.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskPoolExecutorTest {

    public void test() {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue();


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,1000,blockingQueue);



    }

}
