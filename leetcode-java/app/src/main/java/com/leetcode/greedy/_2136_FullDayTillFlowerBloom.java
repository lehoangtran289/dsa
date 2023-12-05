package com.leetcode.greedy;

import java.util.Arrays;

public class _2136_FullDayTillFlowerBloom {
    public static void main(String[] args) {
        System.out.println(earliestFullBloom(new int[]{1, 10, 3, 10, 2}, new int[]{5, 1, 2, 15, 4}));
    }

    static class Seed {
        int p;
        int g;
        public Seed(int p, int g) {
            this.p = p;
            this.g = g;
        }
    }

    public static int earliestFullBloom(int[] plantTime, int[] growTime) {
        Seed[] seeds = new Seed[plantTime.length];
        for (int i = 0; i < plantTime.length; i++)
            seeds[i] = new Seed(plantTime[i], growTime[i]);
        Arrays.sort(seeds, (a, b) -> a.g - b.g);
        int time = 0;
        int res = 0;
        for (Seed s : seeds) {
            time += s.p;
            res = Math.max(res, time + s.g);
        }
        return res;
    }
}
