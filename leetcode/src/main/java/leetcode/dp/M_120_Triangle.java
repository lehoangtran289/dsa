package leetcode.dp;

import java.util.*;

public class M_120_Triangle {
    public static void main(String[] args) {
        M_120_Triangle solution = new M_120_Triangle();
        System.out.println(solution.minimumTotal(
                List.of(
                        List.of(2),
                        List.of(3, 4),
                        List.of(6, 5, 7),
                        List.of(4, 1, 8, 3)
                )
        )); // 11
    }

    /**
     * DP bottom up
     * -----------------------
     * TC: O(N^2) - N is the number of rows in triangle
     * SC: O(N^2)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // init dp array = triangle
        List<List<Integer>> dp = new ArrayList<>();
        for (var row : triangle) {
            List<Integer> dpRow = new ArrayList<>(row);
            dp.add(dpRow);
        }

        // dp bottom up
        for (int i = dp.size() - 2; i >= 0; --i) {
            for (int j = 0; j < dp.get(i).size(); ++j) {
                int pathSum = triangle.get(i).get(j) + Math.min(
                        dp.get(i + 1).get(j),
                        dp.get(i + 1).get(j + 1)
                );

                dp.get(i).set(j, pathSum);
            }
        }

        return dp.get(0).get(0);
    }
}
