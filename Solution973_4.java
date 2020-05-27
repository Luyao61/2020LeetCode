import java.util.Arrays;

class Solution973_4 implements LeetcodeSolution {
    // Time compelexity: O(N) on Average, Worst case O(N^2).
    // Fast, but still cannot deal with online stream data.
    // The returned array is not guaranteed to be sorted.
    // Compatred to the O(NlogN) quick sort sorlution, The trade off is: sorted vs
    // time.
    public int[][] kClosest(int[][] points, int K) {
        int[][] results = new int[K][];
        if (points == null) {
            return null;
        }
        partition(points, 0, points.length - 1, K);
        int c = 0;
        for (int i = 0; i < K; i++) {
            results[c++] = points[i];
        }
        return results;
    }

    public void partition(int[][] points, int start, int end, int K) {
        // if (K == 0) {
        // return;
        // }
        if (start < end) {
            // pick start as the pivot point.
            // ideally, we should suffle the points, or randomly pick the pivot point.
            int pivot = dist(points[start]);
            int i = start + 1, j = end;
            while (i <= j) {
                if (dist(points[i]) <= pivot) {
                    i++;
                } else if (dist(points[j]) > pivot) {
                    j--;
                } else {
                    swap(points, i, j);
                    i++;
                    j--;
                }
            }
            // swap pivot with j, the middle point;
            swap(points, start, j);
            int left = j - start + 1;
            // cutting branch of this recursion.
            if (left == K) {
                return;
            } else if (left < K) {
                partition(points, j + 1, end, K - left);
            } else {
                partition(points, start, j - 1, K);
            }
        }
    }

    public void swap(int[][] points, int i, int j) {
        int p0 = points[i][0], p1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = p0;
        points[j][1] = p1;
    }

    public int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public void test() {
        int[][] test = new int[][] { { 3, 3 }, { 5, -1 }, { -2, 4 } };
        int[][] results = kClosest(test, 2);
        for (int[] result : results) {
            System.out.println(Arrays.toString(result));
        }
    }
}