import java.util.Arrays;

class Solution973_2 implements LeetcodeSolution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] results = new int[K][];
        if (points == null) {
            return null;
        }
        int[] distances = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            distances[i] = dist(points[i]);
        }
        Arrays.sort(distances);
        int threshold = distances[K - 1];
        int c = 0;
        for (int[] point : points) {
            if (dist(point) <= threshold) {
                results[c++] = point;
            }
        }
        return results;
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