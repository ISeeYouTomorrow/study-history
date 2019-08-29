package ListNode;

import tree.TreeNode;

import java.util.*;

/**
 * @ClassName DeleteNodeSolution
 * @Author @lvxile
 * @Date 2019/7/11 10:22
 * @Description 删除链表指定节点
 */
public class DeleteNodeSolution {
    /**
     * @param node 需要删除的节点node
     */
    public void deleteNode(ListNode node) {
//        ListNode first = new ListNode(-1);
//        first.next = node;
//        if (node == null) {
//            return ;
//        }
//        while (first.next != null) {
//            if (first.next.val == )
//        }
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 删除链表指定的元素
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode(-1);
        first.next = head;
        ListNode current = first;
        while (current.next != null){
            if (current.next.val == val) {
                current.next = current.next.next;
            }else {
                current = current.next;
            }
        }
        return first.next;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverseElements(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null ,current = null;
        while (head != null) {
            current = head;
            head = head.next;
            current.next = pre;
            pre = current;
        }
        return current;
    }

    /**
     * 判断是否存在环形链表
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针判断是否环形链表
     * @param head
     * @return
     */
    public boolean hasCycleFast(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left =0, right = nums.length-1;
        int mid;
        while (left<=right) {
            mid = (left +right)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid]<target) {
                left = mid +1;
            }
            if (nums[mid]>target) {
                right = mid -1;
            }
        }
        return -1;
    }

    /**
     * 第n个斐波那契数
     *
     * @param n
     * @return
     */
    private Map<Integer, Integer> fabMap = new HashMap<>();
    public int fib(int n) {
        if (n <= 1){
            return n;
        }else {
            if (fabMap.containsKey(n)) {
                return fabMap.get(n);
            }else{
                int temp = fib(n - 1) + fib(n - 2);
                fabMap.put(n, temp);
                return temp;
            }
        }
    }

    /**
     * 求第n个斐波那契数
     * @param N
     * @return
     */
    public int fibFast(int N) {
        int one = 0;
        int two = 1;
        int sum = 0;
        int i = 2;
        if (N < 2) {
            return N;
        }
        while (i <= N) {
            sum = one+two;
            one = two;
            two = sum;
            i++;
        }
        return sum;
    }

    /**
     * 位运算 - 位1的个数
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int i = 0;
        while (n != 0) {
            n = n&n-1;
            i++;
        }
        return i;
    }

    /**
     * 二叉树的前序遍历
     */
    List<Integer> rs = new ArrayList();
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return rs;
        }
        rs.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return rs;
    }

    /**
     * 二叉树的中序遍历
     * @param root
     * @return
     */
    public List<Integer> middleorderTraversal(TreeNode root) {
        if (root == null) {
            return rs;
        }
        middleorderTraversal(root.left);
        rs.add(root.val);
        middleorderTraversal(root.right);
        return rs;
    }

    /**
     * 判断是否非法二叉树
     *
     * @param root
     * @return
     */
    private double last = Double.MAX_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        int v = root.val;
        boolean left = isValidBST(root.left);
        boolean mid = v<last;
        boolean right = isValidBST(root.right);
        last = v;
        return left && mid && right;
    }


    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        int v = root.val;
        boolean left = isValidBST2(root.left);
        if (left){
            if (v<last){
                last = v;
                if (isValidBST2(root.right)){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 二叉树层次遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null)
            return ts;
        orderHelper(root, 0);
        return ts;
    }
    List<List<Integer>> ts = new ArrayList();
    private void orderHelper(TreeNode root,int dep) {
        if(rs.size() == dep) {
            ts.add(new ArrayList());
        }
        ts.get(dep).add(root.val);
        if(root.left != null) {
            orderHelper(root.left, dep+1);
        }
        if(root.right != null) {
            orderHelper(root.right, dep+1);
        }
    }

}
