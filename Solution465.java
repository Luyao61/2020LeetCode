import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/optimal-account-balancing/
class Solution465 implements LeetcodeSolution {
    private int _minTrans ;
    public int minTransfers(int[][] transactions) {
        this._minTrans = Integer.MAX_VALUE;
        if (transactions == null) {
            return 0;
        }
        List<Integer> nonZeroBalance = new ArrayList<Integer>();
        Map<Integer, Integer> balance = new HashMap<>();
        for (int[] t: transactions) {
            if (t != null) {
                balance.putIfAbsent(t[0], 0);
                balance.putIfAbsent(t[1], 0);
                balance.put(t[0], balance.get(t[0]) - t[2]);
                balance.put(t[1], balance.get(t[1]) + t[2]);
            }
        }
        balance.forEach((key, value) -> {
            if (value != 0) {
                nonZeroBalance.add(value);
            }
        });
        _dfs(nonZeroBalance, 0, 0, 0);
        return _minTrans;
    }

    private void _dfs(List<Integer> balance, int settledCount, int s, int remains) {
        if (settledCount == balance.size()) {
            if (s < _minTrans) {
                _minTrans = s;
            }
            return;
        }
        for (int i = 0 ; i < balance.size(); i++) {
            int currentBalance = balance.get(i);
            if (currentBalance == 0) {
                continue;
            }
            if (remains == 0) {
                // remains = currentBalance;
                balance.set(i, 0);
                _dfs(balance, settledCount, s, currentBalance);
                balance.set(i, currentBalance);
            } else if (balance.get(i) * remains < 0) {
                int currentRemains = remains + currentBalance;
                balance.set(i, currentRemains);
                _dfs(balance, currentRemains == 0 ? settledCount + 2 : settledCount + 1, s + 1, 0);
                balance.set(i, currentBalance);
            }
        }
    }
// [[10,11,6],[12,13,7],[14,15,2],[14,16,2],[14,17,2],[14,18,2]]
    public void test() {
        int[][] trans = new int[6][];
        trans[0] = new int[] {10, 11, 6};
        trans[1] = new int[] {12, 13, 7};
        trans[2] = new int[] {14, 15, 2};
        trans[3] = new int[] {14, 16, 2};
        trans[4] = new int[] {14, 17, 2};
        trans[5] = new int[] {14, 18, 2};

        System.out.println(this.minTransfers(trans));
    }
}