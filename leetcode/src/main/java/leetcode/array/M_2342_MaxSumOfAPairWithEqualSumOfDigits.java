package leetcode.array;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class M_2342_MaxSumOfAPairWithEqualSumOfDigits {
    public int maximumSum(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>(); // <digitSum, values>

        for (int n : nums) {
            int digitSum = digitSum(n);

            if (!map.containsKey(digitSum)) {
                PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
                queue.add(n);
                map.put(digitSum, queue);
            } else {
                map.get(digitSum).add(n);
            }
        }

        int res = -1;
        for (PriorityQueue<Integer> queue : map.values()) {
            if (queue.size() == 1) continue;

            int sum = queue.poll() + queue.poll(); // first max + second max
            res = Math.max(res, sum);
        }

        return res;
    }

    private static int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
