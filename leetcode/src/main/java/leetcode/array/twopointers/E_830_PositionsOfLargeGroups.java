package leetcode.array.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Find positions of large groups in a string.
 * A large group is defined as a group of 3 or more consecutive characters.
 */
public class E_830_PositionsOfLargeGroups {
    public static void main(String[] args) {
        System.out.println(largeGroupPositions2("abcdddeeeeaabbbcd")); // [[3,5],[6,9],[12,14]]
    }

    /**
     * Two pointers approach
     * --------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();

        int l = 0, r = 0;
        char curChar = s.charAt(0);

        while (r < s.length()) {
            if (s.charAt(r) != curChar) {
                if (r - l >= 3) {
                    res.add(List.of(l, r - 1));
                }
                l = r;
                curChar = s.charAt(l);
            }
            r++;
        }

        if (r - l >= 3) {
            res.add(List.of(l, r - 1));
        }

        return res;
    }

    /**
     * Two pointers approach 2
     * --------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static List<List<Integer>> largeGroupPositions2(String s) {
        List<List<Integer>> res = new ArrayList<>();

        int n = s.length();
        int start = 0;

        for (int end = 0; end <= n; ++end) {
            if (end == n || s.charAt(end) != s.charAt(start)) {
                if (end - start >= 3) {
                    res.add(List.of(start, end - 1));
                }
                start = end;
            }
        }

        return res;
    }


}
