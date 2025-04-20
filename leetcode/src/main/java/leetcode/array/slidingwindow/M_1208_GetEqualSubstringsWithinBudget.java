package leetcode.array.slidingwindow;

public class M_1208_GetEqualSubstringsWithinBudget {

    /**
     * Sliding window
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int res = 0;
        int n = s.length();
        int cost = 0;
        int l = 0;

        for (int r = 0; r < n; ++r) {
            cost += getCost(s, t, r);

            while (l <= r && cost > maxCost) {
                cost -= getCost(s, t, l);
                l++;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    private int getCost(String s, String t, int id) {
        return Math.abs(s.charAt(id) - t.charAt(id));
    }
}
