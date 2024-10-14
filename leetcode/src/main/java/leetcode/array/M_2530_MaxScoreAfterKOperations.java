package leetcode.array;

import java.util.PriorityQueue;

public class M_2530_MaxScoreAfterKOperations {
    public static void main(String[] args) {
        int[] nums = {1, 10, 3, 3, 3};
        System.out.println(new M_2530_MaxScoreAfterKOperations().maxKelements(nums, 3));
    }

    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int n : nums) {
            pq.offer(n);
        }

        long score = 0;
        for (int i = 0; i < k; ++i) {
            Integer n = pq.poll();
            if (n != null) {
                score += n;
                pq.offer((int) Math.ceil(n / (double) 3));
            }
        }

        return score;
    }
}
