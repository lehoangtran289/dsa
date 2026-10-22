package leetcode.array.heap;

import java.util.PriorityQueue;

public class M_1801_NumberOfOrdersInTheBacklog {

    private static final int MOD = 1_000_000_007;

    /**
     * Idea: maintain 2 heap for order book, buyOrders = max heap, sellOrders = minHeap
     * ---
     * TC: O(NlogN)
     * SC: O(N)
     */
    public int getNumberOfBacklogOrders(int[][] orders) {
        // [price, amount]
        PriorityQueue<int[]> buyOrders = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        PriorityQueue<int[]> sellOrders = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for (int[] order : orders) {
            int price = order[0], amount = order[1], type = order[2];

            // buy order
            if (type == 0) {
                while (!sellOrders.isEmpty() && sellOrders.peek()[0] <= price && amount > 0) {
                    int[] sellOrder = sellOrders.poll();

                    int matched = Math.min(amount, sellOrder[1]);
                    sellOrder[1] -= matched;
                    amount -= matched;
                    if (sellOrder[1] > 0) sellOrders.add(sellOrder);
                }
                if (amount != 0) buyOrders.add(new int[]{price, amount});
                continue;
            }

            // sell order
            while (!buyOrders.isEmpty() && buyOrders.peek()[0] >= price && amount > 0) {
                int[] buyOrder = buyOrders.poll();

                int matched = Math.min(amount, buyOrder[1]);
                buyOrder[1] -= matched;
                amount -= matched;
                if (buyOrder[1] > 0) buyOrders.add(buyOrder);
            }
            if (amount != 0) sellOrders.add(new int[]{price, amount});
        }

        long res = 0;
        while (!buyOrders.isEmpty()) res = (res + buyOrders.poll()[1]) % MOD;
        while (!sellOrders.isEmpty()) res = (res + sellOrders.poll()[1]) % MOD;
        return (int) res;
    }
}
