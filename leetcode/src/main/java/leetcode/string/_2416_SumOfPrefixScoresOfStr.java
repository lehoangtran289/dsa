package leetcode.string;

import java.util.Arrays;

public class _2416_SumOfPrefixScoresOfStr {
    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
        int score;

        TrieNode() {
            Arrays.fill(child, null);
            isWord = false;
            score = 1;
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
                } else {
                    node.child[index].score++;
                }
                node = node.child[index]; // Move the curr pointer to the newly created node
            }
            node.isWord = true;
        }

        int search(String key) {
            TrieNode node = root;
            int score = 0;
            for (char chr : key.toCharArray()) {
                int index = chr - 'a';
                if (node.child[index] == null) {
                    return 0;
                } else {
                    score += node.child[index].score;
                }
                node = node.child[index];
            }
            if (node != null && node.isWord) return score;
            else return 0;
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
            if (node.isWord) {
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
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _2416_SumOfPrefixScoresOfStr().sumPrefixScores(new String[]{"abc", "ab", "bc", "b"})));
    }

    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int[] res = new int[words.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = trie.search(words[i]);
        }
        return res;
    }
}
