package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class H_632_SmallestRangeCoverElementsFromKLists {
    public static void main(String[] args) {
        List<List<Integer>> nums = Arrays.asList(
                Arrays.asList(4, 10, 15, 24, 26),
                Arrays.asList(0, 9, 12, 20),
                Arrays.asList(5, 18, 22, 30)
        );
        System.out.println(Arrays.toString(new H_632_SmallestRangeCoverElementsFromKLists().smallestRange(nums)));
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        // build list of elements and their list index
        // ex: [[1,0], [2,1], ...] -> [element, kth-list]
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < nums.size(); ++i) {
            for (int n : nums.get(i))
                merged.add(new int[]{n, i});
        }
        merged.sort((a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            else return Integer.compare(a[0], b[0]);
        });

//        // debug
//        for (int[] event : merged) {
//            System.out.print(Arrays.toString(event) + " ");
//        }
//        System.out.println();

        // Two pointers to track the smallest range
        Map<Integer, Integer> freq = new HashMap<>();
        int start = 0, end = Integer.MAX_VALUE;
        int left = 0;

        for (int[] curRight : merged) {
            freq.put(curRight[1], freq.getOrDefault(curRight[1], 0) + 1); // increase freq at list index

            // while condition is satisfied, shrink the range window
            while (freq.size() == nums.size()) {
                int[] curLeft = merged.get(left);

                // update min range
                if (curRight[0] - curLeft[0] < end - start) {
                    start = curLeft[0];
                    end = curRight[0];
                }

                // shrink window ~ increase left pointer and update freq
                if (freq.get(curLeft[1]) == 1) {
                    freq.remove(curLeft[1]);
                } else {
                    freq.put(curLeft[1], freq.get(curLeft[1]) - 1);
                }
                left++;
            }
        }

        return new int[]{start, end};
    }
}
