package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class M_2554_MaximumNumberOfIntegersToChooseFromARangeI {
    public static void main(String[] args) {
        System.out.println(maxCount(new int[]{1, 6, 5}, 5, 6));
    }

    public static int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> bannedSet = new HashSet<>();
        for (int num : banned) bannedSet.add(num);

        int count = 0;
        int curSum = 0;
        for (int num = 1; num <= n; ++num) {
            if (bannedSet.contains(num)) continue;
            if (curSum + num >= maxSum) break;

            curSum += num;
            count++;
        }

        return count;
    }
}
