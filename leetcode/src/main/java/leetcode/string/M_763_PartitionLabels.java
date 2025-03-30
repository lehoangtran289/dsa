package leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class M_763_PartitionLabels {
    public static void main(String[] args) {
        System.out.println(partitionLabels1("ababcbacadefegdehijhklij")); // [9, 7, 8]
    }

    /**
     * ---------------------------------------------------------------------------
     * Merge intervals approach
     * TC: O(n^2)
     * SC: O(n)
     * ---------------------------------------------------------------------------
     */
    public static List<Integer> partitionLabels1(String s) {
        int n = s.length();

        // building intervals
        List<int[]> intervals = new ArrayList<>();
        Set<Character> taken = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (taken.contains(c)) continue;

            for (int j = n - 1; j >= i; --j) {
                if (s.charAt(j) == c) {
                    intervals.add(new int[]{i, j});
                    break;
                }
            }
            taken.add(c);
        }

        // merge intervals
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals.get(0));
        for (int[] interval : intervals) {
            int[] cur = merged.get(merged.size() - 1);
            if (interval[0] <= cur[1]) {
                cur[1] = Math.max(cur[1], interval[1]);
            } else {
                merged.add(interval);
            }
        }

        // building result
        List<Integer> res = new ArrayList<>();
        for (int[] interval : merged) {
            res.add(interval[1] - interval[0] + 1);
        }
        return res;
    }

    /**
     * ---------------------------------------------------------------------------
     * 2 pointers + last index array approach
     * TC: O(n)
     * SC: O(1)
     * ---------------------------------------------------------------------------
     */
    public static List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] lastIndex = new int[26];

        for (int i = 0; i < n; ++i) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int curStart = 0, curEnd = 0;
        for (int i = 0; i < n; ++i) {
            curEnd = Math.max(curEnd, lastIndex[s.charAt(i) - 'a']);

            if (i == curEnd) {
                res.add(curEnd - curStart + 1);
                curStart = curEnd + 1;
            }
        }

        return res;
    }
}
