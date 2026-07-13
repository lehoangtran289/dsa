package leetcode.array.slidingwindow;

import java.util.ArrayList;
import java.util.List;

public class M_1291_SequentialDigits {
    public static void main(String[] args) {
        int low = 100, high = 300;
        System.out.println(sequentialDigits(low, high)); // Output: [123, 234]
        System.out.println(sequentialDigits2(low, high)); // Output: [123, 234]
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    public static List<Integer> sequentialDigits2(int low, int high) {
        final String sample = "123456789";
        List<Integer> res = new ArrayList<>();

        int minLength = String.valueOf(low).length();
        int maxLength = String.valueOf(high).length();

        for (int len = minLength; len <= maxLength; ++len) {
            for (int start = 0; start < 10 - len; ++start) {
                int num = Integer.parseInt(sample.substring(start, start + len));
                if (num >= low && num <= high) res.add(num);
            }
        }

        return res;
    }

    public static List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        int minLength = (int) Math.log10(low) + 1;
        int maxLength = (int) Math.log10(high) + 1;

        for (int length = minLength; length <= maxLength; ++length) {
            int curNum = 0;

            // process first i
            for (int i = 1; i <= length; ++i) {
                curNum = curNum * 10 + i;
            }
            if (curNum >= low && curNum <= high) res.add(curNum);

            // process the rest
            int pow = (int) Math.pow(10, length - 1);
            for (int i = length + 1; i <= 9; ++i) {
                curNum = (curNum - (i - length) * pow) * 10 + i;

                if (curNum >= low && curNum <= high) res.add(curNum);
            }
        }

        return res;
    }
}
