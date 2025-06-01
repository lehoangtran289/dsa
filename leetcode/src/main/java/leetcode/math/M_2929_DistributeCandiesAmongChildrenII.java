package leetcode.math;

public class M_2929_DistributeCandiesAmongChildrenII {

    /**
     * Problem: distribute n candies among 3 children such that no child gets more than limit candies.
     */
    public long distributeCandies(int n, int limit) {
        long res = 0;

        // enumerate first child
        for (int i = 0; i <= Math.min(n, limit); ++i) {
            int left = n - i; // candies left after giving i candies to the first child

            // if left > 2 * limit, 2nd and 3rd child cannot receive candies that satisfy the limit
            if (left > limit * 2) continue;

            // Then second child receives
            //      min = max(0, left - limit)
            //      max = min(limit, left)
            // ex: limit = 100, left = 4 -> min = 0, max = 4

            res += Math.min(limit, left) - Math.max(0, left - limit) + 1;
        }

        return res;
    }
}
