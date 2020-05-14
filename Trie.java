class TrieNode {
    private final int SIZE = 26;
    private TrieNode[] children;

    private boolean isLeaf;

    public TrieNode() {
        this.children = new TrieNode[SIZE];
    }

    public TrieNode get(char c) {
        return this.children[c - 'a'];
    }

    public void putIfAbsent(char c) {
        if (this.children[c - 'a'] == null) {
            this.children[c - 'a'] = new TrieNode();
        }
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }
}

class Trie {
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = this.root;
        for (char c : word.toCharArray()) {
            node.putIfAbsent(c);
            node = node.get(c);
        }
        node.setIsLeaf(true);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = this.root;
        for (char c : word.toCharArray()) {
            node = node.get(c);
            if (node == null) {
                return false;
            }
        }
        return node.isLeaf();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = this.root;
        for (char c : prefix.toCharArray()) {
            node = node.get(c);
            if (node == null) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */