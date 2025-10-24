package leetcode.array.twopointers;

import java.util.ArrayList;
import java.util.List;

public class M_1570_DotProductOfTwoSparseVectors {
    public static void main(String[] args) {
        SparseVector v1 = new SparseVector(new int[]{1, 0, 0, 2, 3});
        SparseVector v2 = new SparseVector(new int[]{0, 3, 0, 4, 0});
        System.out.println(v1.dotProduct(v2)); // 8
    }

    /**
     * Sparse Vector
     * ----------------
     * Idea: Two Pointers
     * ----------------
     * TC: O(n + m) where n and m are the number of non-zero elements in the two sparse vectors.
     * SC: O(n + m) for storing the non-zero elements.
     */
    static class SparseVector {
        private final List<int[]> pairs;

        SparseVector(int[] nums) {
            this.pairs = new ArrayList<>();

            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] != 0)
                    this.pairs.add(new int[]{i, nums[i]});
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector other) {
            int res = 0;
            int p1 = 0, p2 = 0;

            while (p1 < pairs.size() && p2 < other.pairs.size()) {
                if (pairs.get(p1)[0] == other.pairs.get(p2)[0]) {
                    res += pairs.get(p1)[1] * other.pairs.get(p2)[1];
                    p1++;
                    p2++;
                } else if (pairs.get(p1)[0] > other.pairs.get(p2)[0]) {
                    p2++;
                } else {
                    p1++;
                }
            }

            return res;
        }
    }
}
