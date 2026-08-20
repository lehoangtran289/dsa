package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_77_Combinations {
    static void main() {
        System.out.println(combine(4, 2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, k, res, 1, new ArrayList<>());
        return res;
    }

    private static void backtrack(
            int n,
            int k,
            List<List<Integer>> res,
            int start,
            List<Integer> cur
    ) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = start; i <= n; ++i) {
            cur.add(i);
            backtrack(n, k, res, i + 1, cur);
            cur.removeLast();
        }
    }
}
