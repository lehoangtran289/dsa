package leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class M_1930_UniqueLength3PalindromicSubsequences {
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequence("bbcbaba"));
    }

    public static int countPalindromicSubsequence(String s) {
        int count = 0;

        Map<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), new int[]{i, -1});
            } else {
                int[] idx = map.get(s.charAt(i));
                idx[1] = i;
            }
        }

        for (Map.Entry<Character, int[]> entry : map.entrySet()) {
            int[] idx = entry.getValue();
            Set<Character> seen = new HashSet<>();
            for (int i = idx[0] + 1; i < idx[1]; ++i) {
                seen.add(s.charAt(i));
            }

            count += seen.size();
        }

        return count;
    }
}
