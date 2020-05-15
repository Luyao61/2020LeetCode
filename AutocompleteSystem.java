import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/design-search-autocomplete-system/

class AutocompleteSystem {
    private Trie<Integer> root;
    private final int TOP = 3;
    private Trie<Integer> node;
    private StringBuilder sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        if (sentences == null || times == null || sentences.length != times.length) {
            return;
        }
        this.sb = new StringBuilder();
        this.root = new Trie<Integer>(27);
        for (int i = 0; i < sentences.length; i++) {
            String str = sentences[i];
            int count = times[i];
            Trie<Integer> currentNode = root;
            for (char c : str.toCharArray()) {
                currentNode.putIfAbsent(getIndex(c));
                currentNode = currentNode.get(getIndex(c));
            }
            currentNode.setIsLeaf(true);
            currentNode.setValue(count);
        }
        this.node = this.root;
    }

    public List<String> input(char c) {
        PriorityQueue<StringWithCount> topResults = new PriorityQueue<>(TOP + 1, new Comparator<StringWithCount>() {
            public int compare(StringWithCount a, StringWithCount b) {
                return a.c == b.c ? b.s.compareTo(a.s) : a.c - b.c;
            }
        });
        if (c == '#') {
            node.setIsLeaf(true);
            node.setValue(node.getValue() == null ? 1 :node.getValue() + 1);
            node = this.root;
            sb = new StringBuilder();
        } else {
            sb.append(c);
            int index = getIndex(c);
            node.putIfAbsent(index);
            node = node.get(index);
            dfs(node, sb, topResults);
        }
        List<String> ans = new ArrayList<String>(TOP);
        while (!topResults.isEmpty()) {
            ans.add(topResults.poll().s);
        }
        Collections.reverse(ans);
        return ans;
    }

    private void dfs(Trie<Integer> node, StringBuilder sb, PriorityQueue<StringWithCount> topResults) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            topResults.offer(new StringWithCount(sb.toString(), (int) node.getValue()));
            if (topResults.size() > TOP) {
                topResults.poll();
            }
        }

        for (int i = 0; i < node.getChildren().length; i++) {
            sb.append(getChar(i));
            dfs(node.get(i), sb, topResults);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private int getIndex(char c) {
        if (c == ' ') {
            return 0;
        }
        return (int) (c - 'a') + 1;
    }

    private char getChar(int i) {
        if (i == 0) {
            return ' ';
        }
        return (char) ('a' + (i - 1));
    }
}

class StringWithCount {
    public String s;
    public int c;

    public StringWithCount(String s, int c) {
        this.s = s;
        this.c = c;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */