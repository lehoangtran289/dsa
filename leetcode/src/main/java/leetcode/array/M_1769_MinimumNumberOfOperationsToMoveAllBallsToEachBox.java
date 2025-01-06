package leetcode.array;

import java.util.Arrays;

public class M_1769_MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(minOperations("001011"))); // [11,8,5,4,3,4]
        System.out.println(Arrays.toString(minOperations("110"))); // [1,1,3]
    }

    public static int[] minOperations(String boxes) {
        int len = boxes.length();
        int[] res = new int[len];
        int left = 0, right = 0, count = 0;

        for (int i = 0; i < len; ++i) {
            res[i] = left;
            if (boxes.charAt(i) == '1') {
                count++;
            }
            left += count;
        }

        count = 0;
        for (int i = len - 1; i >= 0; --i) {
            res[i] += right;
            if (boxes.charAt(i) == '1') {
                count++;
            }
            right += count;
        }

        return res;
    }

    // brute force
    public static int[] minOperations2(String boxes) {
        int len = boxes.length();
        int[] res = new int[len];

        for (int i = 0; i < len; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                if (boxes.charAt(j) == '1') res[i] += (i - j);
            }
            for (int j = i + 1; j < len; ++j) {
                if (boxes.charAt(j) == '1') res[i] += (j - i);
            }
        }

        return res;
    }
}
