package leetcode.math;

public class H_3312_SortedGCDPairQueries {

    /**
     * Math + inclusion/exclusion + prefix sum + binary search
     * ---
     * TC: O(n log n + q log n), where n = max(nums), q = queries.length
     * SC: O(n)
     */
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        long[] cnt = new long[max + 1];
        for (int num : nums) {
            cnt[num]++;
        }

        // count of number in nums that is a multiple of i
        for (int i = 1; i <= max; ++i) {
            for (int j = i * 2; j <= max; j += i) {
                cnt[i] += cnt[j];
            }
        }

        // count the number of pair such that gcd of each pair is at least i
        for (int i = 1; i <= max; ++i) {
            cnt[i] = (cnt[i] * (cnt[i] - 1)) / 2;
        }

        // inclusion/exclusion count
        // gcd exactly i = (gcd >= i) - [(gcd == 2 * i) + (gcd == 3 * i) + ...]
        // go backward to not double count
        for (int i = max; i >= 1; --i) {
            for (int j = i * 2; j <= max; j += i) {
                cnt[i] -= cnt[j];
            }
        }

        // prefix sum of gcd pairs
        for (int i = 1; i < cnt.length; ++i) {
            cnt[i] += cnt[i - 1];
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            res[i] = binSearch(cnt, queries[i] + 1);
        }

        return res;
    }

    private int binSearch(long[] arr, long target) {
        int l = 1, r = arr.length - 1;
        int res = -1;

        while (l <= r) {
            int mid = r - (r - l) / 2;
            if (arr[mid] >= target) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }
}
