package com.study.lxl.springboot.task.linkednode;

import org.junit.Test;

/**
 * @author Lukas
 * @since 2019/5/30 16:00
 **/
public class LinkNodeTest {
    private static LinkNode head = initNode();

    @Test
    public void test() {
//        testRemove(head, 6);

//        testDelete(head, 1);
        trimNode(head);

        LinkNode n = reverseNode(head);

        trimNode(n);
    }

    public void testRemove(LinkNode top, int val) {

        LinkNode header = new LinkNode();
        header.value = -1;
        header.next = top;
        LinkNode cur = header;//当前的节点指针

        while (cur.next != null) {
            if (cur.next.value == val) {
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        trimNode(header.next);
    }


    public void testDelete(LinkNode top, int val) {
        LinkNode cur = top;
        while (cur != null && cur.next != null) {
            if (cur.next.value == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        if (top != null && top.value == val) {
            top = top.next;
        }

        trimNode(top);
    }

    public static void trimNode(LinkNode top) {
        System.out.println();
        while (top!=null) {
            System.out.print(top.value+" ");
            top = top.next;
        }
        System.out.println();
    }

    public static LinkNode reverseNode(LinkNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkNode cur = null;
        LinkNode pre = null;
        while (head != null) {
            cur = head;
            head = head.next;
            cur.next = pre;
            pre = cur;
        }
        return cur;
    }



    public static LinkNode initNode(){
        LinkNode node1 = new LinkNode();
        node1.value = (1);

        LinkNode node2 = new LinkNode();
        node2.value =(2);

        LinkNode node3 = new LinkNode();
        node3.value =(1);

        LinkNode node4 = new LinkNode();
        node4.value =(4);

        LinkNode node5 = new LinkNode();
        node5.value =(2);

        LinkNode node6 = new LinkNode();
        node6.value =(6);

        node5.next = (node6);
        node4.next = (node5);
        node3.next = (node4);
        node2.next = (node3);
        node1.next = (node2);

        return node1;
    }

}
