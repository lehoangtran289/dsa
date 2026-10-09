package leetcode.graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class M_2093P_MinimumCostToReachCityWithDiscounts {

    /**
     * N = #cities, E = #edges, K = discounts
     * TC: O(N + (N * K) log(N * K) + E log(N * K)) = O((N * K + E) log(N * K))
     *      -> O(N) = building graph, (N * K) log(N * K) = N nodes * K times * log(heap size), E log(N * K) = relax cost
     * SC: O(N * K + E)
     *      -> N * K = dp[][], E = graph
     */
    public int minimumCost(int n, int[][] highways, int discounts) {
        // build graph
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; ++i) graph[i] = new ArrayList<>();

        for (int[] highway : highways) {
            int city1 = highway[0], city2 = highway[1], toll = highway[2];
            graph[city1].add(new int[]{city2, toll});
            graph[city2].add(new int[]{city1, toll});
        }

        // dp[i][j] = min cost going to city i-th, with j discounts left
        int[][] dp = new int[n][discounts + 1];

        // [current city, total cost so far, discounts left]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        // init dijkstra states
        for (int[] row : dp) Arrays.fill(row, 1 << 30);
        dp[0][discounts] = 0;
        minHeap.add(new int[]{0, 0, discounts});

        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int curCity = cur[0], curCost = cur[1], curDiscounts = cur[2];

            // pruning
            if (curCost > dp[curCity][curDiscounts]) continue;
            if (curCity == n - 1) break; // as soon as it reaches n - 1 => guarantee min-cost path

            for (int[] neighbor : graph[curCity]) {
                int nextCity = neighbor[0];
                int nextCost = curCost + neighbor[1];
                int nextCostWithDiscount = curCost + neighbor[1] / 2;

                if (nextCost < dp[nextCity][curDiscounts]) {
                    dp[nextCity][curDiscounts] = nextCost;
                    minHeap.add(new int[]{nextCity, nextCost, curDiscounts});
                }

                if (curDiscounts > 0 && nextCostWithDiscount < dp[nextCity][curDiscounts - 1]) {
                    dp[nextCity][curDiscounts - 1] = nextCostWithDiscount;
                    minHeap.add(new int[]{nextCity, nextCostWithDiscount, curDiscounts - 1});
                }
            }
        }

        // get result
        int res = 1 << 30;
        for (int cost : dp[n - 1]) res = Math.min(res, cost);
        return res == 1 << 30 ? -1 : res;
    }
}
