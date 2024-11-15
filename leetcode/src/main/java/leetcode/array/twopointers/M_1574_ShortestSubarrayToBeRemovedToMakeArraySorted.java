package leetcode.array.twopointers;

public class M_1574_ShortestSubarrayToBeRemovedToMakeArraySorted {
    public static void main(String[] args) {
        System.out.println(findLengthOfShortestSubarray(new int[]{1, 2, 3, 10, 4, 2, 3, 5})); // 3
        System.out.println(findLengthOfShortestSubarray(new int[]{5, 4, 3, 2, 1})); // 4
        System.out.println(findLengthOfShortestSubarray(new int[]{1, 3, 2, 4})); // 1
    }

    public static int findLengthOfShortestSubarray(int[] arr) {
        int leftIdx = 0;
        while (leftIdx < arr.length - 1 && arr[leftIdx] <= arr[leftIdx + 1]) {
            leftIdx++;
        }
        if (leftIdx == arr.length - 1) return 0;

        int rightIdx = arr.length - 1;
        while (rightIdx > 0 && arr[rightIdx - 1] <= arr[rightIdx]) {
            rightIdx--;
        }
        //System.out.println(leftIdx + " " + rightIdx);

        int res = Math.min(rightIdx, arr.length - leftIdx - 1);

        int l = 0, r = rightIdx;
        while (l <= leftIdx && r <= arr.length - 1) {
            if (arr[l] <= arr[r]) {
                res = Math.min(res, r - l - 1);
                l++;
            }
            else r++;
        }
        //System.out.println(l + " " + r);

        return res;
    }
}
