package ListNode;

/**
 * @ClassName TwoNumberSolution
 * @Author @lvxile
 * @Date 2019/7/11 9:44
 * @Description 两数之和
 */
public class TwoNumberSolution {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(8);
        l2.next = new ListNode(9);

        TwoNumberSolution solution = new TwoNumberSolution();
        solution.trace(l1);
        solution.trace(l2);


        ListNode l3 = solution.addTwoNumbers(l1, l2);

        solution.trace(l3);
    }

    public void trace(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val+" ");
            listNode = listNode.next;
        }
        System.out.println();
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        ListNode head = l3;
        if (l1 == null && l2 == null) {
            return null;
        }
        int pre = 0, cur = 0;

        while (l1 != null || l2 != null) {
            int n1 = l1==null?0:l1.val;
            int n2 = l2==null?0:l2.val;
            ListNode temp ;
            cur = n1+n2+pre;
            if (cur >= 10) {
                cur = cur%10;
                pre = 1;
            }else{
                pre = 0;
            }
            temp = new ListNode(cur);
            head.next = temp;
            head = temp;
            if (l1!=null) {
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }
        }
        if (pre == 1) {
            head.next = new ListNode(pre);
        }
        return l3.next;
    }

}
