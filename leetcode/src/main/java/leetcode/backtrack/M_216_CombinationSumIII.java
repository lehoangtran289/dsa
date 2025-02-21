package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_216_CombinationSumIII {
    public static void main(String[] args) {
        M_216_CombinationSumIII solution = new M_216_CombinationSumIII();
        System.out.println(solution.combinationSum3(3, 7)); // [[1,2,4]]
    }

    private int k;
    private int n;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        this.n = n;
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), 1, 0);
        return res;
    }

    private void backtrack(
            List<List<Integer>> res,
            List<Integer> cur,
            int curStart,
            int curSum
    ) {
        if (cur.size() == k && curSum == n) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = curStart; i <= 9; ++i) {
            cur.add(i);
            backtrack(res, cur, i + 1, curSum + i);
            cur.remove(cur.size() - 1);
        }
    }
}
