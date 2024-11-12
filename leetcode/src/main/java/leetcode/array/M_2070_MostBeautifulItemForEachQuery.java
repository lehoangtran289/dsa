package leetcode.array;

import java.util.Arrays;

public class M_2070_MostBeautifulItemForEachQuery {
    public static void main(String[] args) {
        int[][] p = new int[][]{{10, 1000}};
        int[] q = new int[]{5};
        System.out.println(Arrays.toString(maximumBeauty(p, q)));
    }

    public static int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        int[][] maxSoFar = new int[items.length][2];
        maxSoFar[0] = items[0];
        for (int i = 1; i < items.length; ++i) {
            maxSoFar[i][0] = items[i][0];
            maxSoFar[i][1] = Math.max(maxSoFar[i - 1][1], items[i][1]);
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            res[i] = binarySearch(maxSoFar, queries[i]);
        }
        return res;
    }

    public static int binarySearch(int[][] maxSoFar, int query) {
        int l = 0, h = maxSoFar.length - 1;
        int max = 0;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (maxSoFar[mid][0] <= query) {
                max = Math.max(max, maxSoFar[mid][1]);
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return max;
    }
}
