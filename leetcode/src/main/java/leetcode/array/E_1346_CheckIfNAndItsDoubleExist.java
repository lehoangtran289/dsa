package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class E_1346_CheckIfNAndItsDoubleExist {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new int[]{7, 11, 14, 9}));
    }

    public static boolean checkIfExist(int[] arr) {
        Set<Integer> map = new HashSet<>();

        for (int n : arr) {
            if (map.contains(n * 2)) return true;
            if (n % 2 == 0 && map.contains(n / 2)) return true;

            map.add(n);
        }

        return false;
    }
}
