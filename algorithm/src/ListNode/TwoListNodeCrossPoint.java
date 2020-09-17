package ListNode;

/**
 * 查找链表相交的节点
 */
public class TwoListNodeCrossPoint {

    private static ListNode node1, node2;

    static {
        ListNode head6 = new ListNode(6);
        ListNode head5 = new ListNode(5);
        ListNode head4 = new ListNode(4);
        ListNode head3 = new ListNode(3);
        ListNode head2 = new ListNode(2);
        ListNode head1 = new ListNode(1);

        head5.next = head6;
        head4.next = head5;
        head3.next = head4;
        head2.next = head3;
        head1.next = head2;

        node1 = head1;
        node2 = new ListNode(9);
        node2.next = head4;

    }

    public ListNode findCrossNode(ListNode h1, ListNode h2) {
        int len1 = 0, len2 = 0;
        ListNode n1 = h1 , n2 = h2;
        while (n1 != null) {
            System.out.print(n1.val+"->");
            len1++;
            n1 = n1.next;
        }
        System.out.println();
        while (n2 != null) {
            System.out.print(n2.val+"->");
            len2++;
            n2 = n2.next;
        }
        System.out.println();
        System.out.println("h1 长度 = "+len1);
        System.out.println("h2 长度 = "+len2);

        int len = Math.abs(len1 - len2);
        if (len1 > len2) {
            int i = 1;
            while (i++ <= len) {
                h1 = h1.next;
            }
        }
        if (len2 > len1) {
            int i=1;
            while (i++ <= len) {
                h2 = h2.next;
            }
        }
        while ((h1 = h1.next) != null && (h2 = h2.next) != null) {
            if (h1.val == h2.val) {
                return h1;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        TwoListNodeCrossPoint point = new TwoListNodeCrossPoint();
        ListNode crossNode = point.findCrossNode(node1, node2);
        System.out.println(crossNode.val);
    }

}
