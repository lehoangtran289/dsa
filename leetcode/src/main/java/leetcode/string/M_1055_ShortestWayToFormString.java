package leetcode.string;

public class M_1055_ShortestWayToFormString {
    public static void main(String[] args) {
        System.out.println(shortestWay("xyz", "xzyxz"));
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * 2. Two pointers approach. Optimization of (1) Concat until subsequence approach
     * SC: O(S * T)
     * O(S) to create a boolean array to mark all characters of source.
     * O(T) to check if all characters of target are present in source.
     * <p/>
     * Then, we have a loop that runs O(T) times. The loop runs only when the task is possible.
     * In each iteration, we have a while loop which runs at most O(S) times
     * Thus, the total time complexity is O(S * T)
     * <p/>
     * TC: O(1)
     * -----------------------------------------------------------------------------------------------------------------
     */
    public static int shortestWay2(String source, String target) {
        boolean[] srcChar = new boolean[26];
        for (char c : source.toCharArray()) {
            srcChar[c - 'a'] = true;
        }

        for (char c : target.toCharArray()) {
            if (!srcChar[c - 'a']) return -1;
        }

        // check how many times does 'source' have to loop
        // so that 'target' is subsequence of 'source'
        int res = 1;
        int p1 = 0; // source pointer
        int p2 = 0; // target pointer

        while (p2 < target.length()) {
            if (p1 >= source.length()) {
                res++;
                p1 = 0;
            }

            if (source.charAt(p1) == target.charAt(p2)) {
                p2++;
            }
            p1++;
        }

        return res;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * 1. Concatenate until Subsequence
     * TC: O(T^2 * S)
     * O(S) to create a boolean array to mark all characters of source.
     * O(T) to check if all characters of target are present in source.
     * Then we have a loop.
     * in the condition, we are calling isSubsequence(toCheck, inString) -> O(T * S)
     * Concat complexity = O(T * S)
     * Thus, complexity of each iteration is O((T⋅S)+(T⋅S)) which is O(T⋅S)
     * <p>
     * The loop runs until target is a subsequence of concatenatedSource. Therefore, there will be at most T iterations.
     * Therefore, the time complexity of the while part is O(T⋅(T⋅S))=O(T^2 * S)
     * <p>
     * SC: O(T * S) ~ Concat S with T times
     * -----------------------------------------------------------------------------------------------------------------
     */
    public static int shortestWay(String source, String target) {
        boolean[] srcChar = new boolean[26];
        for (char c : source.toCharArray()) {
            srcChar[c - 'a'] = true;
        }

        for (char c : target.toCharArray()) {
            if (!srcChar[c - 'a']) return -1;
        }

        int res = 1;
        StringBuilder srcStr = new StringBuilder(source);

        while (!isSubsequence(target, srcStr.toString())) {
            res++;
            srcStr.append(source);
        }
        return res;
    }

    /**
     * Check if s is subsequence of t
     * ex: abc is subsequence of axbycz
     */
    private static boolean isSubsequence(String s, String t) {
        int p1 = 0, p2 = 0;

        while (p1 < s.length() && p2 < t.length()) {
            char c1 = s.charAt(p1);
            char c2 = t.charAt(p2);

            if (c1 == c2) {
                p1++;
                p2++;
            } else {
                p2++;
            }
        }

        return p1 == s.length();
    }

}
