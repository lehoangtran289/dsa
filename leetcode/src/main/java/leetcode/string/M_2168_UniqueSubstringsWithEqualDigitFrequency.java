package leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class M_2168_UniqueSubstringsWithEqualDigitFrequency {
    public static void main(String[] args) {
        System.out.println(equalDigitFrequency("1102021222")); // 16
    }

    /**
     * 1. Iterate through all substrings of s.
     * 2. For each substring, calculate the frequency of each digit.
     * 3. If all digits have the same frequency, add the substring to the result set.
     * 4. Return the size of the result set.
     */
    public static int equalDigitFrequency(String s) {
        int n = s.length();
        Set<String> res = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            Map<Character, Integer> freq = new HashMap<>();
            for (int j = i; j < n; ++j) {
                char c = s.charAt(j);
                freq.put(c, freq.getOrDefault(c, 0) + 1);

                boolean isValid = true;
                int curFreq = -1;
                for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                    if (curFreq == -1) curFreq = entry.getValue();

                    if (curFreq != entry.getValue()) isValid = false;
                }

                if (isValid) {
                    String str = s.substring(i, j + 1);
                    res.add(str);
                }
            }
        }

        return res.size();
    }
}
