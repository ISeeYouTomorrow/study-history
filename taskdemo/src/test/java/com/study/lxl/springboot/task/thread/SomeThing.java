package com.study.lxl.springboot.task.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukas
 * @since 2019/6/6 18:58
 **/
public class SomeThing {

    private MyBuffer buffer = new MyBuffer();

    public void producer() {
        synchronized (this) {
            while (buffer.isFull()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                buffer.add();
            } catch (Exception e) {
                e.printStackTrace();
            }
            notifyAll();
        }
    }

    public void consumer() {
        synchronized (this) {
            while (buffer.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                buffer.remove();
            } catch (Exception e) {
                e.printStackTrace();
            }
            notifyAll();
        }
    }

    private int counter = 1;
    public static void main(String[] args) {
        SomeThing someThing = new SomeThing();
        final int count1 = 5;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int t = count1;
                while (t-- >0) {
                    someThing.producer();
                }
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            int t = count1;
            while (t-- >0) {
                someThing.consumer();
            }
        },"t2");

        t1.start();

        t2.start();


    }


    private class MyBuffer {

        private List<Integer> list = new ArrayList<>(1);

        public void add() throws Exception {
            if (isFull()) {
                throw new Exception("full");
            }
            if (!isFull()) {
                list.add(counter);
                System.out.println(Thread.currentThread().toString() + " add"+list.get(0));
                counter = counter+2;
            }
        }

        public Boolean isFull() {
            return list.size() == 1;
        }

        public Boolean isEmpty() {
            return list.isEmpty();
        }

        public void remove() throws Exception {
            if (isEmpty()) {
                throw new Exception("null 集合");
            }else {
                System.out.println(Thread.currentThread().toString() + " remove"+(list.get(0)+1));
                list.remove(0);
            }

        }

    }


}
