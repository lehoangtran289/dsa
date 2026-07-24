package leetcode.bit;

public class M_3514_NumberOfUniqueXORTripletsII {
    static void main() {
        System.out.println(uniqueXorTriplets(new int[]{1, 2, 3})); // 4
    }

    /**
     * <b>LeetCode 3514 - Number of Unique XOR Triplets II</b>
     *
     * <p><b>Idea:</b><br>
     * Decompose the triplet XOR problem into two passes:
     * <ol>
     *   <li>Enumerate all pairwise XOR values {@code nums[i] ^ nums[j]} (i ≤ j) and record
     *       which values are reachable in a boolean bitmap {@code xorPair}.</li>
     *   <li>For every element {@code nums[k]}, XOR it with every reachable pair value to
     *       produce a candidate triplet XOR, recording distinct results in {@code xorTriplets}.</li>
     * </ol>
     * Finally, count the number of {@code true} entries in {@code xorTriplets}.
     *
     * <p><b>Intuition:</b><br>
     * A direct O(n³) brute-force over all triplets is too slow. Instead, we separate the
     * problem into two cheaper sub-problems: O(n²) to find all pair XORs, and O(n · M)
     * to extend each pair XOR by one more element — where {@code M} is the next power of 2
     * above {@code max(nums)}. Using boolean bitmaps keeps memory and inner-loop work small
     * because XOR values are bounded by the highest set bit of {@code max}.
     *
     * <p><b>Time Complexity:</b> O(n² + n · M), where M = 2^(⌊log₂(max)⌋ + 1) ≈ 2 · max(nums).
     * <ul>
     *   <li>Building {@code xorPair}: O(n²)</li>
     *   <li>Building {@code xorTriplets}: O(n · M)</li>
     *   <li>Counting results: O(M)</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b> O(M) for the two boolean arrays {@code xorPair} and
     * {@code xorTriplets}, each of size M.
     *
     * @param nums input array of non-negative integers
     * @return the number of distinct values achievable as {@code nums[i] ^ nums[j] ^ nums[k]}
     * for any indices {@code 0 ≤ i ≤ j ≤ k < n}
     */
    public static int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int num : nums) max = Math.max(max, num);

        // build all pair's XOR values
        int msbPos = getMsbPos(max);
        boolean[] xorPair = new boolean[1 << (msbPos + 1)];
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                xorPair[nums[i] ^ nums[j]] = true;
            }
        }

        // build all triplet's XOR values by fixing i
        boolean[] xorTriplets = new boolean[xorPair.length];
        for (int num : nums) {
            for (int j = 0; j < xorPair.length; ++j) {
                if (xorPair[j]) xorTriplets[num ^ j] = true;
            }
        }

        int res = 0;
        for (boolean xor : xorTriplets) {
            if (xor) res++;
        }
        return res;
    }

    private static int getMsbPos(int num) {
        int res = 0;
        while ((num >> 1) != 0) {
            res++;
            num >>= 1;
        }
        return res;
    }
}
