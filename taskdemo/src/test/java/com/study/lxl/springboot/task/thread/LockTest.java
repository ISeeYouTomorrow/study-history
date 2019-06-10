package com.study.lxl.springboot.task.thread;

/**
 * @author Lukas
 * @since 2019/6/8 20:24
 **/
public class LockTest {

    private boolean isLocked = false;
    private Thread currentThread;

    public synchronized void lock() throws InterruptedException {

        while (isLocked) {
            wait();
        }
        System.out.println (Thread.currentThread().getName()+" lock");
        isLocked = true;
        this.currentThread = Thread.currentThread();

    }

    public synchronized void unLock() throws IllegalMonitorStateException{

        if (this.currentThread != (Thread.currentThread())) {
            throw new IllegalMonitorStateException("calling thread has not locked this lock");
        }

        isLocked = false;
        this.currentThread = null;
        System.out.println (Thread.currentThread().getName()+" notify");
        notify();

    }


    public static void main(String[] args) {
        LockTest lock = new LockTest();
        for(int i=0;i<5;i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.lock();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread "+Thread.currentThread().getName());
                    lock.unLock();

                }
            });
            thread.start();
        }



    }

}
