package leetcode.array;

import java.util.*;

public class M_1057_CampusBikes {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(assignBikes(
                new int[][]{{0, 0}, {2, 1}},
                new int[][]{{1, 2}, {3, 3}}
        ))); // [1,0]
    }

    static class WorkerBike {
        int workerId;
        int bikeId;
        int distance;

        WorkerBike(int workerId, int bikeId, int distance) {
            this.workerId = workerId;
            this.bikeId = bikeId;
            this.distance = distance;
        }
    }

    private static int distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    // -----------------------
    /**
     * Sorting all pairs
     * Idea: Calculate all worker-bike pairs, sort by distance, then assign bikes
     * -----------------------
     * TC: O(N * M log(N * M)) - N workers, M bikes
     * SC: O(N * M)
     */
    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        List<WorkerBike> workerBikePairs = new ArrayList<>();

        for (int i = 0; i < workers.length; ++i) {
            for (int j = 0; j < bikes.length; ++j) {
                workerBikePairs.add(new WorkerBike(i, j, distance(workers[i], bikes[j])));
            }
        }

        workerBikePairs.sort(Comparator.comparingInt((WorkerBike a) -> a.distance)
                .thenComparingInt((WorkerBike a) -> a.workerId)
                .thenComparingInt((WorkerBike a) -> a.bikeId));

        boolean[] takenBikes = new boolean[bikes.length];
        boolean[] assignedWorkers = new boolean[workers.length];
        int[] res = new int [workers.length];

        for (WorkerBike wb : workerBikePairs) {
            int workerId = wb.workerId;
            int bikeId = wb.bikeId;

            if (!takenBikes[bikeId] && !assignedWorkers[workerId]) {
                res[workerId] = wb.bikeId;
                takenBikes[bikeId] = true;
                assignedWorkers[workerId] = true;
            }
        }

        return res;
    }

    // -----------------------
    /**
     * Bucket Sort
     * Idea: Since distance is in a small range, group pairs by distance and assign bikes
     * Since pairs in group are added in order, they are already sorted by workerId and bikeId
     * -----------------------
     * TC: O(N * M + K) - N workers, M bikes, K is the range of distances (max 2000)
     * SC: O(N * M)
     */
    public static int[] assignBikes1(int[][] workers, int[][] bikes) {
        Map<Integer, List<WorkerBike>> distanceMap = new HashMap<>();

        for (int i = 0; i < workers.length; ++i) {
            for (int j = 0; j < bikes.length; ++j) {
                int distance = distance(workers[i], bikes[j]);

                if (!distanceMap.containsKey(distance)) {
                    distanceMap.put(distance, new ArrayList<>());
                }
                distanceMap.get(distance).add(new WorkerBike(i, j, distance));
            }
        }

        boolean[] takenBikes = new boolean[bikes.length];
        boolean[] assignedWorkers = new boolean[workers.length];
        int[] res = new int [workers.length];

        for (int curDist = 1; curDist <= 2000; ++curDist) {
            if (!distanceMap.containsKey(curDist)) continue;

            for (WorkerBike wb : distanceMap.get(curDist)) {
                if (!takenBikes[wb.bikeId] && !assignedWorkers[wb.workerId]) {
                    res[wb.workerId] = wb.bikeId;
                    takenBikes[wb.bikeId] = true;
                    assignedWorkers[wb.workerId] = true;
                }
            }
        }

        return res;
    }


    // -----------------------
    /**
     * Priority Queue
     * Idea: For each worker, sort all bikes by distance, then use a min-heap to assign bikes
     * Maintain n elements in heap, each with the closest bike to worker
     * -----------------------
     * TC: O(N * M log M) - N workers, M bikes
     * SC: O(N * M)
     */
    public static int[] assignBikes2(int[][] workers, int[][] bikes) {
        List<List<WorkerBike>> workerBikeList = new ArrayList<>();
        int[] nextClosestBike = new int[workers.length];

        Queue<WorkerBike> pq = new PriorityQueue<>(Comparator.comparingInt((WorkerBike a) -> a.distance)
                .thenComparingInt((WorkerBike a) -> a.workerId)
                .thenComparingInt((WorkerBike a) -> a.bikeId));

        for (int i = 0; i < workers.length; ++i) {
            List<WorkerBike> bikeList = new ArrayList<>();

            for (int j = 0; j < bikes.length; ++j) {
                bikeList.add(new WorkerBike(i, j, distance(workers[i], bikes[j])));
            }

            bikeList.sort((a, b) -> a.distance - b.distance);
            workerBikeList.add(bikeList);

            // add to pq
            pq.add(bikeList.get(0));
            nextClosestBike[i]++;
        }

        boolean[] takenBikes = new boolean[bikes.length];
        boolean[] assignedWorkers = new boolean[workers.length];
        int[] res = new int [workers.length];

        while (!pq.isEmpty()) {
            WorkerBike cur = pq.poll();

            if (!takenBikes[cur.bikeId] && !assignedWorkers[cur.workerId]) {
                res[cur.workerId] = cur.bikeId;
                takenBikes[cur.bikeId] = true;
                assignedWorkers[cur.workerId] = true;
            } else {
                // add to pq
                int nextBikeId = nextClosestBike[cur.workerId];
                pq.add(workerBikeList.get(cur.workerId).get(nextBikeId));
                nextClosestBike[cur.workerId]++;
            }
        }

        return res;
    }
}
