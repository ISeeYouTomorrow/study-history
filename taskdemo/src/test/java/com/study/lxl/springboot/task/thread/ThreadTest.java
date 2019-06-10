package com.study.lxl.springboot.task.thread;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lukas
 * @since 2019/5/30 23:53
 **/
public class ThreadTest {

    volatile Integer result = null;
    @Test
    public void start() {

        startApi1();
        startApi2();
        startApi3();
        while (true) {
            if( result != null) {
                System.out.println("结果 ==== " + result);
                break;
            }
        }
    }

    public int getRandom() {
        return (int) (Math.random()*1000);
    }


    public void startApi1() {
        new Thread(() -> {
            try {
//                Thread.sleep(getRandom());
                Thread.sleep(50);
                System.out.println("api ------ 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = 10;
        }).start();
    }

    public void startApi2() {
        new Thread(() -> {
            try {
//                Thread.sleep(getRandom());
                Thread.sleep(60);
                System.out.println("api ------ 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = 20;
        }).start();
    }

    public void startApi3() {
        new Thread(() -> {
            try {
//                Thread.sleep(getRandom());
                Thread.sleep(70);
                System.out.println("api ------ 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = 30;
        }).start();
    }

    @Test
    public void test100Threads() {

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i=0;i<100;i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000*60);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private Object object = new Object();

    @Test
    public void testTwoThread() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (object) {
                while (flag) {
                    System.out.println("t1 "+(2*n-1));
                    try {
                        flag = false;
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (object) {
                while (!flag) {
                    System.out.println("t2 "+(2*n));
                    n++;
                    try {
                        flag = true;
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }


    @Test
    public void testMultiThread() {
        Thread t1 = new Thread(() -> {
            synchronized (object) {//同步代码获取对象锁
                System.out.println("t1 获得锁");
                while (flag) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1 "+(2*n-1));
                    flag = false;
                    System.out.println("t1 before notify ");
                    //唤醒在此对象监视器上等待的单个线程， 如果存在多个等待线程，则会选择唤醒其中一个线程。线程通过调用wait方法，在对象的监视器上等待。
                    //直到当前线程放弃此对象上的锁定，才能继续执行被唤醒的线程。被唤醒的线程将以常规方式与在该对象上主动同步的其他线程进行竞争。
                    //notify方法只应由作为此对象监视器的所有者的线程来调用。即notify方法必须在synchronized方法或者代码块里执行
                    object.notify();
                    System.out.println("t1 after notify,before wait ");
                    try {
                        //wait方法导致当前线程将其自身放置在对象的等待集中，然后放弃此对象上的所有同步要求(释放锁资源)，直到存在其他线程调用此对象的notify/notifyAll方法
                        //notify等待方法应总是发生在循环中。方法只应由作为此对象监视器的所有者的线程来调用。即wait方法必须在synchronized方法或者代码块里调用。
                        object.wait();
                        System.out.println("t1 after wait ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (object) {
                System.out.println("t2 获取锁");
                while (!flag) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t2 "+(2*n));
                    n++;
                    flag = true;
                    object.notify();
                    System.out.println("t2 after notify,before wait ");
                    try {
                        object.wait();
                        System.out.println("t2 after wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }



    private int n = 1;
    Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private boolean flag = true;
    //多线程轮流执行 condition 实现
    @Test
    public void testLock() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("-----t1获取锁-----");
                while (flag) {
                    try {
                        System.out.println("------t1执行-------");
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " " + (2 * n - 1));
                        System.out.println("c1 before --- await");
                        flag = false;
                        c1.await();//执行等待方法，本线程被唤醒后会从此处继续执行
                        System.out.println("c1 after --- await");
                        System.out.println("c2 --- signal");
                        c2.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("-----t2获取锁-----");
                while (!flag) {
                    try {
                        System.out.println("------t2执行-------");
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " " + 2 * n);
                        n++;
                        System.out.println("c1 --- signal");
                        flag = true;
                        c1.signal();
                        System.out.println("c2 before --- await");
                        c2.await();
                        System.out.println("c2 after--- await");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                lock.unlock();
            }

        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

}
