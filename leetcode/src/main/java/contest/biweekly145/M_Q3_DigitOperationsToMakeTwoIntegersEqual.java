package contest.biweekly145;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class M_Q3_DigitOperationsToMakeTwoIntegersEqual {
    public static void main(String[] args) {
        System.out.println(minOperations(15, 88));
        System.out.println(minOperations(10, 12));
    }

    public static int minOperations(int n, int m) {
        if (m == n) return n;

        boolean[] isPrime = buildPrimeFilter(10000);
        if (isPrime[m] || isPrime[n]) return -1;

        int[] cost = new int[10000];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[n] = n;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // pq by cost
        pq.offer(new int[]{n, n});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNum = cur[0];
            int curCost = cur[1];

            if (curNum == m) return curCost;

            List<Integer> nextNums = getNextNums(curNum, isPrime);
            for (int nextNum : nextNums) {
                int newCost = curCost + nextNum;

                if (newCost < cost[nextNum]) {
                    cost[nextNum] = newCost;
                    pq.offer(new int[]{nextNum, newCost});
                }
            }
        }

        return -1;
    }

    private static List<Integer> getNextNums(int curNum, boolean[] isPrime) {
        List<Integer> res = new ArrayList<>();

        char[] digits = String.valueOf(curNum).toCharArray();
        for (int i = 0; i < digits.length; ++i) {
            if (digits[i] < '9') {
                char[] cloneArr = digits.clone();
                cloneArr[i]++;
                int newNum = Integer.parseInt(new String(cloneArr));
                if (!isPrime[newNum]) {
                    res.add(newNum);
                }
            }

            if (digits[i] > '0') {
                char[] cloneArr = digits.clone();
                cloneArr[i]--;
                int newNum = Integer.parseInt(new String(cloneArr));
                if (!isPrime[newNum]) {
                    res.add(newNum);
                }
            }
        }

        return res;
    }

    private static boolean[] buildPrimeFilter(int n) {
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

    private static int getNumDigits(int num) {
        if (num == 0) return 1;
        return (int) Math.floor(Math.log10(num) + 1);
    }
}
