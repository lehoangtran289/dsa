package leetcode.array;

/**
 * Count #numbers in [low, high] that has 2 * n digits
 * and the sum of the first n digits is equal to the sum of the last n digits.
 */
public class E_2843_CountSymmetricIntegers {

    public int countSymmetricIntegers(int low, int high) {
        int res = 0;
        for (int num = low; num <= high; ++num) {
            if (isSymmetric(num)) res++;
        }
        return res;
    }

    private boolean isSymmetric(int num) {
        String s = Integer.toString(num);
        int n = s.length();
        if ((n & 1) == 1) return false;

        int lSum = 0, rSum = 0;
        int l = 0, r = n - 1;
        while (l < r) {
            lSum += s.charAt(l) - '0';
            rSum += s.charAt(r) - '0';
            l++;
            r--;
        }

        return lSum == rSum;
    }
}
