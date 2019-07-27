package tree;

import java.util.*;

/**
 * @ClassName LevelOrderSolution
 * @Author @lvxile
 * @Date 2019/7/10 13:07
 * @Description 按层级遍历树
 */
public class LevelOrderSolution {
    public static TreeNode init1() {
        TreeNode root1 = new TreeNode(2);

        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root1.left = left;
        root1.right = right;

        return root1;
    }

    public List<List<Integer>> levelOrderByWhile(TreeNode root) {
        if (root == null) {
            return rs;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 0;
        while (!queue.isEmpty()) {
            rs.add(new ArrayList<>());
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                rs.get(index).add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            index++;
        }
        return rs;
    }

    /**
     * 使用递归调用遍历 bfss=     广度优先搜索-bfs [bread
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return rs;
        }
        levelOrderHelper(root,0);
        return rs;

    }
    private List<List<Integer>> rs = new ArrayList<>();
    private void levelOrderHelper(TreeNode root, int length) {
        if (rs.size() == length) {//临界条件，判断层级列表是否需要初始化
            rs.add(new ArrayList<>());
        }
        rs.get(length).add(root.val);

        if (root.left != null) {
            levelOrderHelper(root.left, length+1);
        }
        if (root.right != null) {
            levelOrderHelper(root.right, length+1);
        }
    }

    public static void main(String[] args) {
        TreeNode root = init1();
        LevelOrderSolution sl = new LevelOrderSolution();


        List<List<Integer>> list1 = sl.levelOrder(root);
        list1.forEach(l-> {
            l.forEach(integer ->
                    System.out.print(integer));

            System.out.println();
        });
        System.out.println("---------------------------");
        List<List<Integer>> list = sl.levelOrderByWhile(root);
        list.forEach(l-> {
            l.forEach(integer ->
                    System.out.print(integer));

            System.out.println();
        });
    }
}
