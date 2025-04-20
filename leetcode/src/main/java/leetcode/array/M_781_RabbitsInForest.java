package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class M_781_RabbitsInForest {
    public static void main(String[] args) {
        System.out.println(new M_781_RabbitsInForest().numRabbits2(new int[]{1, 0, 1, 0, 0})); // 5
    }

    public int numRabbits(int[] answers) {
        int[] freq = new int[1000];
        int res = 0;

        for (int num : answers) {
            if (freq[num] == 0) {
                res += num + 1;
                freq[num] += num;
            } else {
                freq[num]--;
            }
        }

        return res;
    }

    public int numRabbits2(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;

        for (int num : answers) {
            if (!map.containsKey(num)) {
                res += num + 1;
                map.put(num, num);
            } else {
                map.put(num, map.get(num) - 1);
            }

            if (map.get(num) <= 0) {
                map.remove(num);
            }
        }

        return res;
    }
}
