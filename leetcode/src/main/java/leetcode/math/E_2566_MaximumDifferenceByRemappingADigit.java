package leetcode.math;

public class E_2566_MaximumDifferenceByRemappingADigit {
    public static void main(String[] args) {
        System.out.println(minMaxDifference(11891)); // 99899 - 00890 = 99009
        System.out.println(minMaxDifference2(11891)); // 99899 - 00890 = 99009
    }

    /**
     * find the maximum difference between two numbers formed by remapping 1 digit of num.
     * ---------------------
     * Idea: remap the first digit != 9 to 9 for max, and remap the first digit to 0 for min
     * This solution does not use string manipulation
     * ---------------------
     * TC: O(d), where d is the number of digits in num
     * SC: O(1)
     */
    public static int minMaxDifference(int num) {
        int max = 0, min = 0;
        int numCopy = num;
        int remapDigit = 0, firstDigit = 0;

        // find first digit != 9 and first digit
        while (numCopy > 0) {
            int lastDigit = numCopy % 10;

            if (lastDigit != 9) {
                remapDigit = lastDigit;
            }
            firstDigit = lastDigit;

            numCopy /= 10;
        }

        // build result
        int i = 0;
        while (num > 0) {
            int cur = num % 10;

            max += (cur == remapDigit ? 9 : cur) * (int) Math.pow(10, i);
            min += (cur == firstDigit ? 0 : cur) * (int) Math.pow(10, i);

            num /= 10;
            i++;
        }

        return max - min;
    }

    /**
     * find the maximum difference between two numbers formed by remapping 1 digit of num.
     * ---------------------
     * Idea: remap the first digit != 9 to 9 for max, and remap the first digit to 0 for min
     * This solution uses string manipulation
     * ---------------------
     * TC: O(d), where d is the number of digits in num
     * SC: O(1)
     */
    public static int minMaxDifference2(int num) {
        String numStr = num + "";
        int max = 0, min = 0;
        char remapChar = 0;
        char firstChar = numStr.charAt(0);

        // find first digit != 9
        for (char c : numStr.toCharArray()) {
            if (c - '0' != 9) {
                remapChar = c;
                break;
            }
        }

        // find max & min
        for (char c : numStr.toCharArray()) {
            max *= 10;
            max += c == remapChar ? 9 : c - '0';

            min *= 10;
            min += c == firstChar ? 0 : c - '0';
        }

        return max - min;
    }
}
