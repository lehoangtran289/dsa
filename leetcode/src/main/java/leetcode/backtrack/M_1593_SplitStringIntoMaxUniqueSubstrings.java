package leetcode.backtrack;

import java.util.HashSet;
import java.util.Set;

public class M_1593_SplitStringIntoMaxUniqueSubstrings {
    public static void main(String[] args) {
        System.out.println(new M_1593_SplitStringIntoMaxUniqueSubstrings().maxUniqueSplit("wwwzfvedwfvhsww")); // 11
        System.out.println(new M_1593_SplitStringIntoMaxUniqueSubstrings().maxUniqueSplit("ababccc"));
        System.out.println(new M_1593_SplitStringIntoMaxUniqueSubstrings().maxUniqueSplit("aba"));
        System.out.println(new M_1593_SplitStringIntoMaxUniqueSubstrings().maxUniqueSplit("aa"));
    }

    /**
     * Initialize an empty unordered set seen to track unique substrings encountered. </br>
     * Call the backtrack function starting from index 0 with the empty seen set.</br>
     * In the backtrack function:</br>
     *     If start equals the size of the string s, return 0 (base case: no more substrings to add).</br>
     *     Initialize maxCount to 0 to track the maximum number of unique substrings.</br>
     *     Use a loop to iterate over all possible substrings starting from index start:</br>
     *         For each end from start + 1 to the size of s, extract the substring s.substr(start, end - start).</br>
     *         If the substring is unique (i.e., not found in seen):</br>
     *              Insert the substring into the seen set.</br>
     *              Recursively call backtrack for the next position (end) and update maxCount with the maximum of its current value and 1 + backtrack(s, end, seen) (including the current substring). </br>
     *              Backtrack by removing the substring from the seen set to explore other possibilities.</br>
     * After evaluating all substrings, return maxCount.</br>
     */

    public int maxUniqueSplit(String s) {
        Set<String> seen = new HashSet<>();

        int start = 0;
        return backtrack(s, start, seen);
    }

    // idea: find all possible substring and count
    public int backtrack(String s, int start, Set<String> seen) {
        if (start == s.length()) return 0;

        int maxCount = 0;
        for (int end = start + 1; end <= s.length(); ++end) {
            String cur = s.substring(start, end);
            if (seen.contains(cur)) continue;

            seen.add(cur);
            maxCount = Math.max(maxCount, 1 + backtrack(s, end, seen));
            seen.remove(cur);
        }
        return maxCount;
    }

    public int maxUniqueSplitNotOptimal(String s) {
        Set<String> set = new HashSet<>();

        String init = s.charAt(0) + "";
        set.add(init);

        String cur = "";
        String prev = cur;
        for (int i = 1; i < s.length(); ++i) {
            cur += s.charAt(i);
            if (set.contains(cur)) {
                if (i == s.length() - 1) {
                    set.remove(prev);
                    set.add(prev + s.charAt(i));
                }
            } else {
                set.add(cur);
                prev = cur;
                cur = "";
            }
        }
        return set.size();
    }
}
