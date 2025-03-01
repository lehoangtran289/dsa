package leetcode.array.slidingwindow;

public class M_1151_MinimumSwapToGroupAll1Together {
    public static void main(String[] args) {
        System.out.println(minSwaps(new int[]{1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1}));
    }

    public static int minSwaps(int[] data) {
        // count all ones in data
        int oneCount = 0;
        for (int n : data) {
            if (n == 1) oneCount++;
        }

        int curZeroCount = 0;

        // process first window
        for (int i = 0; i < oneCount; ++i) {
            if (data[i] == 0) curZeroCount++;
        }

        int res = curZeroCount;

        // sliding window
        for (int r = oneCount; r < data.length; ++r) {
            if (data[r] == 0) curZeroCount++;
            if (data[r - oneCount] == 0) curZeroCount--;
            res = Math.min(res, curZeroCount);
        }

        return res;
    }
}
