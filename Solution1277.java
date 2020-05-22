class Solution1277 {
    public int countSquares(int[][] matrix) {
        int ans = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int width = matrix.length, height = matrix[0].length;
        int maxSide = Math.min(width, height);
        boolean[][][] dp = new boolean[maxSide][width][height];

        for (int i = 0; i < maxSide; i++) {
            for (int m = 0; m < width - i; m++) {
                for (int n = 0; n < height - i; n++) {
                    if (i == 0) {
                        dp[i][m][n] = matrix[m][n] == 1;
                    } else {
                        dp[i][m][n] = dp[i - 1][m][n] && dp[i - 1][m + 1][n] && dp[i - 1][m][n + 1]
                                && dp[i - 1][m + 1][n + 1];
                    }
                    ans += dp[i][m][n] ? 1 : 0;
                }
            }
        }

        return ans;
    }

    public int countSquaresEnhanced(int[][] matrix) {
        int ans = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int width = matrix.length, height = matrix[0].length;

        // This solution takes m*n space, we can even reduce space to n by only keep
        // tracking of prevous row.
        int[][] dp = new int[width][height];

        for (int m = 0; m < width; m++) {
            for (int n = 0; n < height; n++) {
                if (m == 0 || n == 0) {
                    dp[m][n] = matrix[m][n];
                } else {
                    if (matrix[m][n] == 1) {
                        dp[m][n] = Math.min(dp[m - 1][n], Math.min(dp[m][n - 1], dp[m - 1][n - 1])) + 1;
                    }
                }
                ans += dp[m][n];
            }
        }
        return ans;
    }
}