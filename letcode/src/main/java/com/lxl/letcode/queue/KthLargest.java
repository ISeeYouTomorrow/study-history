package com.lxl.letcode.queue;

import java.util.PriorityQueue;

/**
 * @author Lukas
 * @since 2019/6/18 10:07
 **/
class KthLargest {
    private PriorityQueue<Integer> queue = new PriorityQueue<>();
    private int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        if (nums==null || nums.length == 0) {
            return ;
        }
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (queue.size() < k) {
            queue.add(val);
        }else if (queue.size() >= k) {
            if (queue.peek() < val) {
                queue.poll();
                queue.add(val);
            }
        }
        return queue.peek();
    }

    private void trace() {
        Object[] objects = queue.toArray();
        for (Object object : objects) {
            System.out.print(object+" ");
        }
    }

    public static void main(String[] args) {
        KthLargest kth = new KthLargest(3,new int[]{4,5,8,2});
        int l = kth.add(3);
        System.out.println(l);

        kth.trace();
    }
}

