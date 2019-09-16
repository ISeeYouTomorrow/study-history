package com.lxl.letcode.linklist;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Lukas
 * @since 2019/6/14 14:18
 **/
@Slf4j
public class LinkListTest {

    private ListNode head;

    @BeforeClass
    public void initListNode() {
        head = new ListNode(1);

        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node3.setNext(node4);
        node2.setNext(node3);
        head.setNext(node2);


        traceListNode(head);
        System.out.println("----------------------beforeclass--------------");
    }

    public void traceListNode(ListNode node) {
        if (node == null) {
            return;
        }
        System.out.println();
        while(node != null ) {
            System.out.print(node.toString()+" ");
            node = node.next;
        }
        System.out.println();
    }

    @Test
    public void traceHead() {

    }

    public ListNode remove(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode(-1);
        first.next = head;
        ListNode cur = first.next;
        while(cur.next != null) {
            if (cur.next.var == val) {
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return first.next;
    }

    @Test
    public void reverseLink() {
        ListNode header = head;

        ListNode pre = null, current = null;

       while (header != null) {
           current = header;
           header = header.next;
           current.next = pre;
           pre = current;
       }

        traceListNode(current);

    }

    private ListNode convertArrry2ListNode(int[] a) {
        ListNode header = new ListNode(-1);
        ListNode temp = header;
        for (int i: a
             ) {
            ListNode node = new ListNode(i);
            temp.next = node;
            temp = temp.next;
        }
        return header.next;
    }

    @Test
    public void testAddTwoNumbers() {
        int[] a1 = {1};
        int[] a2 = {9,9};

        ListNode l1 = convertArrry2ListNode(a1);
        ListNode l2 = convertArrry2ListNode(a2);
        traceListNode(l1);
        traceListNode(l2);

        ListNode l3 = addTwoNumbers(l1,l2);
        traceListNode(l3);
    }


    public ListNode addTwoNumbers(ListNode p1, ListNode p2) {
        ListNode l3 = new ListNode(-1);
        ListNode header = l3;
        int temp = 0, opt = 0;

        while(p1 != null || p2 != null) {
            temp = (p1==null?0:p1.var) + (p2==null?0:p2.var)+opt;
            ListNode cur;
            if(temp<10) {
                cur = new ListNode(temp);
                opt = 0;
            }else{
                cur = new ListNode(temp%10);
                opt = 1;
            }
            l3.next = cur;
            l3 = cur;
            if(p1 != null)
                p1 = p1.next;
            if(p2 != null)
                p2 = p2.next;
        }
        if(opt == 1) {
            l3.next = new ListNode(opt);
        }
        return header.next;
    }



}

@Data
class ListNode {
    int var;
    ListNode next;

    ListNode(int v) {
        this.var = v;
    }

    @Override
    public String toString() {
        return String.valueOf(var);
    }
}
