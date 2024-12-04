package leetcode.array;

import java.util.Arrays;

public class M_2601_PrimeSubtractionOperation {
    public static void main(String[] args) {
        System.out.println(primeSubOperation(new int[]{5, 8, 3}));
    }

    public static boolean primeSubOperation(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int n : nums) max = Math.max(max, n);
        boolean[] isPrimes = buildPrimeFilter(max);

        // handle first num
        for (int i = nums[0] - 1; i >= 2; --i) {
            if (isPrimes[i]) {
                nums[0] -= i;
                break;
            }
        }

        for (int i = 1; i < nums.length; ++i) {
            for (int j = nums[i] - 1; j >= 2; --j) {
                if (isPrimes[j] && nums[i] - j > nums[i - 1]) { // if j is largest prime < nums[i] && nums[i] - j > nums[i - 1]
                    nums[i] -= j;
                    break;
                }
            }
            if (nums[i] <= nums[i - 1]) return false;
        }

        return true;
    }


    public static boolean[] buildPrimeFilter(int n) {
        if (n <= 1) return new boolean[0];

        boolean[] isPrimes = new boolean[n + 1];
        Arrays.fill(isPrimes, true);
        isPrimes[0] = false;
        isPrimes[1] = false;

        for (int i = 2; i <= Math.sqrt(n); ++i) {
            if (isPrimes[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrimes[j] = false;
                }
            }
        }

        return isPrimes;
    }
}
