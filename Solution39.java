import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/combination-sum/
class Solution {
    public boolean isCombinationSum(int[] candidates, int target) {
        // Arrays.sort(candidates);
        boolean[][] dp = new boolean[candidates.length + 1][target + 1];
        for (int i = 0; i < candidates.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= candidates.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < candidates[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - candidates[i]];
                }
            }
        }
        return dp[candidates.length][target];
    }

    // candidates does not have duplicates.
    // same repeated number can be choosen from candidates.
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // dp[t] - list of subsets that sums to t;
        // dp[t][0] - first subsets that sums to t;
        List<List<List<Integer>>> dp = new ArrayList<>(target + 1);
        Arrays.sort(candidates);
        for (int t = 0; t <= target; t++) {
            dp.add(new ArrayList<>());
            for (int i = 0; i < candidates.length && candidates[i] <= t; i++) {
                if (candidates[i] == t) {
                    dp.get(t).add(new ArrayList<>(Arrays.asList(candidates[i])));
                } else {
                    for (List<Integer> list : dp.get(t - candidates[i])) {
                        // Compare the current candiate with tail element of the list, this is to keep
                        // the elements in the list in ascending order, and we can avoid having
                        // duplicates like [2,3] and [3,2]
                        if (candidates[i] >= list.get(list.size() - 1)) {
                            List<Integer> copy = new ArrayList<>(list);
                            copy.add(candidates[i]);
                            dp.get(t).add(copy);
                        }
                    }
                }
            }
        }
        return dp.get(target);
    }

    public List<List<Integer>> combinationSumEnhanced(int[] candidates, int target) {
        // dp[t] - list of subsets that sums to t;
        // dp[t][0] - first subsets that sums to t;
        List<List<List<Integer>>> dp = new ArrayList<>(target + 1);
        Arrays.sort(candidates);
        // initialize the dp[0];
        List<List<Integer>> base = new ArrayList<>();
        base.add(new ArrayList<>());
        dp.add(base);
        for (int t = 1; t <= target; t++) {
            dp.add(new ArrayList<>());
            for (int i = 0; i < candidates.length && candidates[i] <= t; i++) {
                for (List<Integer> list : dp.get(t - candidates[i])) {
                    // Compare the current candiate with tail element of the list, this is to keep
                    // the elements in the list in ascending order, and we can avoid having
                    // duplicates like [2,3] and [3,2]
                    if (list.isEmpty() || candidates[i] >= list.get(list.size() - 1)) {
                        List<Integer> copy = new ArrayList<>(list);
                        copy.add(candidates[i]);
                        dp.get(t).add(copy);
                    }
                }
            }
        }
        return dp.get(target);
    }
}