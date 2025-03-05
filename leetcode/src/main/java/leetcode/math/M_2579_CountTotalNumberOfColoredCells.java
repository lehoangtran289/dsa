package leetcode.math;

public class M_2579_CountTotalNumberOfColoredCells {
    public static void main(String[] args) {
        System.out.println(coloredCells2(1)); // 1
        System.out.println(coloredCells2(2)); // 5
        System.out.println(coloredCells2(3)); // 13
        System.out.println(coloredCells2(4)); // 25
    }

    public static long coloredCells(int n) {
        if (n == 1) return 1;
        return coloredCells(n - 1) + (n - 2) * 4L + 4L;
    }

    public static long coloredCells2(int n) {
        long res = 1;

        while (n > 0) {
            res += 4 + (n - 2) * 4L;
            n--;
        }

        return res;
    }
}
