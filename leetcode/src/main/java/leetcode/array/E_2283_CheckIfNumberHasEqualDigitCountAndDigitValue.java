package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class E_2283_CheckIfNumberHasEqualDigitCountAndDigitValue {
    public boolean digitCount(String num) {
        Map<Integer, Integer> freq = new HashMap<>();

        int[] nums = new int[num.length()];
        for (int i = 0; i < num.length(); ++i) {
            int digit = num.charAt(i) - '0';
            nums[i] = digit;
            freq.put(digit, freq.getOrDefault(digit, 0) + 1);
        }

        for (int i = 0; i < nums.length; ++i) {
            if (freq.getOrDefault(i, 0) != nums[i]) {
                return false;
            }
        }

        return true;
    }
}
