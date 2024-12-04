package leetcode.greedy;

import java.util.Arrays;

public class H_1402_ReducingDishes {
    public static void main(String[] args) {
        System.out.println(maxSatisfaction(new int[]{-1, -8, 0, 5, -9}));
    }

    public static int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int res = 0;
        int curSum = 0;
        for (int i = satisfaction.length - 1; i >= 0; --i) {
            curSum += satisfaction[i];

            // res[i] = res[i-1] + sum(0 -> i)
            // curSum < 0 means res[i] is decreasing -> stop
            if (curSum < 0) return res;
            else res += curSum;
        }
        return res;
    }
}
