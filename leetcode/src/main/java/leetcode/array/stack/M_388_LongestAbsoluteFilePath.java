package leetcode.array.stack;

import java.util.Stack;

public class M_388_LongestAbsoluteFilePath {
    public static void main(String[] args) {
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext")); // 20
        System.out.println(lengthLongestPath("a")); // 0
        System.out.println(lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt")); // 12
    }


    /**
     * Stack, with built-in string functions
     * ----------------------------------
     * Idea:
     * - Use an array to store the current path length at each depth level
     * - Split the input string by newline to process each file/directory
     * - For each file/directory, determine its depth by counting leading tabs
     * <p>
     * - If it's a file (contains a dot), update the maximum length
     * - If it's a directory, update the path length for the next depth level
     * ----------------------------------
     * TC: O(n) where n is the length of the input string
     * SC: O(d) where d is the maximum depth of the directory structure
     */
    public static int lengthLongestPath(String input) {
        String[] lines = input.split("\n");
        int[] pathLen = new int[lines.length + 1];
        int res = 0;

        for (String line : lines) {
            int depth = 0;
            while (depth < line.length() && line.charAt(depth) == '\t') {
                depth++;
            }

            String name = line.substring(depth);
            int pathLength = depth == 0 ? name.length() : pathLen[depth - 1] + 1 + name.length();

            if (name.contains(".")) {
                res = Math.max(res, pathLength);
            } else {
                pathLen[depth] = pathLength;
            }

        }

        return res;
    }

    /**
     * Stack, manual parsing
     * ----------------------------------
     * TC: O(n) where n is the length of the input string
     * SC: O(d) where d is the maximum depth of the directory structure
     */
    public static int lengthLongestPath2(String input) {
        int n = input.length();
        int res = 0;
        Stack<Integer> dirStack = new Stack<>();

        int i = 0;
        while (i < n) {
            // Determine depth
            int depth = 0;
            while (i < n && input.charAt(i) == '\t') {
                depth++;
                i++;
            }

            // get name length
            int nameLength = 0;
            boolean isFile = false;
            while (i < n && input.charAt(i) != '\n') {
                if (input.charAt(i) == '.') {
                    isFile = true;
                }
                nameLength++;
                i++;
            }

            // skip newline
            i++;

            // check depth and pop stack if needed
            while (!dirStack.isEmpty() && dirStack.size() > depth) {
                dirStack.pop();
            }

            // get path length
            int pathLength = nameLength;
            if (!dirStack.isEmpty()) {
                pathLength += dirStack.peek() + 1; // add '/' length
            }

            // update result or push to stack
            if (isFile) {
                res = Math.max(res, pathLength);
            } else {
                dirStack.push(pathLength);
            }
        }

        return res;
    }
}
