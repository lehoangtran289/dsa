package leetcode.bit;

import java.util.ArrayList;
import java.util.List;

public class M_320P_GeneralizedAbbreviation {
    static void main() {
        System.out.println(generateAbbreviations("word"));
        // Output: ["4", "3d", "2r1", "2rd", "1o2", "1o1d", "1or1", "1ord", "w3", "w2d", "w1r1", "w1rd", "wo2", "wo1d", "wor1", "word"]
    }

    /**
     * Bitmask, generate all masks and generate corresponding abbreviation word for each mask.
     * ---
     * TC: O(2^n), where n is the length of word
     * SC: O(n) - result
     */
    public static List<String> generateAbbreviations(String word) {
        int n = word.length();
        List<String> res = new ArrayList<>();
        int mask = 1 << n;

        for (int i = 0; i < mask; ++i) {
            StringBuilder sb = new StringBuilder();
            int count = 0;

            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) != 0) {
                    count++;
                } else {
                    if (count > 0) sb.append(count);
                    sb.append(word.charAt(j));
                    count = 0;
                }
            }
            if (count > 0) sb.append(count);

            res.add(sb.toString());
        }

        return res;
    }
}
