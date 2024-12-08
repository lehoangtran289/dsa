package contest.biweekly145;

import java.util.HashSet;
import java.util.Set;

public class E_Q1_MinimumOperationsToMakeArrayValuesEqualToK {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{5, 2, 5, 4, 5}, 2)); //2
        System.out.println(minOperations(new int[]{2, 1, 2}, 2)); //-1
        System.out.println(minOperations(new int[]{9, 7, 5, 3}, 1)); // 4
        System.out.println(minOperations(new int[]{1}, 1)); // 0
        System.out.println(minOperations(new int[]{2, 2}, 2)); // 0
        System.out.println(minOperations(new int[]{6, 9, 2, 2}, 5)); // -1
    }

    public static int minOperations(int[] nums, int k) {
        for (int num : nums) {
            if (num < k) {
                return -1;
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > k) {
                set.add(num);
            }
        }

        return set.size();
    }
}
