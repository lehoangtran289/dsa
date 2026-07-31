package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_17_LetterCombinationsOfAPhoneNumber {
    private final String[] mappings = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private List<String> res;
    private String digits;

    static void main() {
        System.out.println(new M_17_LetterCombinationsOfAPhoneNumber().letterCombinations("23"));
        System.out.println(new M_17_LetterCombinationsOfAPhoneNumber().letterCombinations("2"));
    }

    public List<String> letterCombinations(String digits) {
        this.res = new ArrayList<>();
        this.digits = digits;

        backtrack(0, new StringBuilder());

        return res;
    }

    private void backtrack(int pos, StringBuilder curSb) {
        if (pos >= digits.length()) {
            res.add(curSb.toString());
            return;
        }

        int curDigit = digits.charAt(pos) - '2';
        for (char c : mappings[curDigit].toCharArray()) {
            curSb.append(c);
            backtrack(pos + 1, curSb);
            curSb.setLength(curSb.length() - 1);
        }
    }
}
