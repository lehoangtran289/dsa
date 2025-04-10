package leetcode.string.trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class M_139_WordBreak {
    public static void main(String[] args) {
        String s = "aaaaaaa";
        List<String> wordDict = List.of("aaaa", "aaa");
        System.out.println(new M_139_WordBreak().wordBreak2(s, wordDict));
    }

    /**
     * ------------------------------------------------
     * Top-down DP
     * ------------------------------------------------
     */
    private String s;
    private HashSet<String> wordDict;
    private Boolean[] memo;

    public boolean wordBreak2(String s, List<String> wordDict) {
        this.s = s;
        this.wordDict = new HashSet<>(wordDict);
        this.memo = new Boolean[s.length()];

        return dp(0);
    }

    private boolean dp(int i) {
        if (i == s.length()) return true;
        if (memo[i] != null) return memo[i];

        for (int j = i + 1; j <= s.length(); ++j) {
            if (
                    wordDict.contains(s.substring(i, j)) // prefix should in dict
                    && dp(j) // check if the rest of the string can be segmented
            ) {
                return memo[i] = true;
            }
        }

        return memo[i] = false;
    }

    /**
     * ------------------------------------------------
     * Trie + DP
     * ------------------------------------------------
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.insert(word);
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 0; i < s.length(); ++i) {
            if (dp[i]) {
                for (int j = i + 1; j <= s.length(); ++j) {
                    if (trie.search(s.substring(i, j))) {
                        dp[j] = true;
                    }
                }
            }
        }

        // add word trace


        return dp[s.length()];
    }

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isLeaf;

        TrieNode() {
            Arrays.fill(child, null);
            isLeaf = false;
        }
    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void insert(String key) {
            TrieNode node = root;
            for (char chr : key.toCharArray()) {
                int index = chr - 'a';
                if (node.child[index] == null) {
                    node.child[index] = new TrieNode(); // If node for current character does not exist then make a new node
                }
                node = node.child[index]; // Move the curr pointer to the newly created node
            }
            node.isLeaf = true;
        }

        boolean search(String key) {
            TrieNode node = root;
            for (char chr : key.toCharArray()) {
                int index = chr - 'a';
                if (node.child[index] == null) {
                    return false;
                }
                node = node.child[index];
            }
            return node != null && node.isLeaf;
        }

        boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char chr : prefix.toCharArray()) {
                int index = chr - 'a';
                if (node.child[index] == null) {
                    return false;
                }
                node = node.child[index];
            }
            return node != null;
        }

        void display(TrieNode node, char[] str, int level) {
            if (node.isLeaf) {
                str[level] = '\0';
                System.out.println(new String(str, 0, level));
            }
            for (int i = 0; i < 26; i++) {
                if (node.child[i] != null) {
                    str[level] = (char) (i + 'a');
                    display(node.child[i], str, level + 1);
                }
            }
        }
    }
}
