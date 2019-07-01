package tree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {

    public static TreeNode init1() {
        TreeNode root1 = new TreeNode(2);

        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root1.left = left;
        root1.right = right;

        return root1;
    }

    public static TreeNode init2() {
        TreeNode root2 = new TreeNode(6);

        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(7);
        root2.left = left;
        root2.right = right;
        return root2;
    }

    public static TreeNode initRoot() {
        TreeNode root = new TreeNode(4);

        root.left = init1();
        root.right = init2();
        return root;
    }


    /**
     * 是否合法的二叉搜索树
     * 左子树的所有元素小于根，右子树的所有元素大于根
     */
    static double last = -Double.MAX_VALUE;// 保存已比较的节点中最大的数
    public static boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        int v = root.val;
//        boolean l = isValidBST(left);
//        boolean mid = last<v;
//        last = v;
//        boolean r = isValidBST(right);
//        return l&mid&r;

         if(isValidBST(left)) {
             if(last < v) {
                 last = v;
                 return isValidBST(right);
             }
         }
         return false;
    }

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    private static List<Integer> list = new ArrayList<>();
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode l = root.left;
        if (l != null)
            inorderTraversal(l);
        list.add(root.val);
        TreeNode r = root.right;
        if (r != null)
            inorderTraversal(r);
        return list;
    }

    /**
     *         List < Integer > res = new ArrayList < > ();
     *         Stack < TreeNode > stack = new Stack < > ();
     *         TreeNode curr = root;
     *         while (curr != null || !stack.isEmpty()) {
     *             while (curr != null) {
     *                 stack.push(curr);
     *                 curr = curr.left;
     *             }
     *             curr = stack.pop();
     *             res.add(curr.val);
     *             curr = curr.right;
     *         }
     *         return res;
     *
     * 栈的实现 中序遍历
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        while (current != null && !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            res.add(current.val);
            current = current.right;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(isValidBST(initRoot()));

        inorderTraversal(initRoot());

        list.forEach(r -> System.out.println(r));
    }
}
