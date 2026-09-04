package leetcode.backtrack;

public class M_3720_LexicographicallySmallestPermutationGreaterThanTarget {
    static void main() {
        System.out.println(lexGreaterPermutation("leet", "code")); // eelt
    }

    /**
     * Idea: Backtracking + Greedy
     * - Try to use same char as target first, if not possible then try next bigger char
     * - For same char, we need to check if the rest of the chars could form a greater string than target[pos:]
     * ---
     * TC: O(n * 26) = O(n)
     * SC: O(n)
     */
    public static String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        // build frequency array
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            char targetChar = target.charAt(i);

            // try tie char, if not possible, undo and try next bigger char
            if (freq[targetChar - 'a'] != 0) {
                freq[targetChar - 'a']--;

                if (hasGreaterForm(freq, target, i + 1)) {
                    res.append(targetChar);
                    continue;
                }
                freq[targetChar - 'a']++;
            }

            // try next bigger char
            int nextBigger = getNextBigger(freq, targetChar - 'a' + 1);
            if (nextBigger == -1) return "";

            freq[nextBigger]--;
            res.append((char) (nextBigger + 'a'));

            // fill in the rest of chars in ascending order
            for (int j = 0; j < 26; ++j) {
                while (freq[j] != 0) {
                    res.append((char) (j + 'a'));
                    freq[j]--;
                }
            }
            return res.toString();
        }

        return ""; // all chars are the same -> no result
    }

    /**
     * Check if current freq could form a greater string than target[pos:]
     */
    private static boolean hasGreaterForm(int[] freq, String target, int pos) {
        int[] freqClone = freq.clone();
        for (int i = 25; i >= 0; --i) {
            if (freqClone[i] == 0) continue;

            while (pos < target.length() && freqClone[i] != 0) {
                int targetIndex = target.charAt(pos) - 'a';
                if (i != targetIndex) return i > targetIndex;
                freqClone[i]--;
                pos++;
            }
        }
        return false;
    }

    private static int getNextBigger(int[] freq, int pos) {
        for (int i = pos; i < 26; ++i) {
            if (freq[i] != 0) return i;
        }
        return -1;
    }
}
