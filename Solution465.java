import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/optimal-account-balancing/
class Solution465 implements LeetcodeSolution {
    private int _minTrans ;
    public int minTransfers(int[][] transactions) {
        this._minTrans = Integer.MAX_VALUE;
        if (transactions == null) {
            return 0;
        }
        Map<Integer, Integer> balance = new HashMap<>();
        for (int[] t: transactions) {
            if (t != null) {
                balance.putIfAbsent(t[0], 0);
                balance.putIfAbsent(t[1], 0);
                balance.put(t[0], balance.get(t[0]) - t[2]);
                balance.put(t[1], balance.get(t[1]) + t[2]);
            }
        }
        int[] nonZeroBalance = new int[balance.size()];
        int i = 0;
        for (int value: balance.values()){
            nonZeroBalance[i++] = value;
        }
        _dfs(nonZeroBalance, 0, 0);
        return _minTrans;
    }

    private void _dfs(int[] balance, int settledIndex, int transCount) {
        while (settledIndex < balance.length &&  balance[settledIndex] == 0) {
            settledIndex++;
        }
        if (settledIndex == balance.length) {
            if (transCount < _minTrans) {
                _minTrans = transCount;
            }
            return;
        }
        for (int i = transCount + 1 ; i < balance.length; i++) {
            int currentBalance = balance[settledIndex];
            if (currentBalance * balance[i] < 0) {
                balance[i] += currentBalance;
                _dfs(balance, settledIndex + 1, transCount + 1);
                balance[i] -= currentBalance;
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