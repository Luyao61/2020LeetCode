import java.util.Arrays;

class Solution973_1 implements LeetcodeSolution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] results = new int[K][];
        if (points == null) {
            return null;
        }

        sort(points, 0, points.length - 1);

        for (int i = 0; i < K; i++) {
            results[i] = points[i];
        }
        return results;
    }

    public void sort(int[][] points, int start, int end) {
        if (start < end) {
            int pivot = dist(points[start]);
            int i = start + 1, j = end;
            // we need to check i=j; 1. we expect i and j go byond each other.
            while (i <= j) {
                if (dist(points[i]) <= pivot) {
                    i++;
                } else if (dist(points[j]) > pivot) {
                    j--;
                } else {
                    swap(points[i], points[j]);
                    i++;
                    j--;
                }
            }
            swap(points[start], points[j]);
            sort(points, start, j - 1);
            sort(points, j + 1, end);
        }
    }

    public void swap(int[] p1, int[] p2) {
        int t1 = p1[0], t2 = p1[1];
        p1[0] = p2[0];
        p1[1] = p2[1];
        p2[0] = t1;
        p2[1] = t2;
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