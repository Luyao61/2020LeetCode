import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution1339 {
    private List<Integer> sums;

    public int maxProduct(TreeNode root) {
        sums = new LinkedList<>();

        int sum = getSumOfSubTree(root);

        long p = 0;
        for (long s : sums) {
            if ((sum - s) * s > p) {
                p = (sum - s) * s;
            }
        }
        int m = 10 ^ 9 + 7;
        return (int) p % m;
    }

    public int getSumOfSubTree(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getSumOfSubTree(node.left);
        int right = getSumOfSubTree(node.right);
        int sum = left + right + node.val;
        this.sums.add(sum);
        return sum;
    }
}