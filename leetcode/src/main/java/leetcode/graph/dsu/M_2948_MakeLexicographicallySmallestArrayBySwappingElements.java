package leetcode.graph.dsu;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class M_2948_MakeLexicographicallySmallestArrayBySwappingElements {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(lexicographicallySmallestArray(new int[]{1, 7, 6, 18, 2, 1}, 3)));
    }

    public static int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // clone input and sort
        int[] numsClone = new int[nums.length];
        for (int i = 0; i < nums.length; i++) numsClone[i] = nums[i];
        Arrays.sort(numsClone);

        // construct DSU
        DisjointSet dsu = new DisjointSet(numsClone);
        for (int i = 1; i < n; ++i) {
            if (numsClone[i] - numsClone[i - 1] <= limit) {
                dsu.union(numsClone[i], numsClone[i - 1]);
            }
        }

        // construct groups of sorted elements
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int root = dsu.find(numsClone[i]);
            map.putIfAbsent(root, new ArrayDeque<>());
            map.get(root).add(numsClone[i]);
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
        private final Map<Integer, Integer> parent;

        public DisjointSet(int[] nums) {
            parent = new HashMap<>();
            for (int num : nums) {
                parent.put(num, num);
            }
        }

        public int find(int x) {
            if (parent.get(x) != x) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent.put(rootY, rootX);
            }
        }
    }

}
