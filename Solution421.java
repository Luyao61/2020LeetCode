// https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
class Solution421 {
    public int findMaximumXOR(int[] nums) {
        Trie421 trie = new Trie421();
        for (int num : nums) {
            Trie421 node = trie;
            for (int i = 31; i >= 0; i--) {
                int currentBit = (num >>> i) & 1;
                if (node.children[currentBit] == null) {
                    node.children[currentBit] = new Trie421();
                }
                node = node.children[currentBit];
            }
        }

        int ans = 0;
        for (int num : nums) {
            int currentAns = 0;
            Trie421 node = trie;
            for (int i = 31; i >= 0; i--) {
                int currentBit = (num >>> i) & 1;
                if (node.children[currentBit ^ 1] != null) {
                    currentAns = currentAns + (1 << i);
                    node = node.children[currentBit ^ 1];
                } else {
                    node = node.children[currentBit];
                }
            }
            ans = Math.max(currentAns, ans);
        }
        return ans;
    }
}

class Trie421 {
    public Trie421[] children;

    public Trie421() {
        children = new Trie421[2];
    }
}