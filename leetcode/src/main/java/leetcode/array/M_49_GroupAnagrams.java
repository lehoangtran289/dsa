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

    public static List<List<String>> groupAnagrams(String[] strs) {
        // group string index by sorted string
        Map<String, List<Integer>> groups = new HashMap<>();

        for (int i = 0; i < strs.length; ++i) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String sortedStr = new String(charArr);
            groups.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(i);
        }

        // build result
        List<List<String>> res = new ArrayList<>();
        for (String key : groups.keySet()) {
            List<String> anagrams = new ArrayList<>();
            for (int id : groups.get(key)) {
                anagrams.add(strs[id]);
            }
            res.add(anagrams);
        }

        return res;
    }
}
