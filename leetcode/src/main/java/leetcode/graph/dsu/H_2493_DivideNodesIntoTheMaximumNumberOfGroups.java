package leetcode.graph.dsu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class H_2493_DivideNodesIntoTheMaximumNumberOfGroups {
    public static void main(String[] args) {
        System.out.println(magnificentSets(6, new int[][]{{1, 2}, {1, 4}, {1, 5}, {2, 6}, {2, 3}, {4, 6}}));
    }

    /**
     * BFS + DSU solution
     * DSU -> group components
     * BFS -> find number of groups inside a component
     */
    public static int magnificentSets(int n, int[][] edges) {
        List<Integer>[] adjLst = new List[n + 1];
        DisjointSets dsu = new DisjointSets(n);

        for (int i = 0; i < n; ++i) {
            adjLst[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int x = e[0] - 1;
            int y = e[1] - 1;
            adjLst[x].add(y);
            adjLst[y].add(x);
            dsu.union(x, y);
        }

        Map<Integer, Integer> componentToGroupNumMap = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int numGroups = getMaxNumGroup(adjLst, i, n);
            if (numGroups == -1) return -1;

            int root = dsu.find(i);
            componentToGroupNumMap.put(
                    root,
                    Math.max(
                            numGroups,
                            componentToGroupNumMap.getOrDefault(root, 0)
                    )
            );
        }

        // Calculate the total number of groups across all components
        int res = 0;
        for (int numberOfGroups : componentToGroupNumMap.values()) {
            res += numberOfGroups;
        }
        return res;
    }

    // Function to calculate the number of groups for a given component starting from srcNode
    private static int getMaxNumGroup(List<Integer>[] adjLst, int src, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] levelArr = new int[n];
        Arrays.fill(levelArr, -1);

        queue.add(src);
        levelArr[src] = 0;

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int adj : adjLst[cur]) {
                    if (levelArr[adj] == -1) {
                        levelArr[adj] = level + 1;
                        queue.add(adj);
                    } else {
                        // If the neighbor is already in the same layer, return -1 (invalid partition)
                        if (levelArr[adj] == level) {
                            return -1;
                        }
                    }
                }
            }
            level++;
        }
        return level;
    }

    static class DisjointSets {
        int[] parent;

        DisjointSets(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] == x) return parent[x];
            parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;
            parent[rootY] = rootX;
        }
    }
}
