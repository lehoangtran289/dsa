package leetcode.array;

public class M_2762_ContinuousSubarrays {
    public static void main(String[] args) {
        System.out.println(continuousSubarrays(new int[]{65, 66, 65, 64, 63, 62, 62})); // 20
        System.out.println(continuousSubarrays(new int[]{5, 4, 2, 4})); // 8
    }

    public static long continuousSubarrays(int[] nums) {
        long res = 0;
        int n = nums.length;

        int l = 0, r = 0, windowSize = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        while (r < n) {
            max = Math.max(max, nums[r]);
            min = Math.min(min, nums[r]);

            if (max - min > 2) {
                windowSize = r - l; // not + 1 because ++r
                res += ((long) windowSize * (windowSize + 1)) / 2;

                // start new window at r
                l = r;
                max = min = nums[r];

                // expand window to the left
                while (l > 0) {
                    if (Math.abs(nums[r] - nums[l - 1]) <= 2) break;

                    l--;
                    min = Math.min(min, nums[l]);
                    max = Math.max(max, nums[l]);
                }
                windowSize = r - l; // not + 1 because r is not included
                res -= ((long) windowSize * (windowSize + 1)) / 2;
            }
            ++r;
        }

        windowSize = r - l; // not + 1 because ++r
        res += ((long) windowSize * (windowSize + 1)) / 2;

        return res;
    }

    /**
     * Sliding window technique
     * for(int l=0, r=0; r<n; r++){
     * do_something_add(nums[r])
     * while(l<r and !condition(k:=2)) {
     * do_something_remove(nums[l]);
     * l++;
     * }
     * update_answer(r, l);
     * }
     */
    public long continuousSubarraysBruteForce(int[] nums) {
        long res = 0;
        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            int j = i;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            while (j < n) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                if (max - min >= 0 && max - min <= 2) {
                    res++;
                } else {
                    break;
                }
                ++j;
            }
        }

        return res;
    }
}
