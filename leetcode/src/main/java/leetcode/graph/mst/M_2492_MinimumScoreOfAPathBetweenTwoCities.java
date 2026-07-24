package leetcode.graph.mst;

public class M_2492_MinimumScoreOfAPathBetweenTwoCities {

    /**
     * TC: O(n + m) ~ where n = number of cities, m = number of roads
     * SC: O(n)
     */
    public static int minScore(int n, int[][] roads) {
        DisjointSet dsu = new DisjointSet(n + 1); // O(n)

        for (int[] road : roads) { // O(m)
            dsu.join(road[0], road[1]);
        }

        int res = 1 << 30;
        int target = dsu.find(1);
        for (int[] road : roads) { // O(m)
            if (dsu.find(road[0]) == target) {
                res = Math.min(res, road[2]);
            }
        }

        return res;
    }
}
