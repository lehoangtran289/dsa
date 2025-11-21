package leetcode.graph.mst;

public class M_547_NumberOfProvinces {

    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }

    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DisjointSet ds = new DisjointSet(n);

        for (int u = 0; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                if (isConnected[u][v] == 1) {
                    ds.union(u, v);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (ds.find(i) == i) {
                count++;
            }
        }
        return count;
    }
}
