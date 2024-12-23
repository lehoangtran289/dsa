package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class E_496_NextGreaterElementI {
    public static void main(String[] args) {
        E_496_NextGreaterElementI solution = new E_496_NextGreaterElementI();
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println(Arrays.toString(solution.nextGreaterElement(nums1, nums2)));
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; ++i) map.put(nums2[i], i);

        for (int i = 0; i < res.length; ++i) {
            res[i] = -1;

            int idx = map.get(nums1[i]);
            for (int j = idx + 1; j < nums2.length; ++j) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }

        return res;
    }
}
