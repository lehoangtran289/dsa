package leetcode.array;

public class _367_ValidPerfectSquare {
    public static void main(String[] args) {
        System.out.println(isPerfectSquare(9));
    }

    public static boolean isPerfectSquare(int num) {
        if (num == 1)
            return true;

        long low = 1, high = num;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
