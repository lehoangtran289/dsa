package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class H_761_SpecialBinaryString {

    /**
     * Recursion + sorting
     * -----
     * TC: O(n log n) where n is the length of the input string, due to sorting
     * SC: O(n) for the recursion stack and the list of special substrings
     */
    public String makeLargestSpecial(String s) {
        if (s.isEmpty()) return "";

        List<String> specialSubstrings = new ArrayList<>();
        int count = 0, startIndex = 0;

        for (int i = 0; i < s.length(); ++i) {
            count += s.charAt(i) == '1' ? 1 : -1;

            if (count == 0) {
                String inner = makeLargestSpecial(s.substring(startIndex + 1, i));
                specialSubstrings.add("1" + inner + "0");
                startIndex = i + 1;
            }
        }

        specialSubstrings.sort((a, b) -> b.compareTo(a));

        StringBuilder res = new StringBuilder();
        for (var str : specialSubstrings) res.append(str);
        return res.toString();
    }
}
