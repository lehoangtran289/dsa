package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_498_DiagonalTraverse {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
    }

    /**
     * Group by diagonals using a HashMap, then traverse
     * -----------------
     * TC: O(n*m)
     * SC: O(n*m)
     */
    public static int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int key = i + j;

                if (!map.containsKey(key)) map.put(key, new ArrayList<>());
                map.get(key).add(mat[i][j]);
            }
        }

        int[] res = new int[n * m];
        int idx = 0;

        for (int i = 0; i <= n + m - 2; ++i) {
            List<Integer> diagonal = map.get(i);
            if (i % 2 == 0) Collections.reverse(diagonal);

            for (Integer num : diagonal) {
                res[idx++] = num;
            }
        }

        return res;
    }

    /**
     * Simulate the movement
     * -----------------
     * TC: O(n*m)
     * SC: O(1)
     */
    public static int[] findDiagonalOrder2(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[] res = new int[n * m];
        int x = 0, y = 0, dir = 1;

        for (int i = 0; i < n * m; ++i) {
            res[i] = mat[x][y];

            if (dir == 1) { // moving up right
                if (y >= m - 1) { // hit the right wall
                    dir = -1;
                    x++;
                } else if (x == 0) { // hit the top wall
                    dir = -1;
                    y++;
                }else {
                    x--;
                    y++;
                }
            } else { // moving down left
                if (x >= n - 1) { // hit the bottom wall
                    dir = 1;
                    y++;
                } else if (y == 0) { // hit the left wall
                    dir = 1;
                    x++;
                } else {
                    x++;
                    y--;
                }
            }
        }

        return res;
    }
}
