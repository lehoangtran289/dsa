package leetcode.backtrack;

public class M_1780_CheckIfNumberIsASumOfPowersOfThree {
    public static void main(String[] args) {
        System.out.println(checkPowersOfThree(55645)); // true
        System.out.println(checkPowersOfThree(91)); // true
        System.out.println(checkPowersOfThree(21)); // false
    }

    public static boolean checkPowersOfThree2(int n) {
        int power = 0;

        // Find the largest power that is smaller or equal to n
        while (Math.pow(3, power) <= n) {
            power++;
        }

        while (n > 0) {
            // Subtract current power from n
            if (n >= Math.pow(3, power)) {
                n -= (int) Math.pow(3, power);
            }
            // We cannot use the same power twice
            if (n >= Math.pow(3, power)) {
                return false;
            }
            // Move to the next lower power
            power--;
        }

        // n has reached 0
        return true;
    }

    // backtrack - TLE
    public static boolean checkPowersOfThree(int n) {
        int maxPower = 0;
        while (Math.pow(3, maxPower) <= n) {
            maxPower++;
        }

        boolean[] powers = new boolean[maxPower];
        return backtrack(n, 0, powers);
    }

    private static boolean backtrack(
            int n,
            int curSum,
            boolean[] powers
    ) {
        // base cases
        if (curSum == n) return true;
        if (curSum > n) return false;

        for (int i = 0; i < powers.length; ++i) {
            if (!powers[i]) {
                powers[i] = true;
                boolean res = backtrack(n, curSum + (int) Math.pow(3, i), powers);

                if (res) {
                    return true;
                } else {
                    powers[i] = false;
                }
            }
        }

        return false;
    }
}
