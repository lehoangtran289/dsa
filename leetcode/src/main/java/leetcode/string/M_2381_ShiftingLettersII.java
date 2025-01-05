package leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class M_2381_ShiftingLettersII {
    public static void main(String[] args) {
        System.out.println(shiftingLetters2("dztz", new int[][]{{0, 0, 0}, {1, 1, 1}})); // catz
        System.out.println(shiftingLetters2("abc", new int[][]{{0, 1, 0}, {1, 2, 1}, {0, 2, 1}})); // ace
    }

    // Line sweep
    public static String shiftingLetters2(String s, int[][] shifts) {
        int n = s.length();
        int[] diffArray = new int[n]; // Initialize a difference array with all elements set to 0.

        // Process each shift operation
        for (int[] shift : shifts) {
            if (shift[2] == 1) { // If direction is forward (1)
                diffArray[shift[0]]++; // Increment at the start index
                if (shift[1] + 1 < n) {
                    diffArray[shift[1] + 1]--; // Decrement at the end+1 index
                }
            } else { // If direction is backward (0)
                diffArray[shift[0]]--; // Decrement at the start index
                if (shift[1] + 1 < n) {
                    diffArray[shift[1] + 1]++; // Increment at the end+1 index
                }
            }
        }

        StringBuilder result = new StringBuilder(s);
        int amount = 0;

        // Apply the shifts to the string
        for (int i = 0; i < n; i++) {
            amount = (amount + diffArray[i]) % 26; // Update cumulative shifts, keeping within the alphabet range
            result.setCharAt(i, shift(s.charAt(i), Math.abs(amount), amount < 0 ? 0 : 1));
        }

        return result.toString();
    }

    // TLE
    public static String shiftingLetters(String s, int[][] shifts) {
        Map<Integer, Integer> map = new HashMap<>(); // index, <count, dir>
        for (int[] shift : shifts) {
            for (int i = shift[0]; i <= shift[1]; ++i) {
                map.put(i, map.getOrDefault(i, 0) + (shift[2] == 1 ? 1 : -1));
            }
        }

        StringBuilder sb = new StringBuilder(s);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int index = entry.getKey();
            int shift = entry.getValue();
            if (shift == 0) continue;

            if (shift > 0) {
                sb.setCharAt(index, shift(sb.charAt(index), shift, 1));
            } else {
                sb.setCharAt(index, shift(sb.charAt(index), -shift, 0));
            }
        }

        return sb.toString();
    }

    private static char shift(char c, int amount, int dir) {
        int shift = amount % 26;
        if (dir == 1) {
            return (char) ('a' + (c - 'a' + shift) % 26);
        } else {
            return (char) ('a' + (c - 'a' + 26 - shift) % 26);
        }
    }
}
