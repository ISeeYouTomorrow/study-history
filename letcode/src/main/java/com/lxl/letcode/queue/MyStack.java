package com.lxl.letcode.queue;

import java.util.LinkedList;

/**
 * @author Lukas
 * @since 2019/6/18 9:53
 **/
class  MyStack {

    private LinkedList<Integer> list;

    /** Initialize your data structure here. */
    public MyStack() {
        list = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        list.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return list.poll();
    }

    /** Get the top element. */
    public int top() {
        return list.removeLast();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return list.isEmpty();
    }
}
