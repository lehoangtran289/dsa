package contest.weekly460;

import java.util.*;

public class M_Q3_MinimumJumpsToReachEndViaPrimeTeleportation {
    public static void main(String[] args) {
        System.out.println(minJumps(new int[]{4,6,5,8}));
    }

    /**
     * Problem: Minimum Jumps to Reach End by Prime Teleportation and Adjacent Moves
     * -------------------------------------------------
     * Idea: BFS
     * Optimization:
     * 1. Time complexity
     * - Process time teleportation:
     *    - Intuition: find all indices of multiples of a prime number in nums using 2 for loops -> O(n^2)
     *    - Optimization: use a map to store indices of each value in nums -> just need to check multiplication -> O(n log(max))
     * <br>
     * 2. Space complexity:
     *      2.1. Avoid building adjacent list for each index -> O(n^2) space
     *      -> Instead, bfs on the fly since adj are 2 moves (i - 1, i + 1 and prime teleportation)
     */
    public static int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n - 1;

        // find max value in nums
        int maxVal = Integer.MIN_VALUE;
        for (int num : nums) maxVal = Math.max(maxVal, num);

        // build prime filter
        boolean[] isPrime = sieve(maxVal);

        // stores indices of each value in nums
        Map<Integer, List<Integer>> valToIndices = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            valToIndices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // BFS from 0 to n - 1, teleport on the go
        Set<Integer> seenPrimes = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        queue.offer(0);
        visited[0] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int i = queue.poll();
                if (i == n - 1) return steps;

                // Adjacent moves
                if (i > 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }
                if (i < n - 1 && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }

                // Prime teleportation
                int val = nums[i];
                if (isPrime[val] && !seenPrimes.contains(val)) {
                    seenPrimes.add(val);

                    for (int mul = val; mul <= maxVal; mul += val) {
                        if (!valToIndices.containsKey(mul)) continue;
                        for (int index : valToIndices.get(mul)) {
                            if (index != i && !visited[index]) {
                                visited[index] = true;
                                queue.offer(index);
                            }
                        }
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    private static boolean[] sieve(int n) {
        if (n <= 1) return new boolean[0];
        boolean[] isPrimes = new boolean[n + 1];
        Arrays.fill(isPrimes, true);
        isPrimes[0] = false;
        isPrimes[1] = false;

        for (int i = 2; i <= Math.sqrt(n); ++i) {
            if (isPrimes[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrimes[j] = false;
                }
            }
        }

        return isPrimes;
    }
}
