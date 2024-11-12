package leetcode.array;

import java.util.Arrays;

public class M_2070_MostBeautifulItemForEachQuery {
    public static void main(String[] args) {
        int[][] p = new int[][]{{193, 732}, {781, 962}, {864, 954}, {749, 627}, {136, 746}, {478, 548}, {640, 908}, {210, 799}, {567, 715}, {914, 388}, {487, 853}, {533, 554}, {247, 919}, {958, 150}, {193, 523}, {176, 656}, {395, 469}, {763, 821}, {542, 946}, {701, 676}};
        int[] q = new int[]{885, 1445, 1580, 1309, 205, 1788, 1214, 1404, 572, 1170, 989, 265, 153, 151, 1479, 1180, 875, 276, 1584};
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
