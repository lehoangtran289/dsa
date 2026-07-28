package leetcode.graph.trie;

import leetcode.utils.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

public class M_211_DesignAddAndSearchWordsDataStructure {

    private static Trie trie;

    public M_211_DesignAddAndSearchWordsDataStructure() {
        trie = new Trie();
    }

    public void addWord(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        return trie.search(word);
    }

    /**
     * DFS - Iterative using stack
     * ------------------------
     * Time: O(N) in the worst case where N is the number of nodes in the trie
     * Space: O(N) in the worst case where N is the number of nodes in the trie
     */
    public boolean search2(String word) {
        Deque<Pair<TrieNode, Integer>> stack = new ArrayDeque<>();

        stack.push(new Pair<>(trie.root, 0));

        while (!stack.isEmpty()) {
            Pair<TrieNode, Integer> cur = stack.pop();
            TrieNode curNode = cur.getKey();
            int wordIndex = cur.getValue();

            if (wordIndex == word.length()) {
                if (curNode.isWord) return true;
                continue;
            }

            if (word.charAt(wordIndex) == '.') {
                for (int i = 0; i < 26; ++i) {
                    if (curNode.nodes[i] != null) {
                        stack.push(new Pair<>(curNode.nodes[i], wordIndex + 1));
                    }
                }
            } else {
                int index = word.charAt(wordIndex) - 'a';
                if (curNode.nodes[index] != null) {
                    stack.push(new Pair<>(curNode.nodes[index], wordIndex + 1));
                }
            }
        }

        return false;
    }

    static class TrieNode {
        TrieNode[] nodes = new TrieNode[26];
        boolean isWord;
    }

    static class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode head = root;

            for (char c : word.toCharArray()) {
                int idx = c - 'a';

                if (head.nodes[idx] == null) {
                    head.nodes[idx] = new TrieNode();
                }
                head = head.nodes[idx];
            }
            head.isWord = true;
        }

        public boolean search(String word) {
            TrieNode head = root;
            return dfs(head, word, 0);
        }

        private boolean dfs(TrieNode head, String word, int start) {
            if (head == null) return false;
            if (start == word.length()) return head.isWord;

            char c = word.charAt(start);

            if (c == '.') {
                for (TrieNode child : head.nodes) {
                    if (dfs(child, word, start + 1)) {
                        return true;
                    }
                }
                return false;
            }
            return dfs(head.nodes[c - 'a'], word, start + 1);
        }
    }
}
