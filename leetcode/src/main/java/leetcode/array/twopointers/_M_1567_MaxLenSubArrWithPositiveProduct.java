package leetcode.array.twopointers;

public class _M_1567_MaxLenSubArrWithPositiveProduct {
    public static void main(String[] args) {
        _M_1567_MaxLenSubArrWithPositiveProduct obj = new _M_1567_MaxLenSubArrWithPositiveProduct();
        int[] nums = new int[] {-1, -2, -3, 0, 1};
        System.out.println(obj.getMaxLen(nums)); // 2
    }

    public int getMaxLen(int[] nums) {
        int maxLen = 0;
        int l = 0, r = 0;

        int negCount = 0;
        int firstNeg = -1, lastNeg = -1;

        while (r < nums.length) {
            if (nums[r] < 0) {
                firstNeg = firstNeg == -1 ? r : firstNeg;
                lastNeg = r;
                negCount++;
            }

            if (nums[r] == 0) {
                int curLen = negCount % 2 == 0 ? r - l : Math.max(r - firstNeg - 1, lastNeg - l);
                maxLen = Math.max(curLen, maxLen);

                // reset
                negCount = 0;
                firstNeg = -1;
                lastNeg = -1;

                // next subarr
                l = r + 1;
                r = l;
                continue;
            }
            r++;
        }
        int curLen = negCount % 2 == 0 ? r - l : Math.max(r - firstNeg - 1, lastNeg - l);
        System.out.println(curLen);
        return Math.max(maxLen, curLen);
    }
}
