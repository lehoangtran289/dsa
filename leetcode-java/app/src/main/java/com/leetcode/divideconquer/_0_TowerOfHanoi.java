package com.leetcode.divideconquer;

public class _0_TowerOfHanoi {
    public static void main(String[] args) {
        System.out.println(new _0_TowerOfHanoi().toh(3, 1, 3, 2));
    }

    public long toh(int N, int from, int to, int aux) {
        if (N == 1) {
            System.out.println("move disk 1 from rod " + from + " to rod " + to);
            return 1;
        }
        long count = toh(N - 1, from, aux, to);
        System.out.println("move disk " + N + " from rod " + from + " to rod " + to);
        count++;
        count += toh(N - 1, aux, to, from);
        return count;
    }
}
