import java.util.Arrays;
import java.util.PriorityQueue;

class Solution973_3 implements LeetcodeSolution {
    // O(nlogK) run time compelxity.
    // Good for dealing with online stream data.
    // Actual running time may be longer.
    public int[][] kClosest(int[][] points, int K) {
        int[][] results = new int[K][];
        if (points == null) {
            return null;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(K, (a, b) -> {
            return dist(b) - dist(a);
        });

        for (int[] point : points) {
            if (pq.size() < K) {
                pq.offer(point);
            } else if (dist(pq.peek()) > dist(point)) {
                pq.poll();
                pq.offer(point);
            }
        }
        int c = 0;
        while (pq.size() > 0) {
            results[c++] = pq.poll();
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