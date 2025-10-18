package leetcode.array.binarysearch;

import java.util.*;

public class H_774_MinimizeMaxDistanceToGasStation {
    public static void main(String[] args) {
        System.out.println(minmaxGasDist(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9)); // 0.500000
    }

    /**
     * Binary search (double range)
     * --------------------
     * TC: O(n logC), C is the range of distance
     * SC: O(1)
     */
    public static double minmaxGasDist(int[] stations, int k) {
        double res = 0;
        double l = 0, h = Math.pow(10, 8);

        while (h - l > Math.pow(10, -6)) {
            double mid = l + (h - l) / 2;

            if (isValid(stations, k, mid)) {
                res = mid;
                h = mid;
            } else {
                l = mid;
            }
        }

        return res;
    }

    private static boolean isValid(int[] stations, int k, double target) {
        int addedStations = 0;

        for (int i = 0; i < stations.length - 1; ++i) {
            addedStations += (int) ((stations[i + 1] - stations[i]) / target);
        }

        return addedStations <= k;
    }

    /**
     * Greedy + heap -> TLE
     * Idea: track max difference between station and add new station in between
     * --------------------
     * TC: O(n + k logn)
     * SC: O(n)
     */
    public static double minmaxGasDist1(int[] stations, int k) {
        int n = stations.length;

        // max heap: <difference, new stations added>
        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) ->
                Double.compare((double) b[0] / b[1], (double) a[0] / a[1]));

        for (int i = 0; i < n - 1; ++i) {
            maxHeap.add(new int[]{stations[i + 1] - stations[i], 1});
        }

        for (int i = 0; i < k; ++i) {
            int[] cur = maxHeap.poll();
            cur[1]++;
            maxHeap.add(cur);
        }

        int[] peek = maxHeap.poll();
        return (double) peek[0] / peek[1];
    }
}
