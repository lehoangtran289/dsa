package leetcode.graph.trie;

/**
 * Problem: Find the length of the longest common prefix of two integer arrays arr1 and arr2.
 */
public class M_3043_FindTheLengthOfTheLongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(
                new int[]{1, 10, 100},
                new int[]{1000, 100000}
        )); // 3
    }

    /**
     * Trie
     * ---
     * TC: O(n * D + m * D) = O(n + m), where D is number of digit of num in both array (D <= 8)
     * SC: O(n * D) = O(n)
     */
    public static int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie trie = new Trie();

        // build trie with arr1
        for (int num : arr1) {
            trie.insert(num);
        }

        // find the longest common prefix with arr2
        int res = 0;
        for (int num : arr2) {
            res = Math.max(res, trie.getPrefixLength(num));
        }

        return res;
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[10]; // 0 -- 9
    }

    static class Trie {
        TrieNode root;

        Trie() {
            this.root = new TrieNode();
        }

        void insert(int num) {
            TrieNode node = root;
            String numStr = num + "";

            for (char digitChar : numStr.toCharArray()) {
                int index = digitChar - '0';

                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
        }

        int getPrefixLength(int num) {
            TrieNode node = root;
            String numStr = num + "";

            int res = 0;
            for (char digitChar : numStr.toCharArray()) {
                int index = digitChar - '0';

                if (node.children[index] == null) break;
                res++;
                node = node.children[index];
            }

            return res;
        }
    }
}
