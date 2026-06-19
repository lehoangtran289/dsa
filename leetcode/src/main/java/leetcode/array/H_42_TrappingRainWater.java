package leetcode.array;

public class H_42_TrappingRainWater {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // Output: 6
    }

    /**
     * Intuition:
     * Calculate prefix left and right max height
     * ---
     * TC: O(3 * n)
     * SC: O(2 * n)
     */
    public static int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int res = 0;

        // Calculate the trapped water at each index (How much water can be trapped on this wall)
        for (int i = 0; i < n; ++i) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return res;
    }

    /**
     * Two Pointers
     * ---
     * Optimization: Move the pointer on the shorter side — because that side is the limiting factor for water.
     * You already have enough information to calculate water there!
     * ---
     * TC: O(n)
     * SC: O(1)
     */
    public static int trap2(int[] height) {
        int n = height.length;
        int res = 0;

        int leftMax = 0, rightMax = 0;
        int left = 0, right = n - 1;

        while (left <= right) {
            if (height[left] <= height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left]; // same equation as intuition solution, but here we already have shorter side.
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right];
                right--;
            }
        }

        return res;
    }
}
