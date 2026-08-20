package leetcode.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Follow up: do not use Set<List<Integer>> to check for duplication
 */
public class M_47_PermutationsII {
    static void main() {
        System.out.println(permuteUnique(new int[]{1, 1, 2}));
    }

    /**
     * Idea: Traverse unique nums in frequency map -> avoid starting at a number again (duplication)
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        backtrack(nums, res, freq, new ArrayList<>());

        return res;
    }

    private static void backtrack(
            int[] nums,
            List<List<Integer>> res,
            Map<Integer, Integer> freq,
            List<Integer> cur
    ) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (var entry : freq.entrySet()) { // avoid starting at a number multiple time
            if (entry.getValue() == 0) continue;
            int num = entry.getKey();

            cur.add(num);
            freq.put(num, freq.get(num) - 1);

            backtrack(nums, res, freq, cur);

            cur.removeLast();
            freq.put(num, freq.get(num) + 1);
        }
    }
}
