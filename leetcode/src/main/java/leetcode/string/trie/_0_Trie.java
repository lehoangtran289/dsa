package leetcode.string.trie;

import java.util.Arrays;

public class _0_Trie {
    public static int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int[] scores = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int score = 0;
            for (int j = 0; j < word.length(); j++) {
                String prefix = word.substring(0, j + 1);
                if (trie.search(prefix)) {
                    score += j + 1;
                }
            }
            scores[i] = score;
        }
        return scores;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sumPrefixScores(new String[]{"abc", "ab", "bc", "b"})));

//        Trie trie = new Trie();
//        for (String s : Arrays.asList("and", "ant", "do", "geek", "dad", "ball")) {
//            trie.insert(s);
//        }
//
//        List<String> searchKeys = Arrays.asList("do", "gee", "bat");
//        for (String s : searchKeys) {
//            System.out.print("Key : " + s + " -> ");
//            if (trie.search(s))
//                System.out.println("Present");
//            else
//                System.out.println("Not Present");
//        }
//
//        trie.display(trie.root, new char[100], 0);
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
