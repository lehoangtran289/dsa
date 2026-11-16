package leetcode.array;

public class H_42_TrappingRainWater {
    static void main() {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // Output: 6
    }

    /**
     * INTUITION
     * Idea: at index i, trap-able water = min(height_left_max, height_right_max) - height[i]
     * -> Use 2 arrays to track max height on left side and right side separately.
     * ---
     * TC: O(2N)
     * SC: O(2N)
     */
    public static int trap(int[] height) {
        int n = height.length;

        // build left and right max height arrays
        int[] leftMaxHeights = new int[n];
        int[] rightMaxHeights = new int[n];

        leftMaxHeights[0] = height[0];
        rightMaxHeights[n - 1] = height[n - 1];

        for (int i = 1; i < n; ++i) {
            leftMaxHeights[i] = Math.max(leftMaxHeights[i - 1], height[i]);
            rightMaxHeights[n - 1 - i] = Math.max(rightMaxHeights[n - i], height[n - 1 - i]);
        }

        // build result
        int res = 0;
        for (int i = 1; i < n - 1; ++i) {
            res += Math.min(leftMaxHeights[i], rightMaxHeights[i]) - height[i];
        }
        return res;
    }

    /**
     * Idea: Optimize space to O(1). (trick: only need to track max height of the side with SMALLER MAX HEIGHT)
     * ---
     * Maintain 2 vars max_left_so_far and max_right_so_far and 2 pointers l and r
     * If max_left < max_right -> calc trapped water at l, then forward l and update max_left
     * If max_right > max_left -> calc trapped water at r, then backward r and update max_right
     * ---
     * TC: O(N)
     * SC: O(1)
     */
    public static int trap2(int[] height) {
        int n = height.length;
        int res = 0;
        int l = 0, r = n - 1;
        int maxLeftHeight = height[l], maxRightHeight = height[r];

        while (l <= r) {
            if (maxLeftHeight < maxRightHeight) {
                maxLeftHeight = Math.max(maxLeftHeight, height[l]);
                res += maxLeftHeight - height[l]; // no need negative check since we already update maxLeft
                l++;
            } else {
                maxRightHeight = Math.max(maxRightHeight, height[r]);
                res += maxRightHeight - height[r];
                r--;
            }
        }

        return res;
    }
}
