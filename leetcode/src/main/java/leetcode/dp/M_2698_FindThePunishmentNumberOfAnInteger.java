package leetcode.dp;

public class M_2698_FindThePunishmentNumberOfAnInteger {
    public int punishmentNumber(int n) {
        int res = 0;

        for (int i = 1; i <= n; ++i) {
            if (isPunishNumber(i * i, i)) {
                res += i * i;
            }
        }

        return res;
    }

    private static boolean isPunishNumber(int n, int target) {
        if (target < 0 || n < target) return false;

        if (n == target) return true;

        return
                isPunishNumber(n / 10, target - (n % 10)) ||
                isPunishNumber(n / 100, target - (n % 100)) ||
                isPunishNumber(n / 1000, target - (n % 1000));
    }
}
