package leetcode.array.prefixSum;

public class M_769_MaxChunksToMakeSorted {
    static void main() {
        System.out.println(maxChunksToSorted(new int[]{2, 0, 1})); // 1
        System.out.println(maxChunksToSorted(new int[]{1, 0, 2})); // 2
    }

    /**
     * Idea: Since arr is permutation of [0...n-1]
     * -> Final sorted array = max of chunk (i-1)-th < min of chunk i-th
     * -> Use prefix max and suffix min array
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];

        prefixMax[0] = arr[0];
        suffixMin[n - 1] = arr[n - 1];

        for (int i = 1; i < n; ++i) {
            prefixMax[i] = Math.max(prefixMax[i - 1], arr[i]);
            suffixMin[n - 1 - i] = Math.min(suffixMin[n - i], arr[n - 1 - i]);
        }

        int res = 1;
        for (int i = 0; i < n - 1; ++i) {
            if (prefixMax[i] < suffixMin[i + 1]) res++;
        }
        return res;
    }

    /**
     * Idea: Since arr is permutation of [0...n-1]
     * -> at index i-th, if max[0...i] == i -> all elements from [0...i] already in segment [0..i]
     * -> Make a chunk
     * ---
     * TC: O(N)
     * SC: O(1)
     */
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        int maxSoFar = arr[0];
        int res = 0;

        for (int i = 0; i < n; ++i) {
            maxSoFar = Math.max(maxSoFar, arr[i]);
            if (maxSoFar == i) res++;
        }
        return res;
    }
}
