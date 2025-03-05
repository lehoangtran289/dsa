package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_46_Permutations {
    public static void main(String[] args) {
        System.out.println(new M_46_Permutations().permute(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, nums, new ArrayList<>());

        return res;
    }

    private void backtrack(
            List<List<Integer>> res,
            int[] nums,
            List<Integer> curList
    ) {
        if (curList.size() == nums.length) {
            res.add(new ArrayList<>(curList));
            return;
        }

        for (int num : nums) {
            if (curList.contains(num)) continue;

            curList.add(num);
            backtrack(res, nums, curList);
            curList.remove(curList.size() - 1);
        }
    }
}
