package leetcode.array;

public class M_2924_FindChampionII {
    public static void main(String[] args) {
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}};
        System.out.println(findChampion(n, edges));
    }

    public static int findChampion(int n, int[][] edges) {
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            indegree[edge[1]] = 1;
        }

        int res = -1;
        for (int i = 0; i < n; ++i) {
            if (indegree[i] == 0) {
                if (res != -1) return -1;
                res = i;
            }
        }
        return res;
    }
}
