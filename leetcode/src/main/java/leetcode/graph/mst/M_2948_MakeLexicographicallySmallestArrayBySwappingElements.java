package leetcode.graph.mst;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class M_2948_MakeLexicographicallySmallestArrayBySwappingElements {
    static void main() {
        System.out.println(Arrays.toString(lexicographicallySmallestArray2(new int[]{1, 7, 6, 18, 2, 1}, 3)));
    }

    /**
     * Idea: Maintain pair of [num, index] and sort by num (to preserve num indices)
     * ---
     * TC: O(N logN)
     * SC: O(N)
     */
    public static int[] lexicographicallySmallestArray2(int[] nums, int limit) {
        int n = nums.length;
        int[][] pairs = new int[n][2];
        int[] res = new int[n];

        // construct pairs array of [[num, index]]
        for (int i = 0; i < n; ++i) {
            pairs[i] = new int[]{nums[i], i};
        }
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        // group relevant numbers and build result
        int l = 0;
        while (l < n) {
            int r = l;
            while (r < n - 1 && Math.abs(pairs[r][0] - pairs[r + 1][0]) <= limit) {
                r++;
            }

            int windowSize = r - l + 1;
            int[] indices = new int[windowSize];

            for (int i = 0; i < windowSize; ++i) {
                indices[i] = pairs[l + i][1];
            }
            Arrays.sort(indices);

            for (int i = 0; i < windowSize; ++i) {
                res[indices[i]] = pairs[l + i][0];
            }

            l = r + 1;
        }

        return res;
    }

    /**
     * Idea: Use DSU to group numbers in range "limit", then sort each group and assign to original indices
     * ---
     * TC: O(N logN)
     * SC: O(N)
     */
    public static int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // clone input and sort
        int[] clone = new int[nums.length];
        for (int i = 0; i < nums.length; i++) clone[i] = nums[i];
        Arrays.sort(clone);

        // construct DSU
        DisjointSet dsu = new DisjointSet(clone);
        for (int i = 1; i < n; ++i) {
            if (clone[i] - clone[i - 1] <= limit) {
                dsu.union(clone[i], clone[i - 1]);
            }
        }

        // construct groups of sorted elements
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int root = dsu.find(clone[i]);
            map.putIfAbsent(root, new ArrayDeque<>());
            map.get(root).add(clone[i]);
        }

        // construct output
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int root = dsu.find(nums[i]);
            res[i] = map.get(root).poll();
        }

        return res;
    }

    static class DisjointSet {
        Map<Integer, Integer> lab = new HashMap<>();

        DisjointSet(int[] arr) {
            for (int num : arr) {
                lab.putIfAbsent(num, -1);
            }
        }

        int find(int u) {
            return lab.get(u) < 0 ? u : (lab.put(u, find(lab.get(u))));
        }

        void union(int u, int v) {
            int parU = find(u);
            int parV = find(v);
            if (parU == parV) return;

            lab.put(parV, lab.get(parV) + lab.get(parU));
            lab.put(parU, parV);
        }
    }

}
