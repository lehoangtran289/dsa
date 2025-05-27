package leetcode.array.twopointers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class M_658_FindKClosestElements {

    public static void main(String[] args) {
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3)); // [1, 2, 3, 4]
    }

    /**
     * Binary search + 2 pointers
     * TC: O(logN + KlogK)
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        List<Integer> res = new ArrayList<>();

        // binary search to find closest element
        // TC: O(logN)
        int l = 0, r = n - 1;
        int midIndex = 0;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // update midIndex to closer element
            if (Math.abs(arr[mid] - x) < Math.abs(arr[midIndex] - x)) {
                midIndex = mid;
            } else if (Math.abs(arr[mid] - x) == Math.abs(arr[midIndex] - x)) {
                midIndex = Math.min(midIndex, mid);
            }

            if (arr[mid] > x) {
                r = mid - 1;
            } else if (arr[mid] < x) {
                l = mid + 1;
            }  else {
                midIndex = mid;
                break;
            }
        }

        // 2 pointers to find kth closest
        // TC: O(k)
        res.add(arr[midIndex]);
        k--;
        int p1 = midIndex - 1, p2 = midIndex + 1;

        while (p1 >= 0 && p2 <= n - 1 && k-- > 0) {
            if (Math.abs(arr[p1] - x) > Math.abs(arr[p2] - x)) {
                res.add(arr[p2]);
                p2++;
            } else {
                res.add(arr[p1]);
                p1--;
            }
        }

        while (p1 >= 0 && k-- > 0) {
            res.add(arr[p1--]);
        }

        while (p2 <= n && k-- > 0) {
            res.add(arr[p2++]);
        }

        // TC: O(KlogK)
        Collections.sort(res);
        return res;
    }
}
