package leetcode.array.twopointers;

public class M_11_ContainerWithMostWater {
    public static void main(String[] args) {
        M_11_ContainerWithMostWater container = new M_11_ContainerWithMostWater();
        System.out.println(container.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        int lo = 0;
        int hi = height.length - 1;

        while (lo < hi) {
            int area = (hi - lo) * (Math.min(height[lo], height[hi]));
            maxArea = Math.max(area, maxArea);
            if (height[lo] < height[hi]) {
                lo++;
            } else {
                hi--;
            }
        }
        return maxArea;
    }
}
