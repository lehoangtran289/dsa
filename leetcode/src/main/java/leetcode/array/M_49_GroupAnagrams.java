package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_49_GroupAnagrams {
    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    /**
     * Given an array of strings strs, group the anagrams together.
     * Idea; Sort + hashmap
     * --------------------
     * TC: O(n * k log k), where n is the number of strings and k is the maximum length of a string
     * SC: O(n * k), where n is the number of strings and k is the maximum length of a string
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // group string index by sorted string
        Map<String, List<Integer>> groups = new HashMap<>();

        for (int i = 0; i < strs.length; ++i) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String key = String.valueOf(charArr);

            if (!groups.containsKey(key)) {
                groups.put(key, new ArrayList<>());
            }
            groups.get(key).add(i);
        }

        // build result
        List<List<String>> res = new ArrayList<>();

        for (var entry : groups.entrySet()) {
            List<String> group = new ArrayList<>();
            for (int index : entry.getValue()) {
                group.add(strs[index]);
            }
            res.add(group);
        }

        return res;
    }
}
