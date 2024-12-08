package contest.biweekly145;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class M_Q3_DigitOperationsToMakeTwoIntegersEqual {
    public static void main(String[] args) {
        System.out.println(minOperations(10, 12));
    }

    private static int transformDigits(String n, String m, int index, int cost) {
        // Base case: If we've processed all digits, return the accumulated cost
        if (index == n.length()) {
            return cost;
        }

        // Get the current digit of n and m
        int digitN = n.charAt(index) - '0';
        int digitM = m.charAt(index) - '0';

        // Try to match the current digit of n to the corresponding digit of m
        int currentNum = Integer.parseInt(n);
        int newCost = cost + currentNum;

        // If the current number is prime, we need to skip this digit transformation and move to the next
        if (isPrime(currentNum)) {
            return transformDigits(n, m, index + 1, newCost);
        }

        // Try increasing the digit of n
        if (digitN < digitM) {
            StringBuilder newN = new StringBuilder(n);
            newN.setCharAt(index, (char) (digitN + 1 + '0'));
            int newNum = Integer.parseInt(newN.toString());
            // Recursively transform the next digit, and add the cost for this operation
            if (!isPrime(newNum)) {
                return transformDigits(newN.toString(), m, index + 1, newCost + newNum);
            }
        }

        // Try decreasing the digit of n
        if (digitN > digitM) {
            StringBuilder newN = new StringBuilder(n);
            newN.setCharAt(index, (char) (digitN - 1 + '0'));
            int newNum = Integer.parseInt(newN.toString());
            // Recursively transform the next digit, and add the cost for this operation
            if (!isPrime(newNum)) {
                return transformDigits(newN.toString(), m, index + 1, newCost + newNum);
            }
        }

        // Return -1 if we cannot transform the number to match m
        return -1;
    }

    // Main function to call the recursive function and check the transformation cost
    public static int minCost(int n, int m) {
        // Convert n and m to strings
        String nStr = String.valueOf(n);
        String mStr = String.valueOf(m);

        // If n is prime initially, return -1 immediately
        if (isPrime(n)) return -1;

        // Call the recursive function to transform the digits and calculate the cost
        return transformDigits(nStr, mStr, 0, 0);
    }

    public static int minOperations(int n, int m) {
        return minCost(n, m);
    }

    private static List<Integer>[] createGraph(int n) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        return graph;
    }

    private static void addEdgeDi(List<Integer>[] graph, int from, int to) {
        graph[from].add(to);
    }

    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static List<Integer> numberToDigits(int num) {
        List<Integer> digits = new ArrayList<>();
        while (num > 0) {
            digits.add(num % 10);
            num /= 10;
        }
        Collections.reverse(digits);
        return digits;
    }

    public static int digitsToNumber(List<Integer> digits) {
        int num = 0;
        for (int digit : digits) {
            num = num * 10 + digit;
        }
        return num;
    }
}
