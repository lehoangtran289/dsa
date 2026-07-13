package leetcode.backtrack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class M_291P_WordPatternII {

    private String pattern;
    private String target;
    private Set<String> seenString;
    private Map<Character, String> symbolStringMap;

    /**
     * backtrack target string
     * ---
     * TC: O(p * n^3), p is pattern length & n is target length
     * For each pIndex, we traverse every possible sIndex -> O(p)
     * At each recursion,
     * for each sIndex -> we traverse every possible sEndIndex -> O(n^2)
     * And substring() -> O(n)
     * -> overall is O(n^3)
     * SC: O(p + n)
     */
    public boolean wordPatternMatch(String pattern, String s) {
        this.pattern = pattern;
        this.target = s;
        this.seenString = new HashSet<>();
        this.symbolStringMap = new HashMap<>();

        return backtrack(0, 0);
    }

    private boolean backtrack(int pIndex, int sIndex) {
        // base cases
        if (pIndex == pattern.length() && sIndex == target.length()) {
            return true;
        }

        if (pIndex == pattern.length() || sIndex == target.length() || pattern.length() - pIndex > target.length() - sIndex) {
            return false;
        }

        // traverse s and backtrack
        char symbol = pattern.charAt(pIndex);

        for (int sEndIndex = sIndex + 1; sEndIndex <= target.length(); ++sEndIndex) {
            String curString = target.substring(sIndex, sEndIndex);

            // if symbol has a mapping
            if (symbolStringMap.containsKey(symbol)) {
                // if that mapping matches current string
                if (symbolStringMap.get(symbol).equals(curString) && backtrack(pIndex + 1, sEndIndex)) {
                    return true;
                }
                continue;
            }

            // if symbol not have mapping && curString not been seen
            if (!seenString.contains(curString)) {
                symbolStringMap.put(symbol, curString);
                seenString.add(curString);

                if (backtrack(pIndex + 1, sEndIndex))
                    return true;

                symbolStringMap.remove(symbol);
                seenString.remove(curString);
            }
        }

        return false;
    }
}
