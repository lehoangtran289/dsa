package leetcode.array;

public class M_1760_MinimumLimitOfBallsInABag {
    public static void main(String[] args) {

    }

    public static int minimumSize(int[] nums, int maxOperations) {
        int l = 1, r = 0;
        for (int n : nums) r = Math.max(r, n);

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (isValid(nums, maxOperations, mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private static boolean isValid(int[] nums, int maxOperations, int size) {
        int totalOps = 0;
        for (int num : nums) {
            totalOps += (int) (Math.ceil((double) num / size) - 1);
            if (totalOps > maxOperations) return false;
        }
        return true;
    }
}
