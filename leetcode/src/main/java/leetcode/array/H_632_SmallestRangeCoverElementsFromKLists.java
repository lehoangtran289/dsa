package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class H_632_SmallestRangeCoverElementsFromKLists {
    static void main() {
        List<List<Integer>> nums = Arrays.asList(
                Arrays.asList(4, 10, 15, 24, 26),
                Arrays.asList(0, 9, 12, 20),
                Arrays.asList(5, 18, 22, 30)
        );
        System.out.println(Arrays.toString(smallestRange(nums))); // [20, 24]
    }

    /**
     * Idea: merge all elements in all list, keep list index with each element
     * Use sliding window to find min range containing all k lists.
     * ---
     * TC: O(N * log(N))
     * SC: O(N)
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        final int INF = 1 << 20;
        final int k = nums.size();

        // [[list index, value]]
        List<int[]> mergedList = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            for (int num : nums.get(i)) {
                mergedList.add(new int[]{num, i});
            }
        }

        // sort by values ASC
        mergedList.sort((a, b) -> a[0] == b[0] ?
                Integer.compare(a[1], b[1]) :
                Integer.compare(a[0], b[0])
        );

        int[] result = new int[]{-INF, INF};
        Map<Integer, Integer> listCounts = new HashMap<>();

        // sliding window to find min range containing all list
        int l = 0;
        for (int[] rightPair : mergedList) {
            int rightValue = rightPair[0], rightList = rightPair[1];

            listCounts.put(rightList, listCounts.getOrDefault(rightList, 0) + 1);

            while (listCounts.size() == k) {
                int[] leftPair = mergedList.get(l);
                int leftValue = leftPair[0], leftList = leftPair[1];

                int range = rightValue - leftValue;
                if (range < result[1] - result[0]) {
                    result[0] = leftValue;
                    result[1] = rightValue;
                }

                listCounts.put(leftList, listCounts.get(leftList) - 1);
                if (listCounts.get(leftList) == 0) listCounts.remove(leftList);
                l++;
            }
        }

        return result;
    }
}
