package leetcode.graph.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class M_1514_PathWithMaximumProbability {
    static void main() {
        System.out.println(maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2}, 0, 2));
    }

    /**
     * TC: O((V + E) log(V)),
     *      ~ heap ops = logV, nodes are added to heap once -> VlogV, edges are examined once -> ElogV
     * SC: O(V + E) for graph
     */
    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // init graph
        List<int[]>[] graph = new List[n];

        for (int i = 0; i < n; ++i) graph[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; ++i) {
            graph[edges[i][0]].add(new int[]{edges[i][1], i});
            graph[edges[i][1]].add(new int[]{edges[i][0], i});
        }

        // init dijkstra states
        double[] best = new double[n];
        PriorityQueue<State> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));

        maxHeap.add(new State(start_node, 1));

        // process dijkstra
        while (!maxHeap.isEmpty()) {
            State curState = maxHeap.poll();

            if (curState.node == end_node) return curState.prob;

            for (int[] neighbor : graph[curState.node]) {
                int nextNode = neighbor[0];
                double nextProb = succProb[neighbor[1]] * curState.prob;

                if (nextProb > best[nextNode]) {
                    best[nextNode] = nextProb;
                    maxHeap.add(new State(nextNode, nextProb));
                }
            }
        }

        return best[end_node];
    }

    static class State {
        int node;
        double prob;

        State(int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }
}
