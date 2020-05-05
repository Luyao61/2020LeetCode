import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution1202 implements LeetcodeSolution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs == null || pairs.size() <= 0) {
            return s;
        }
        int[] parent = new int[s.length()];
        int[] rank = new int[s.length()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (List<Integer> pair : pairs) {
            this._union(parent, rank, pair.get(0), pair.get(1));
        }
        Map<Integer, PriorityQueue<Character>> map = new HashMap<Integer, PriorityQueue<Character>>();
        for (int i = 0; i < s.length(); i++) {
            char e = s.charAt(i);
            int root = this._find(parent, i);
            map.putIfAbsent(root, new PriorityQueue<Character>());
            map.get(root).offer(e);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            Character e = map.get(this._find(parent, i)).poll();
            sb.append(e);
        }
        return sb.toString();
    }

    private int _find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = this._find(parent, parent[index]);
        }
        return parent[index];
    }

    private void _union(int[] parent, int[] rank, int i, int j) {
        int root_i = _find(parent, i);
        int root_j = _find(parent, j);
        if (rank[root_i] < rank[root_j]) {
            parent[root_i] = root_j;
        } else if (rank[root_i] > rank[root_j]) {
            parent[root_j] = root_i;
        } else {
            parent[root_i] = root_j;
            rank[root_j]++;
        }
    }

    public void test() {
        List<List<Integer>> pairs = new LinkedList<>();
        pairs.add(new LinkedList<Integer>());
        pairs.add(new LinkedList<Integer>());
        pairs.get(0).add(0);
        pairs.get(0).add(3);
        pairs.get(1).add(1);
        pairs.get(1).add(2);
        Solution1202 solution = new Solution1202();
        String ans = solution.smallestStringWithSwaps("dcab", pairs);
        System.out.println(ans);
    }
}