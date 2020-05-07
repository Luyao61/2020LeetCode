
// https://leetcode.com/problems/android-unlock-patterns/
class Solution351 implements LeetcodeSolution {
    public int numberOfPatterns(int m, int n) {

        int ans = 0;
        for (int i = m; i <= n; i++) {
            boolean[] visited = new boolean[10];
            ans += calculate(-1, i, visited);
        }
        return ans;
    }

    private int calculate(int prev, int len, boolean[] visited) {
        if (len == 0) {
            return 1;
        }
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (isValidMove(visited, prev, i)) {
                visited[i] = true;
                sum += calculate(i, len - 1, visited);
                visited[i] = false;
            }

        }
        return sum;
    }

    private boolean isValidMove(boolean[] visited, int last, int i) {
        if (visited[i]) {
            return false;
        }
        if (last == -1) {
            return true;
        }
        // knight moves or adjacent cells (in a row or in a column)
        if ((i + last) % 2 == 1)
            return true;
        int mid = (i + last) / 2;
        if (mid == 4)
            return visited[mid];
        // adjacent cells on diagonal - for example 0,0 and 1,0 or 2,0 and //1,1
        if ((i % 3 != last % 3) && (i / 3 != last / 3)) {
            return true;
        }
        // all other cells which are not adjacent
        return visited[mid];
    }

    public void test() {
        int solution = this.numberOfPatterns(3, 9);
        System.out.println(solution);
    }
}