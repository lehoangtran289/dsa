package leetcode.graph.topo;

public class M_4085_FindRootOfColoredTree {

    /**
     * Idea: Order of color R -> B -> W -> R => direction of edge
     * Use indegree to find root, outdegree to verify constraint.
     * ---
     * TC: O(N + E)
     * SC: O(N)
     */
    public int findColoredRoot(int[][] edges, String colors) {
        int n = colors.length();
        int[] inDegree = new int[n];
        int[] outDegree = new int[n];

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            char colorU = colors.charAt(u);
            char colorV = colors.charAt(v);

            if (colorU == colorV) return -1;

            if (next(colorU) == colorV) {
                outDegree[u]++;
                inDegree[v]++;
            } else if (next(colorV) == colorU) {
                outDegree[v]++;
                inDegree[u]++;
            }
        }

        int res = -1;
        for (int i = 0; i < n; ++i) {
            if (outDegree[i] > 2 || inDegree[i] > 1) return -1; // constraint
            if (inDegree[i] == 0) {
                if (res != -1) return -1; // 2 roots => invalid
                res = i;
            }
        }

        return res;
    }

    private char next(char c) {
        if (c == 'R') return 'B';
        if (c == 'B') return 'W';
        return 'R';
    }
}
