package leetcode.array.twopointers;

import java.util.ArrayList;
import java.util.List;

public class M_658_FindKClosestElements {

    static void main() {
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3)); // [1, 2, 3, 4]
    }

    /**
     * Idea: find element in arr that is closest to x, then expand both directions to find boundary.
     * ---
     * TC: O(logN + K)
     * SC: O(K)
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        // find index where x belongs in arr
        int midIndex = binarySearch(arr, x);

        int p1 = midIndex, p2 = midIndex + 1;
        while (p1 >= 0 && p2 < arr.length && k-- > 0) {
            if (Math.abs(arr[p1] - x) <= Math.abs(arr[p2] - x)) {
                p1--;
            } else {
                p2++;
            }
        }

        while (p1 >= 0 && k-- > 0) {
            p1--;
        }

        while (p2 < arr.length && k-- > 0) {
            p2++;
        }

        // build result from boundaries
        List<Integer> result = new ArrayList<>();
        for (int i = p1 + 1; i < p2; ++i) {
            result.add(arr[i]);
        }
        return result;
    }

    private static int binarySearch(int[] arr, int x) {
        int result = 0;
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                result = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return result;
    }
}
