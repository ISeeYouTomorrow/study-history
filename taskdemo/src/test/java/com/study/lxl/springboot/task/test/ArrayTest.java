package com.study.lxl.springboot.task.test;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lukas
 * @since 2019/6/4 23:02
 **/
public class ArrayTest {

    @Test
    public void testArraySort() {
        int [] a = {1,9,2,7,8,4};
        Arrays.sort(a);
        System.out.println(a[a.length-2]);

        int[] b = {3,2,1,5,19};

        int[] c = {2,4,9,2,11};

        testSort(a,b,c);



    }

    private Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };



    public void testSort(int[]... args ) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] arg : args) {
            for (int temp :arg ) {
                set.add(temp);
            }
        }
        Integer[] result = new Integer[set.size()];
        set.toArray(result);
        for (Integer integer : result) {
            System.out.println(integer+" ");
        }

    }

    class Node {
        int v;
        Node left;
        Node right;

        public Node(int v) {
            this.v = v;
        }
    }

    @Test
    public void testNode() {

        Node n1 = new Node(1);

        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        Node n5 = new Node(5);

        Node n6 = new Node(6);

        Node n7 = new Node(7);


        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n1.left = n2;
        n1.right = n3;

        zagZig(n1);

        list.stream().forEach(n-> System.out.println(n));

    }

    private List<Integer> list = new ArrayList<>();

    public void zagZig(Node root) {
        list.add(root.v);
        Node left = root.left;
        Node right = root.right;

        if (right != null) {
            zagZig(right);
        }
        if (left != null) {
            zagZig(left);
        }
        return;
    }

    private volatile int n = 1;

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    @Test
    public void testObject() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock2) {
                while (true) {
                   System.out.println(Thread.currentThread().getName()+" "+ (n*2-1));
                   lock2.notify();
                   try {
                       lock2.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {//必须在synchonized代码里操作对象的wait/notify方法
                while (true) {
                    System.out.println(Thread.currentThread().getName()+" "+ (n*2));
                    n++;
                    lock2.notify();
                    try {
                        lock2.wait();
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



    //多线程轮流执行 synchorized 实现
    @Test
    public void testTrace() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (lock1){
                    try {
                        Thread.sleep(1000);
                        System.out.println(2 * n - 1);
                        lock1.notify();
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while(true) {
                synchronized (lock1){
                    try {
                        Thread.sleep(1000);
                        System.out.println(2 * n);
                        n = n + 1;
                        lock1.notify();
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();

        Thread.sleep(1000);
        t2.start();

        t1.join();
        t2.join();
    }


}
