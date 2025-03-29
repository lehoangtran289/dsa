package leetcode.array.stack;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class H_2818_ApplyOperationsToMaximizeScore {
    final int MOD = (int) Math.pow(10, 9) + 7;

    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();

        // init primeScore array
        int[] primeScore = new int[n];
        for (int i = 0; i < n; ++i) {
            primeScore[i] = primeScore(nums.get(i));
        }

        // init dominant index arrays with mono stack
        int[] nextDominant = new int[n];
        int[] prevDominant = new int[n];
        Arrays.fill(nextDominant, n);
        Arrays.fill(prevDominant, -1);

        Stack<Integer> dominantStack = new Stack<>();

        // calculate nextDominant using mono stack
        for (int i = 0; i < n; ++i) {
            while (
                    !dominantStack.isEmpty()
                    && primeScore[dominantStack.peek()] < primeScore[i]
            ) {
                int topId = dominantStack.pop();
                nextDominant[topId] = i;
            }

            // if stack is not empty -> prev dominant at i-th
            if (!dominantStack.isEmpty())
                prevDominant[i] = dominantStack.peek();

            dominantStack.push(i);
        }

        // Calculate the number of subarrays where i-th element is dominant
        // LEFT = i - prevDominant[i] choices
        // RIGHT = nextDominant[i] - i choices
        // => total choices at i-th = LEFT * RIGHT
        long[] subArrCount = new long[n];
        for (int i = 0; i < n; ++i) {
            subArrCount[i] = (long) (nextDominant[i] - i) * (i - prevDominant[i]);
        }

        // Priority queue to process elements in decreasing order of their value
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        for (int i = 0; i < n; ++i) {
            pq.add(new int[]{nums.get(i), i});
        }

        long res = 1;
        while (k > 0) {
            int[] cur = pq.poll();
            int num = cur[0];
            int index = cur[1];

            // Calculate the number of operations to apply on the current element
            // max ops = k
            long ops = Math.min(k, subArrCount[index]);

            // Update the score by raising the element to the power of operations
            res = res * power(num, ops) % MOD;

            k -= ops;
        }

        return (int) res;
    }

    private static int primeScore(int n) {
        int count = 0;

        for (int i = 2; i <= Math.sqrt(n); ++i) {
            if (n % i == 0) count++;
            while (n % i == 0) {
                n /= i;
            }
        }

        // if n is prime -> +1
        if (n >= 2) count++;

        return count;
    }

    // Fast exponentiation % MOD
    private long power(long base, long exponent) {
        long res = 1;

        // Calculate the exponentiation using binary exponentiation
        while (exponent > 0) {
            // If the exponent is odd, multiply the result by the base
            if (exponent % 2 == 1) {
                res = (res * base) % MOD;
            }

            // Square the base and halve the exponent
            base = (base * base) % MOD;
            exponent /= 2;
        }

        return res;
    }
}
