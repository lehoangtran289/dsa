package leetcode.array;

public class M_918_MaximumSumCircularSubarray {
    public static void main(String[] args) {
        System.out.println(maxSubarraySumCircular(new int[]{5,-3,5})); // 10
        System.out.println(maxSubarraySumCircular(new int[]{1,-2,3,-2})); // 3
        System.out.println(maxSubarraySumCircular(new int[]{-3,-2,-3})); // -2
    }

    public static int maxSubarraySumCircular(int[] arr) {
        int[] sumCircular = new int[arr.length * 2];
        for (int i = 0; i < sumCircular.length; ++i) {
            sumCircular[i] = arr[i % arr.length];
        }

        int currMax = 0;
        int maxSum = sumCircular[0];

        for (int i = 0; i < sumCircular.length; i++) {
            currMax = Math.max(0, currMax) + sumCircular[i];
            maxSum = Math.max(maxSum, currMax);
        }

        return currMax;
    }
}
