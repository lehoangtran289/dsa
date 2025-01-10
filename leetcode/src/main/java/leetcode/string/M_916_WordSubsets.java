package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_916_WordSubsets {
    public static void main(String[] args) {
        System.out.println(wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"lo", "eo"}
        ));
    }

    // brute force
    public static List<String> wordSubsets(String[] words1, String[] words2) {
        // preprocess words2
        Map<Character, Integer> map = new HashMap<>();
        for (String w : words2) {
            Map<Character, Integer> temp = new HashMap<>();
            for (int i = 0; i < w.length(); ++i) {
                char c = w.charAt(i);
                temp.put(c, temp.getOrDefault(c, 0) + 1);
                if (!map.containsKey(c) || temp.get(c) > map.get(c)) {
                    map.put(c, temp.get(c));
                }
            }
        }

        // process words1
        List<String> res = new ArrayList<>();
        for (String w : words1) {
            if (w.length() < map.size()) continue;
            if (check(w, map)) res.add(w);
        }

        return res;
    }

    private static boolean check(String w, Map<Character, Integer> map) {
        Map<Character, Integer> clone = new HashMap<>(map);
        for (int i = 0; i < w.length(); ++i) {
            char c = w.charAt(i);
            if (clone.containsKey(c)) {
                if (clone.get(c) == 1) clone.remove(c);
                else clone.put(c, clone.get(c) - 1);
            }
        }
        return clone.isEmpty();
    }
}
