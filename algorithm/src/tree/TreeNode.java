package tree;

public class TreeNode {

    public TreeNode left;

    public TreeNode right;

    public int val;

    public TreeNode(int i) {
        this.val = i;
    }

    @Override
    public String toString() {
        return "left:"+(left==null?"":left.val)+" root: "+val+" right:"+(right==null?"":right.val);
    }

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
}
