package ListNode;

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
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
