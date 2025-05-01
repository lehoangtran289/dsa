package leetcode.array.slidingwindow;

public class E_1176_DietPlanPerformance {

    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int res = 0;
        int n = calories.length;
        int curSum = 0;

        // first k days
        for (int i = 0; i < k; ++i) {
            curSum += calories[i];
        }
        res += getPoints(curSum, upper, lower);

        // process the rest
        for (int i = k; i < n; ++i) {
            curSum += calories[i];
            curSum -= calories[i - k];
            res += getPoints(curSum, upper, lower);
        }

        return res;
    }

    private int getPoints(int sum, int upper, int lower) {
        if (sum < lower) return -1;
        if (sum > upper) return 1;
        return 0;
    }
}
