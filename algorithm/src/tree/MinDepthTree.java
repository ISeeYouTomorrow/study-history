package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName MinDepthTree
 * @Author @lvxile
 * @Date 2019/7/27 16:07
 * @Description TODO
 */
public class MinDepthTree {

    public static void main(String[] args) {
        MinDepthTree depth = new MinDepthTree();
        TreeNode root = TreeNode.initRoot();
        System.out.println("min = "+depth.minDepth(root));
    }

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        int min = dfsSolution(root, 1);
        return min;
    }

    private int dfsSolution(TreeNode root, int level) {
        if (root.left == null && root.right == null) {
            return level;
        }
        if (root.left == null) {
            return dfsSolution(root.right, level + 1);
        }
        if (root.right == null) {
            return dfsSolution(root.left, level + 1);
        }
        return Math.min(dfsSolution(root.left,level+1),dfsSolution(root.right,level+1));
    }

    private int bfsSolution(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return 0;
        }

        queue.offer(root);
        int level = 1;
        TreeNode current;
        while (!queue.isEmpty()) {
            int levelNum = queue.size(); // 当前层元素的个数
            for (int i = 0; i < levelNum; i++) {//按层进行遍历
                current = queue.poll();
                if (current != null) {
                    if (current.left == null && current.right == null) {
                        return level;
                    }
                    if (current.left != null) {
                        queue.offer(current.left);
                    }
                    if (current.right != null) {
                        queue.offer(current.right);
                    }
                }
            }
            level++;
        }
        return level;
    }


}
