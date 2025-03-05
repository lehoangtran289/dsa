package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_17_LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        System.out.println(new M_17_LetterCombinationsOfAPhoneNumber().letterCombinations("23"));
        System.out.println(new M_17_LetterCombinationsOfAPhoneNumber().letterCombinations("2"));
    }

    private final String[] mappings = {
            "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();

        List<String> res = new ArrayList<>();
        backtrack(res, digits, 0, new StringBuilder());

        return res;
    }

    private void backtrack(
            List<String> res,
            String digits,
            int curId,
            StringBuilder curSb
    ) {
        if (curId == digits.length()) {
            res.add(curSb.toString());
            return;
        }

        int mappingIdx = getIndex(digits.charAt(curId));
        String letters = mappings[mappingIdx];

        for (int i = 0; i < letters.length(); ++i) {
            curSb.append(letters.charAt(i));
            backtrack(res, digits, curId + 1, curSb);
            curSb.deleteCharAt(curSb.length() - 1);
        }
    }

    private int getIndex(char digit) {
        return digit - '2';
    }
}
