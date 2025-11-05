package leetcode.array.slidingwindow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class H_3321_FindXSumOfAllKLongSubarraysII {
    // store pair of <value, frequency>
    Comparator<int[]> comparator;
    /**
     * Idea: Sliding Window + Two Balanced TreeSets
     * - Maintain 2 TreeSets for TOP X frequent elements and remaining elements
     * - Update frequency map and TreeSets as the window slides
     * ---------------------------
     * TC: O(n log n) - log n for TreeSet operations
     * SC: O(n)
     */
    private Map<Integer, Integer> freqMap;
    private long curSum;
    private TreeSet<int[]> topSet; // maintain top x
    private TreeSet<int[]> remainSet; // remaining elements

    public static void main(String[] args) {
        H_3321_FindXSumOfAllKLongSubarraysII solution = new H_3321_FindXSumOfAllKLongSubarraysII();
        System.out.println(Arrays.toString(
                solution.findXSum(new int[]{1, 1, 2, 2, 3, 4, 2, 3}, 6, 2))
        ); // [6, 10, 12]
    }

    public long[] findXSum(int[] nums, int k, int x) {
        this.freqMap = new HashMap<>();
        this.curSum = 0;
        this.comparator = (a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1];
        this.topSet = new TreeSet<>(comparator);
        this.remainSet = new TreeSet<>(comparator);

        int n = nums.length;
        long[] res = new long[n - k + 1];

        // process first k
        for (int i = 0; i < k; ++i) {
            removeFromSets(nums[i]);
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
            insertToSets(nums[i]);

            // keep topSet x elements
            balanceSets(x);
        }
        res[0] = curSum;

        // process remaining using sliding window
        for (int i = 1; i < n - k + 1; ++i) {
            // add current element into window
            int curNum = nums[i + k - 1];
            removeFromSets(curNum);
            freqMap.put(curNum, freqMap.getOrDefault(curNum, 0) + 1);
            insertToSets(curNum);

            // handle previous out-of-window elements
            int prevNum = nums[i - 1];
            removeFromSets(prevNum);
            freqMap.put(prevNum, freqMap.getOrDefault(prevNum, 0) - 1);

            if (freqMap.get(prevNum) <= 0) freqMap.remove(prevNum);
            else insertToSets(prevNum);

            // keep topSet x elements
            balanceSets(x);

            res[i] = curSum;
        }

        return res;
    }

    private void insertToSets(int num) {
        int[] pair = new int[]{num, freqMap.getOrDefault(num, 0)};

        if (!topSet.isEmpty() && comparator.compare(topSet.last(), pair) > 0) {
            topSet.add(pair);
            curSum += (long) pair[0] * pair[1];
        } else {
            remainSet.add(pair);
        }
    }

    private void removeFromSets(int num) {
        int[] pair = new int[]{num, freqMap.getOrDefault(num, 0)};

        if (topSet.contains(pair)) {
            topSet.remove(pair);
            curSum -= (long) pair[0] * pair[1];
        } else if (remainSet.contains(pair)) {
            remainSet.remove(pair);
        }
    }

    private void balanceSets(int x) {
        while (!topSet.isEmpty() && topSet.size() > x) {
            int[] last = topSet.last();
            curSum -= (long) last[0] * last[1];
            topSet.remove(last);
            remainSet.add(last);
        }

        while (!remainSet.isEmpty() && topSet.size() < x) {
            int[] top = remainSet.first();
            curSum += (long) top[0] * top[1];
            topSet.add(top);
            remainSet.remove(top);
        }
    }
}
