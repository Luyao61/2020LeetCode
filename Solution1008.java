import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution1008 {
    public TreeNode bstFromPreorder(int[] preorder) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = new TreeNode(preorder[0]);
        TreeNode root = node;
        stack.offerLast(node);
        for (int i = 1; i < preorder.length; i++) {
            if (preorder[i] < node.val) {
                stack.offerLast(node);
                node.left = new TreeNode(preorder[i]);
                node = node.left;
            } else {
                while (!stack.isEmpty() && stack.peekLast().val < preorder[i]) {
                    node = stack.pollLast();
                }
                node.right = new TreeNode(preorder[i]);
                node = node.right;
            }
        }
        return root;
    }
}