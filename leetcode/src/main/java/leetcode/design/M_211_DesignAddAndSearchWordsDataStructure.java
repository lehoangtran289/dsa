package leetcode.design;

import leetcode.utils.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

public class M_211_DesignAddAndSearchWordsDataStructure {

    private final Node root;

    public M_211_DesignAddAndSearchWordsDataStructure() {
        this.root = new Node();
    }

    public void addWord(String word) {
        Node head = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (head.links[index] == null) head.links[index] = new Node();
            head = head.links[index];
        }
        head.isWord = true;
    }

    /**
     * DFS - Iterative using stack
     * ------------------------
     * Time: O(N) in the worst case where N is the number of nodes in the trie
     * Space: O(N) in the worst case where N is the number of nodes in the trie
     */
    public boolean search(String word) {
        Deque<Pair<Node, Integer>> stack = new ArrayDeque<>();

        stack.push(new Pair<>(root, 0));

        while (!stack.isEmpty()) {
            Pair<Node, Integer> cur = stack.pop();
            Node curNode = cur.getKey();
            int wordIndex = cur.getValue();

            if (wordIndex == word.length()) {
                if (curNode.isWord) return true;
                continue;
            }

            if (word.charAt(wordIndex) == '.') {
                for (int i = 0; i < 26; ++i) {
                    if (curNode.links[i] != null) {
                        stack.push(new Pair<>(curNode.links[i], wordIndex + 1));
                    }
                }
            } else {
                int index = word.charAt(wordIndex) - 'a';
                if (curNode.links[index] != null) {
                    stack.push(new Pair<>(curNode.links[index], wordIndex + 1));
                }
            }
        }

        return false;
    }

    /**
     * DFS - Recursive
     * ------------------------
     * Time: O(N) in the worst case where N is the number of nodes in the trie
     * Space: O(N) in the worst case where N is the number of nodes in the trie
     */
    public boolean search2(String word) {
        return dfs(root, word, 0);
    }

    public boolean dfs(Node node, String word, int index) {
        if (node == null) return false;
        if (index == word.length()) return node.isWord;

        char c = word.charAt(index);

        if (c == '.') {
            for (Node child : node.links) {
                if (dfs(child, word, index + 1))
                    return true;
            }
        } else {
            return dfs(node.links[c - 'a'], word, index + 1);
        }
        return false;
    }

    static class Node {
        Node[] links = new Node[26];
        boolean isWord = false;
    }
}
