package leetcode.string;

// Same as M_616P_AddBoldTagInString
public class M_758P_BoldWordsInString {
    private static final String BOLD_START = "<b>";
    private static final String BOLD_END = "</b>";

    static void main() {
        System.out.println(boldWords(new String[]{"ab", "bc"}, "aabcd")); // a<b>abc</b>d
        System.out.println(boldWords(new String[]{"ab", "cd"}, "aabcd")); // a<b>abcd</b>
        System.out.println(boldWords(new String[]{"ab", "bc"}, "aabcde")); // a<b>abc</b>de
    }

    /**
     * Simulation, string pattern matching (with Java API)
     * ---
     * TC: O(n^2 * m * k) ~ n = s.length(), m = words.length(), k = average length of words[i]
     * SC: O(n)
     */
    public static String boldWords(String[] words, String s) {
        StringBuilder res = new StringBuilder();
        boolean[] isBold = new boolean[s.length()];

        // Mark the characters that need to be bolded by checking for each word in the list
        // O(m * n^2 * k)
        for (String word : words) { // O(m)
            int idx = s.indexOf(word); // O(n * k)
            while (idx != -1) { // O(n)
                for (int i = 0; i < word.length(); ++i) {
                    isBold[idx + i] = true;
                }
                idx = s.indexOf(word, idx + 1); // O(n * k)
            }
        }

        // Build the result string with bold tags
        for (int i = 0; i < s.length(); ++i) {
            if (!isBold[i]) {
                res.append(s.charAt(i));
                continue;
            }

            res.append(BOLD_START);
            while (i < s.length() && isBold[i]) {
                res.append(s.charAt(i));
                i++;
            }
            res.append(BOLD_END);
            i--;
        }

        return res.toString();
    }
}
