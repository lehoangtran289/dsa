package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class M_763_PartitionLabels {
    public static void main(String[] args) {
        System.out.println(new M_763_PartitionLabels().partitionLabels(
                "ababcbacadefegdehijhklij"
        )); // [9, 7, 8]
    }

    public List<Integer> partitionLabels(String s) {
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
