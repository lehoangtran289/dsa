package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class E_1408_StringMatchingInAnArray {
    public static void main(String[] args) {

    }

    public static List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            for (int j = 0; j < words.length; ++j) {
                if (i == j) continue;
                if (words[j].contains(word)) {
                    res.add(word);
                    break;
                }
            }
        }
        return res;
    }
}
