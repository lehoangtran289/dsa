package leetcode.array.twopointers;

// Neetcode two pointers 4
public class M_11_ContainerWithMostWater {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public static int maxArea(int[] height) {
        int res = 0;

        int l = 0, r = height.length - 1;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            res = Math.max(res, area);

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return res;
    }
}
