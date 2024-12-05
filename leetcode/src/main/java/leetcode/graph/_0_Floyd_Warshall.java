package leetcode.graph;

public class _0_Floyd_Warshall {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] graph = {{0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}};

        int[][] shortestPaths = floydWarshall(graph);

        // Print the shortest paths
        System.out.println("Shortest paths between all pairs of vertices:");
        printShortestPaths(shortestPaths);
    }

    private static int[][] floydWarshall(int[][] dist) {
        int n = dist.length;

        // Apply the Floyd-Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF
                        && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    private static void printShortestPaths(int[][] shortestPaths) {
        int vertices = shortestPaths.length;
        for (int[] shortestPath : shortestPaths) {
            for (int j = 0; j < vertices; j++) {
                if (shortestPath[j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(shortestPath[j] + "\t");
                }
            }
            System.out.println();
        }
    }
}
