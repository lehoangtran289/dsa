package leetcode.array;

public class M_1574_ShortestSubarrayToBeRemovedToMakeArraySorted {
    public static void main(String[] args) {
        System.out.println(findLengthOfShortestSubarray(new int[] {1,2,3,10,4,2,3,5}));
        System.out.println(findLengthOfShortestSubarray(new int[] {5,4,3,2,1}));
    }

    public static int findLengthOfShortestSubarray(int[] arr) {
        int l = 0, h = arr.length - 1;
        int res = Integer.MAX_VALUE;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (check(arr, mid)) {
                h = mid - 1;
                res = Math.min(res, mid);
            } else {
                l = mid + 1;
            }
        }

        return res;
    }

    public static boolean check(int[] arr, int removeLen) {
        boolean isSkip = false;
        for (int i = removeLen; i < arr.length; ++i) {
            if (i > 0 && arr[i] < arr[i - 1]) {
                isSkip = true;
                break;
            }
        }
        if (!isSkip) return true;

        int i = 0;
        int prev = arr[0];
        while (i < arr.length) {
            if (i != 0 && arr[i] < arr[i - 1]) {
                prev = arr[i - 1];
                i += removeLen;
                break;
            }
            i++;
        }

        while (i < arr.length) {
            if (arr[i] < prev) return false;
            else {
                prev = arr[i];
                i++;
            }
        }

        return false;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            if (i != 0 && arr[i] < arr[i - 1]) return false;
        }
        return true;
    }
}
