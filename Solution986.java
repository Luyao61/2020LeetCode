import java.util.ArrayList;
import java.util.List;

class Solution986 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        // put A, B and their indices in array, so I can get a generic way to find them;
        int[] index = new int[] { 0, 0 };
        int[][][] interval = new int[2][][];
        interval[0] = A;
        interval[1] = B;
        List<int[]> result = new ArrayList<>();
        while (index[0] < A.length && index[1] < B.length) {
            int[] temp = new int[2];
            // find the array whose next interval's starting point is smaller.
            int small = interval[0][index[0]][0] < interval[1][index[1]][0] ? 0 : 1;
            int larger = small == 1 ? 0 : 1;
            // check if the 2 intervals are overlapping;
            if (interval[larger][index[larger]][0] <= interval[small][index[small]][1]) {
                temp[0] = interval[larger][index[larger]][0];
                temp[1] = Math.min(interval[larger][index[larger]][1], interval[small][index[small]][1]);
                result.add(temp);
                // move index of the array whose next interval's ending point is smaller.
                if (interval[larger][index[larger]][1] < interval[small][index[small]][1]) {
                    index[larger]++;
                } else {
                    index[small]++;
                }
            } else {
                // if the interval are not overlapping, increment smaller array's index.
                index[small]++;
            }

        }

        return result.toArray(new int[0][]);
    }
}