package leetcode.array.binarysearch;

import java.util.Arrays;

public class M_2517_MaximumTastinessOfCandyBasket {
    public static void main(String[] args) {
        System.out.println(maximumTastiness(new int[]{13, 5, 1, 8, 21, 2}, 3)); // 8
    }

    /**
     * Binary search on result.
     * Idea: isValid() = check if we can pick k candies with min tastiness >= mid
     * ---
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);

        int res = 0;
        int l = 0, r = price[price.length - 1] - price[0];

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (isValid(price, k, mid)) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }

    private static boolean isValid(int[] price, int k, int minDiff) {
        int lastPick = price[0];
        int count = 1;

        for (int i = 1; i < price.length; ++i) {
            if (price[i] - lastPick >= minDiff) {
                count++;
                lastPick = price[i];
            }

            if (count >= k) return true;
        }

        return false;
    }
}
