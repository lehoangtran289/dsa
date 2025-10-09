package leetcode.array.binarysearch;

import java.util.Arrays;

public class M_2300_SuccessfulPairsOfSpellsAndPotions {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(successfulPairs(
                new int[]{5, 1, 3},
                new int[]{1, 2, 3, 4, 5},
                7
        ))); // [4,0,3]
    }

    /**
     * Binary Search
     * Idea: Sort potions, for each spell, find the minimum potion that can form a successful pair
     * -----------------------
     * TC: O((M + N) logM)
     * SC: O(1)
     */
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int[] res = new int[n];

        Arrays.sort(potions);

        for (int i = 0; i < n; ++i) {
            int minPotion = (int) Math.ceil((double) success / spells[i]);
            int validIdx = lowerBound(potions, minPotion);

            if (validIdx != -1) {
                res[i] = potions.length - validIdx;
            }
        }

        return res;
    }

    private static int lowerBound(int[] arr, int target) {
        int res = -1;
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] >= target) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }
}
