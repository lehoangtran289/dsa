package leetcode.string;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class M_1980_FindUniqueBinaryString {

    /**
     * Ensure constructed string differs from each string in nums at least 1 position
     * ----
     * TC: O(n) where n is the length of the input array
     * SC: O(n) for the result string
     */
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < nums.length; ++i) {
            res.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }

        return res.toString();
    }

    /**
     * Simulation
     * ---
     * TC: O(n * 2^n) where n is the length of the input array, since we need to check all possible binary strings of length n
     * SC: O(n) for the seen set and the result string
     */
    public String findDifferentBinaryString0(String[] nums) {
        int n = nums.length;
        Set<String> seen = new HashSet<>();
        Collections.addAll(seen, nums);

        for (int i = 0; i <= Math.pow(2, n); ++i) {
            String numStr = toBinaryString(i, n);
            if (!seen.contains(numStr)) return numStr;
        }

        return "";
    }

    private String toBinaryString(int num, int n) {
        StringBuilder res = new StringBuilder();

        while (num > 0) {
            res.append(num % 2);
            num >>= 1;
        }

        while (res.length() < n) {
            res.append(0);
        }

        return res.reverse().toString();
    }
}
