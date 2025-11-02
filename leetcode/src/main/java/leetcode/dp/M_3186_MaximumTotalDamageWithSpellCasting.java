package leetcode.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_3186_MaximumTotalDamageWithSpellCasting {

    /**
     * DP bottom up
     * -----------------------
     * TC: O(N log N) - N is the length of power
     * SC: O(N)
     */
    public long maximumTotalDamage(int[] power) {
        // Map: power -> count
        Map<Integer, Long> powerCount = new HashMap<>();
        for (int p : power) {
            powerCount.put(p, powerCount.getOrDefault(p, 0L) + 1);
        }

        // sort unique power
        List<Integer> uniquePowers = new ArrayList<>(powerCount.keySet());
        Collections.sort(uniquePowers);

        int n = uniquePowers.size();
        long[] dp = new long[n]; // i -> max power [1->i]

        // base case
        dp[0] = powerCount.get(uniquePowers.get(0)) * uniquePowers.get(0);

        for (int i = 1; i < n; ++i) {
            long skip = dp[i - 1];

            int curPower = uniquePowers.get(i);
            long take = powerCount.get(curPower) * curPower;

            // Find previous non-conflicting power (diff >= 3)
            int j = i - 1;
            while (j >= 0 && uniquePowers.get(j) + 2 >= curPower) {
                j--;
            }

            if (j >= 0) take += dp[j]; // max power at prev

            dp[i] = Math.max(take, skip); // max(skip, take)
        }

        return dp[n - 1];
    }
}
