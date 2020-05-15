class Trie<T> {
    private T value;
    private final int DEFAULT_SIZE = 26;
    private Trie<T>[] children;
    private boolean isLeaf;
    private int size = -1;

    /** Initialize your data structure here. */
    public Trie() {
        this.children = new Trie[DEFAULT_SIZE];
    }

    /** Initialize your data structure here. */
    public Trie(int size) {
        this.size = size;
        this.children = new Trie[this.size];
    }

    public Trie<T>[] getChildren() {
        return this.children;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public Trie<T> get(int index) {
        return this.children[index];
    }

    public void putIfAbsent(int index) {
        if (this.children[index] == null) {
            this.children[index] = new Trie<T>(this.size == -1 ? DEFAULT_SIZE : this.size);
        }
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */