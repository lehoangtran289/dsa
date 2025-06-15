package leetcode.math;

public class M_1432_MaxDifferenceYouCanGetFromChangingAnInteger {
    public static void main(String[] args) {
        System.out.println(maxDiff(1101057)); // 8808050
        System.out.println(maxDiff(9089733)); // 8908000
    }

    /**
     * Given a positive integer num, you can apply one operation to it:
     * Replace any digit by any other digit.
     * Result must not contain leading zeros
     * ---------------------
     * Idea: MAX -> remap the first digit != 9 to 9 for max
     *      MIN -> remap the first digit to 0 if not 1, or next non-0/1 digit to 0
     */
    public static int maxDiff(int num) {
        String numStr = num + "";
        int n = numStr.length();

        // find max by replace first non-9 digit with 9
        int maxReplaceDigit = -1;

        for (char c : numStr.toCharArray()) {
            int curDigit = c - '0';

            if (curDigit != 9) {
                maxReplaceDigit = curDigit;
                break;
            }
        }
        int max = replaceWith(numStr, maxReplaceDigit, 9);

        // find min by replace
        //      first digit (if not 1) with 1
        //      or next non-0/1 digit with 0
        int minReplaceDigit = -1;
        int minTarget = 1;

        for (int i = 0; i < n; ++i) {
            int curDigit = numStr.charAt(i) - '0';

            if (curDigit != 1 && curDigit != 0) {
                minReplaceDigit = curDigit;

                if (i != 0) minTarget = 0;
                break;
            }
        }
        int min = replaceWith(numStr, minReplaceDigit, minTarget);

        return max - min;
    }

    // helper function to replace digits in a number string
    private static int replaceWith(String numStr, int from, int to) {
        int res = 0;

        for (char c : numStr.toCharArray()) {
            int curDigit = c - '0';

            res = res * 10 + (curDigit == from ? to : curDigit);
        }

        return res;
    }
}
