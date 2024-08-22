package leetcode.string;

import java.util.Arrays;
import java.util.List;

public class _0_Trie {
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

    public static void main(String[] args) {
        Trie trie = new Trie();
        for (String s : Arrays.asList("and", "ant", "do", "geek", "dad", "ball")) {
            trie.insert(s);
        }

        List<String> searchKeys = Arrays.asList("do", "gee", "bat");
        for (String s : searchKeys) {
            System.out.print("Key : " + s + " -> ");
            if (trie.search(s))
                System.out.println("Present");
            else
                System.out.println("Not Present");
        }

        trie.display(trie.root, new char[100], 0);
    }
}
