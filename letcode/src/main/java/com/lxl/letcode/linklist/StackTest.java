package com.lxl.letcode.linklist;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Stack;

/**
 * @author Lukas
 * @since 2019/6/17 9:15
 **/
public class StackTest {

    public static void main(String[] args) {
        System.out.println(isValid("([)](){[]}"));
    }

    /**
     * 判断一个只包含(){}[]的字符串是否合法
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Character temp ;
        for(int i=0;i<s.length();i++) {
            Character ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);//入栈
            }else {
                if (stack.isEmpty()) {
                    return false;
                }
                temp = stack.pop();//出栈且判断是否与当前字符匹配
                if (ch == ')' && temp != '(') {
                    return false;
                }
                if (ch == ']' && temp != '[') {
                    return false;
                }
                if (ch == '}' && temp != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public int[] stack2QueueParamater() {
        return new int[]{1,2,3,4,5};
    }

    @Test
    public void testStack2Queue() {
        int args[] = stack2QueueParamater();
        Stack<Integer> input = new Stack<>();
        Stack<Integer> ouput = new Stack<>();

        for (int arg : args) {
            input.push(arg);
        }

        while (!input.isEmpty()) {
            ouput.push(input.pop());
        }

        while (!ouput.isEmpty()) {
            System.out.println(ouput.pop());
        }
    }

}
