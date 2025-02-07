package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class M_3160_FindTheNumberOfDistinctColorsAmongTheBalls {
    public static void main(String[] args) {

    }

    public static int[] queryResults(int limit, int[][] queries) {
        Map<Integer, Integer> ballMap = new HashMap<>();
        Map<Integer, Integer> colorMap = new HashMap<>();

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int ballIdx = queries[i][0];
            int newColor = queries[i][1];

            // set new color and decrease count of old color
            int prevColor = ballMap.getOrDefault(ballIdx, 0);
            ballMap.put(ballIdx, newColor);

            if (colorMap.containsKey(prevColor)) {
                colorMap.put(prevColor, colorMap.get(prevColor) - 1);
                if (colorMap.get(prevColor) == 0) colorMap.remove(prevColor);
            }
            colorMap.put(newColor, colorMap.getOrDefault(newColor, 0) + 1);

            // set result
            res[i] = colorMap.size();
        }

        return res;
    }
}
