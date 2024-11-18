package leetcode.array;

import java.util.Arrays;

public class E_1652_DefuseTheBomb {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(decrypt(new int[]{2,4,9,3}, -2)));
    }

    public static int[] decrypt(int[] code, int k) {
        int[] res = new int[code.length];
        if (k == 0) return res;

        for (int i = 0; i < res.length; ++i) {
            if (k > 0) {
                for (int j = i + 1; j < i + 1 + k; ++j) {
                    res[i] += code[j % res.length];
                }
            } else {
                for (int j = i - Math.abs(k); j < i; ++j) {
                    res[i] += code[(res.length + j) % res.length];
                }
            }
        }
        return res;
    }
}
