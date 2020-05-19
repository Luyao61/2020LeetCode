/**
 * Given an integer array, pick any 3 non-repated integers from the it to form a
 * tuple, if any 2 integers of the tuple are equal, then the tuple is a nice
 * tuple, how many nice tuples can be created from the array.
 */
class CountNiceTuple implements LeetcodeSolution {
    public int countNiceTuple(int[] array) {
        return dfs(array, new int[3], 0, 0);
    }

    public int dfs(int[] array, int[] current, int index, int count) {
        if (count == 3) {
            if ((current[0] ^ current[1]) == 0 || (current[0] ^ current[2]) == 0 || (current[1] ^ current[2]) == 0) {
                return 1;
            }
            return 0;
        }
        int ans = 0;
        for (int i = index; i < array.length; i++) {
            current[count] = array[i];
            ans += dfs(array, current, i + 1, count + 1);
        }
        return ans;
    }

    public void test() {
        System.out.println(countNiceTuple(new int[] { 1, 1, 1, 1, 1, 1 }));
    }
}