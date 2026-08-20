package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_46_Permutations {
    static void main() {
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }

    private static void backtrack(
            int[] nums,
            List<List<Integer>> res,
            List<Integer> cur,
            boolean[] used
    ) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (used[i]) continue;

            used[i] = true;
            cur.add(nums[i]);

            backtrack(nums, res, cur, used);

            used[i] = false;
            cur.removeLast();
        }
    }
}
