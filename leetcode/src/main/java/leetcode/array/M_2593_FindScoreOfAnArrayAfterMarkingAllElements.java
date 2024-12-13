package leetcode.array;

import java.util.PriorityQueue;

public class M_2593_FindScoreOfAnArrayAfterMarkingAllElements {
    public static void main(String[] args) {
        System.out.println(findScore(new int[]{2, 5, 6, 6, 10})); // 18
        System.out.println(findScore(new int[]{2, 1, 3, 4, 5, 2})); // 7
        System.out.println(findScore(new int[]{2, 3, 5, 1, 3, 2})); // 5
    }

    public static long findScore(int[] nums) {
        int n = nums.length;
        int[] marked = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < n; ++i) pq.add(new int[]{nums[i], i});

        int res = 0;
        while (!pq.isEmpty()) {
            int[] num = pq.poll();
            int score = num[0];
            int index = num[1];

            if (marked[index] == 0) {
                res += score;
                marked[index] = 1;
                if (index >= 1) marked[index - 1] = 1;
                if (index < n - 1) marked[index + 1] = 1;
            }
        }

        return res;
    }
}
