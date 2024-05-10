package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _349_IntersectionOfTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> result = new HashSet<>();

        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) i++;
            else if (nums2[j] < nums1[i]) j++;
            else {
                result.add(nums1[i]);
                i++;
                j++;
            }
        }

        return result.stream().mapToInt(n -> n).toArray();
    }
}
