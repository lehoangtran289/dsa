package leetcode.greedy;

import java.util.Arrays;

public class M_134_GasStation {
    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int sumCost = Arrays.stream(cost).sum();
        int sumGas = Arrays.stream(gas).sum();
        if (sumCost > sumGas) return -1;

        int res = 0;
        int remain = 0;
        for (int i = 0; i < gas.length; ++i) {
            remain += gas[i] - cost[i];
            if (remain < 0) {
                remain = 0;
                res = i + 1;
            }
        }
        return res;
    }
}
