package leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class M_1980_FindUniqueBinaryString {
    public static void main(String[] args) {
        System.out.println(findDifferentBinaryString(new String[]{"01", "10"}));
    }

    private static final char[] DIGITS = new char[]{'0', '1'};

    public static String findDifferentBinaryString(String[] nums) {
        Set<String> set = new HashSet<>(Arrays.asList(nums));
        int n = nums.length;

        List<String> res = new ArrayList<>();
        backtrack(set, res, new StringBuilder(), n);
        return res.get(0);
    }

    private static void backtrack(
            Set<String> set,
            List<String> res,
            StringBuilder cur,
            int n
    ) {
        // since the problem only requires one result, we can skip when res is not empty
        if (!res.isEmpty()) return;

        if (cur.length() == n) {
            if (!set.contains(cur.toString())) {
                res.add(cur.toString());
            }
            return;
        }

        // try combinations
        for (char c : DIGITS) {
            cur.append(c);
            backtrack(set, res, cur, n);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
