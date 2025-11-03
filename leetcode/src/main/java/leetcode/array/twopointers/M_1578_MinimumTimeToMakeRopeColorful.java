package leetcode.array.twopointers;

public class M_1578_MinimumTimeToMakeRopeColorful {
    public static void main(String[] args) {
        System.out.println(minCost("abaac", new int[]{1, 2, 3, 4, 5})); // 3
        System.out.println(minCost("abc", new int[]{1, 2, 3})); // 0
        System.out.println(minCost("aabaa", new int[]{1, 2, 3, 4, 1})); // 2
    }

    /**
     * 1 pass traversal
     * Idea: Keep track of the maximum needed time in the current segment of same colors.
     * -------------------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        int res = 0;
        int curMax = neededTime[0];

        for (int i = 1; i < n; ++i) {
            if (colors.charAt(i) != colors.charAt(i - 1)) {
                curMax = neededTime[i];
                continue;
            }
            res += Math.min(curMax, neededTime[i]);
            curMax = Math.max(curMax, neededTime[i]);
        }

        return res;
    }

    /**
     * 2 pointers, partition the string into segments with the same color
     * -------------------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static int minCost1(String colors, int[] neededTime) {
        int n = colors.length();
        int res = 0;

        for (int i = 0; i < n; ++i) {
            int maxTime = neededTime[i];
            int totalTime = neededTime[i];

            while (
                    i < n - 1
                    && colors.charAt(i) == colors.charAt(i + 1)
            ) {
                totalTime += neededTime[i + 1];
                maxTime = Math.max(maxTime, neededTime[i + 1]);
                i++;
            }

            res += totalTime - maxTime;
        }

        return res;
    }


}
