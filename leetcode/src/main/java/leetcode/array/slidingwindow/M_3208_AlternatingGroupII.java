package leetcode.array.slidingwindow;

public class M_3208_AlternatingGroupII {
    public static void main(String[] args) {
        System.out.println(numberOfAlternatingGroups(new int[]{0, 1, 0, 0, 1, 0, 1}, 6)); // 2
        System.out.println(numberOfAlternatingGroups(new int[]{0, 1, 0, 1, 0}, 3)); // 3
    }

    public static int numberOfAlternatingGroups(int[] colors, int k) {
        int res = 0;
        int n = colors.length;
        int l = 0;

        // circle array technique
        for (int r = 1; r < n + k - 1; ++r) {
            if (colors[r % n] == colors[(r - 1) % n]) {
                l = r;
                continue;
            }

            if (r - l + 1 >= k) {
                l++;
                res++;
            }
        }

        return res;
    }
}
