package leetcode.array.array2d;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class H_773_Sliding_Puzzle {
    public static void main(String[] args) {
        System.out.println(slidingPuzzle(new int[][]{{1, 2, 3}, {4, 0, 5}}));
    }

    public static int slidingPuzzle(int[][] board) {
        int[][] dirs = new int[][]{
                {1, 3},
                {0, 2, 4},
                {1, 5},
                {0, 4},
                {1, 3, 5},
                {2, 4},
        };

        String target = "123450";
        StringBuilder state = new StringBuilder();

        for (int[] ints : board) {
            for (int i = 0; i < board[0].length; ++i) {
                state.append(ints[i]);
            }
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.add(state.toString());
        visited.add(state.toString());

        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                String curState = queue.poll();
                if (target.equals(curState)) return res;

                int zeroPos = curState.indexOf('0');
                for (int pos : dirs[zeroPos]) {
                    String nextState = swap(curState, zeroPos, pos);
                    if (visited.contains(nextState)) continue;

                    visited.add(nextState);
                    queue.add(nextState);
                }

                size--;
            }
            res++;
        }

        return -1;
    }

    // Helper method to swap characters at indices i and j in the string
    private static String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }
}
