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
}
