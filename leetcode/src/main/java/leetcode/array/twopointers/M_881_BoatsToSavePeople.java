package leetcode.array.twopointers;

import java.util.Arrays;

public class M_881_BoatsToSavePeople {
    public static void main(String[] args) {
        System.out.println(numRescueBoats(new int[]{5, 1, 4, 2}, 6)); // 2
    }

    /**
     * Greedy approach with two pointers
     * Idea: pair the lightest and heaviest person together if possible,
     * --------------------
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int n = people.length;
        int res = 0;
        int l = 0, r = n - 1;

        while (l <= r) {
            if (people[l] + people[r] <= limit) {
                res++;
                l++;
                r--;
            } else {
                res++;
                r--;
            }
        }

        return res;
    }
}
