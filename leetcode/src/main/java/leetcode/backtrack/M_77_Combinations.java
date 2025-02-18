package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_77_Combinations {
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), 1);
        return res;
    }

    private void backtrack(
            List<List<Integer>> res,
            List<Integer> cur,
            int firstNum
    ) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = firstNum; i <= n; ++i) {
            cur.add(i);
            backtrack(res, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}
