package contest.weekly456;

import java.util.*;

public class Q2_LongestCommonPrefixBetweenAdjacentStringsAfterRemovals {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                longestCommonPrefix(new String[]{"jump", "run", "run", "jump", "run"}))
        );
    }

    public static int[] longestCommonPrefix(String[] words) {
        int n = words.length;
        int[] prefix = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            prefix[i] = commonPrefixLength(words[i], words[i + 1]);
        }

        TreeMap<Integer, Integer> prefixMap = new TreeMap<>(); // <prefix length, count>
        for (int val : prefix) {
            increaseCount(prefixMap, val);
        }

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int left = i - 1;

            List<Integer> removed = new ArrayList<>();
            if (left >= 0) {
                decreaseCount(prefixMap, prefix[left]);
                removed.add(prefix[left]);
            }

            if (i < n - 1) {
                decreaseCount(prefixMap, prefix[i]);
                removed.add(prefix[i]);
            }

            // Add new prefix of words[i - 1] & words[i + 1]
            int merged = 0;
            if (left >= 0 && i < n - 1) {
                merged = commonPrefixLength(words[left], words[i + 1]);
                increaseCount(prefixMap, merged);
            }

            result[i] = prefixMap.isEmpty() ? 0 : prefixMap.lastKey();

            // Restore state
            if (left >= 0 && i < n - 1) {
                decreaseCount(prefixMap, merged);
            }
            for (int val : removed) {
                increaseCount(prefixMap, val);
            }
        }

        return result;
    }

    private static void decreaseCount(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    private static void increaseCount(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private static int commonPrefixLength(String s1, String s2) {
        int p = 0;

        while (p < s1.length() && p < s2.length()) {
            if (s1.charAt(p) == s2.charAt(p)) {
                p++;
            } else break;
        }

        return p;
    }
}
