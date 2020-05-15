// https://leetcode.com/problems/maximum-sum-circular-subarray/
class Solution918 implements LeetcodeSolution {
    public int maxSubarraySumCircular(int[] A) {
        int maxValue = Integer.MIN_VALUE;
        int currentMax = 0;
        for (int i = 0; i < A.length; i++) {
            currentMax = Math.max(currentMax, 0);
            currentMax = currentMax + A[i];
            maxValue = Math.max(maxValue, currentMax);
        }
        // Or max sum could be the sum of maxFromLeft + maxFromRight;
        // Calculate max sum from right;
        int sumFromRight = A[0];
        int[] maxSumFromRight = new int[A.length];
        maxSumFromRight[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sumFromRight += A[i];
            maxSumFromRight[i] = Math.max(maxSumFromRight[i - 1], sumFromRight);
        }
        int sumfromLeft = 0;
        for (int i = A.length - 1; i > 0; i--) {
            // for each sum from left to index i, lookup maxSumFromRight[i-1];
            // compare with global maxsum.
            sumfromLeft += A[i];
            maxValue = Math.max(maxValue, sumfromLeft + maxSumFromRight[i - 1]);
        }
        
        return maxValue;
    }

    public void test() {
        System.out.println(maxSubarraySumCircular(new int[] {1,-2,3,-2}));
    }
}