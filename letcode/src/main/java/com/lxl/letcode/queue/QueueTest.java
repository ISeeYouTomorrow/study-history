package com.lxl.letcode.queue;

import org.testng.annotations.Test;

import java.util.PriorityQueue;

/**
 * @author Lukas
 * @since 2019/6/18 9:06
 **/
public class QueueTest {


    @Test
    public void test() {
        int[] args = {1,9,2,7,6,3,10,19,200,11,4,15};

        Object[] maxK = testPriorityQueue(args,3);
        for (Object o : maxK) {
            System.out.print(o +" ");
        }

    }

    /**
     * 从输入的n个数里获取topk
     * @param args
     * @param size
     * @return
     */
    public Object[] testPriorityQueue(int[] args, int size) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(size);
        if (args == null || args.length == 0)
            return null;
        for (int arg : args) {
            if (pq.size() > size) {
                if (pq.peek() < arg) {
                    pq.poll();
                    pq.add(arg);
                }
            }else {
                pq.add(arg);
            }
        }

        return pq.toArray();
    }




}
