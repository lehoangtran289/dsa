package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_3132_FindTheIntegerAddedToArray2 {
    public static void main(String[] args) {
        System.out.println(minimumAddedInteger(new int[]{9,4,3,9,4}, new int[]{7,8,8})); // 4
        System.out.println(minimumAddedInteger(new int[]{6,3,6,7}, new int[]{6,2})); // -1
        System.out.println(minimumAddedInteger(new int[]{4,20,16,12,8}, new int[]{14,18,10})); // -2
    }

    public static int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Map<Integer, Integer> map = new HashMap<>();
        int max2 = -1;
        for (int n : nums2) {
            max2 = Math.max(max2, n);
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for (int i = 0; i < nums1.length - 1; ++i) {
            for (int j = i + 1; j < nums1.length; ++j) {
                int max1 = -1;
                for (int k = nums1.length - 1; k >= 0; --k) {
                    if (k == i || k == j) continue;
                    max1 = nums1[k];
                    break;
                }

                if (check(nums1, i, j, max2 - max1, map)) {
                    return max2 - max1;
                }
            }
        }

        return 0;
    }

    public static boolean check(int[] nums1, int i, int j, int x, Map<Integer, Integer> map) {
        Map<Integer, Integer> cloneMap = new HashMap<>(map);
        for (int k = 0; k < nums1.length; ++k) {
            if (k == i || k == j) continue;

            int num1 = nums1[k] + x;
            if (!cloneMap.containsKey(num1)) {
                return false;
            } else {
                if (cloneMap.get(num1) == 1) cloneMap.remove(num1);
                else cloneMap.put(num1, cloneMap.get(num1) - 1);
            }
        }
        return true;
    }
}
