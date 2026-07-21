package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_320P_GeneralizedAbbreviation {

    static void main() {
        System.out.println(generateAbbreviations("word"));
        // [4, 3d, 2r1, 2rd, 1o2, 1o1d, 1or1, 1ord, w3, w2d, w1r1, w1rd, wo2, wo1d, wor1, word]
    }

    /**
     * backtracking. 2 options at each character: either abbreviate it or skip it
     * ---
     * TC: O(2^n), where n is the length of word
     * SC: O(n)
     */
    public static List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        backtrack(word, res, new StringBuilder(), 0, 0);
        return res;
    }

    private static void backtrack(
            String word,
            List<String> res,
            StringBuilder curWord,
            int start,
            int abbrCount
    ) {
        if (start == word.length()) {
            int prevLength = curWord.length();
            if (abbrCount > 0) curWord.append(abbrCount);
            res.add(curWord.toString());
            curWord.setLength(prevLength);
            return;
        }

        // case 1: take current char to abbr
        backtrack(word, res, curWord, start + 1, abbrCount + 1);

        // case 2: skip this char
        int prevLength = curWord.length();
        if (abbrCount > 0) {
            curWord.append(abbrCount);
        }
        curWord.append(word.charAt(start));
        backtrack(word, res, curWord, start + 1, 0);
        curWord.setLength(prevLength);
    }

    // -----------------------------------------

    /**
     * No StringBuilder -> No need to "undo", but memory-inefficient
     */
    public static List<String> generateAbbreviations2(String word) {
        List<String> res = new ArrayList<>();
        backtrack2(word, res, "", 0, 0);
        return res;
    }

    private static void backtrack2(
            String word,
            List<String> res,
            String curWord,
            int start,
            int abbrCount
    ) {
        if (start == word.length()) {
            res.add(abbrCount > 0 ? curWord + abbrCount : curWord);
            return;
        }

        // case 1: take current char to abbr
        backtrack2(word, res, curWord, start + 1, abbrCount + 1);

        // case 2: skip this char
        curWord = curWord + (abbrCount > 0 ? abbrCount : "") + word.charAt(start);
        backtrack2(word, res, curWord, start + 1, 0);
    }
}
