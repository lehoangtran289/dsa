package leetcode.array.twopointers;

import java.util.ArrayList;
import java.util.List;

public class E_88_MergeSortedArray {
    public static void main(String[] args) {
        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        List<Integer> res = new ArrayList<>();

        int p1 = 0, p2 = 0;
        while (p1 < m && p2 < n) {
            if (nums1[p1] < nums2[p2]) {
                res.add(nums1[p1]);
                p1++;
            } else {
                res.add(nums2[p2]);
                p2++;
            }
        }

        while (p1 < m) {
            res.add(nums1[p1]);
            p1++;
        }

        while (p2 < n) {
            res.add(nums2[p2]);
            p2++;
        }

        for (int i = 0; i < m + n; ++i) {
            nums1[i] = res.get(i);
        }
    }
}
