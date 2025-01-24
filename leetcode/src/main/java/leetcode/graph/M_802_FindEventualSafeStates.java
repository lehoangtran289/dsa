package leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class M_802_FindEventualSafeStates {
    public static void main(String[] args) {
        System.out.println(eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}}));
    }

    /**
     * The problem is reduced to finding the nodes that do not have any paths that lead to a cycle.
     * terminal nodes are nodes which has 0 outDegree
     *
     * @param graph outDegree list
     */
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        List<List<Integer>> inDegree = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            inDegree.add(new ArrayList<>());
        }

        int[] outDegree = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int u : graph[i]) {
                outDegree[i]++;
                inDegree.get(u).add(i);
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (outDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            res.add(u);

            for (int v : inDegree.get(u)) {
                outDegree[v]--;
                if (outDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        Collections.sort(res);
        return res;
    }
}
