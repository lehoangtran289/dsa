package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E_2570_MergeTwo2DArraysBySummingValues {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(
                mergeArrays(new int[][]{{1, 2}, {2, 3}, {4, 5}}, new int[][]{{1, 4}, {3, 2}, {4, 1}}))
        );
    }

    public static int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int p1 = 0, p2 = 0;

        List<int[]> resultList = new ArrayList<>();

        while (p1 < len1 && p2 < len2) {
            if (nums1[p1][0] < nums2[p2][0]) {
                resultList.add(nums1[p1]);
                p1++;
            } else if (nums1[p1][0] > nums2[p2][0]) {
                resultList.add(nums2[p2]);
                p2++;
            } else {
                int index = nums1[p1][0];
                int sum = nums1[p1][1] + nums2[p2][1];
                resultList.add(new int[]{index, sum});
                p1++;
                p2++;
            }
        }

        while (p1 < len1) {
            resultList.add(nums1[p1]);
            p1++;
        }

        while (p2 < len2) {
            resultList.add(nums2[p2]);
            p2++;
        }

        return resultList.toArray(new int[0][]);
    }
}
