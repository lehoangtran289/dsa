package leetcode.array.binarysearch;

public class E_704_BinarySearch {
    public static void main(String[] args) {
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 9)); // 4
    }

    public static int search(int[] nums, int target) {
        int lo = -1, hi = nums.length;
        while (lo + 1 < hi) {
            int mid = hi - (hi - lo) / 2;
            System.out.println(mid);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return -1;
    }
}
