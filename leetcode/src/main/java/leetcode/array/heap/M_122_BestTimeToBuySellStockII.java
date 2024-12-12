package leetcode.array.heap;

import java.util.PriorityQueue;

public class M_122_BestTimeToBuySellStockII {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
    }

    public static int maxProfit(int[] prices) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        int i = 1;
        while (i < prices.length) {
            int temp = 0;
            while (i < prices.length && prices[i] > prices[i - 1]) {
                temp += prices[i] - prices[i - 1];
                ++i;
            }
            pq.add(temp);
            ++i;
        }

        int count = 0;
        int res = 0;
        while (!pq.isEmpty() && count < 2) {
            res += pq.poll();
            count++;
        }

        return res;
    }
}
