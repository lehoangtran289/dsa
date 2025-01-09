package leetcode.string.trie;

public class E_2185_CountingWordsWithAGivenPrefix {
    public int prefixCount(String[] words, String pref) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }

        return trie.countPrefix(pref);
    }
    
    static class Node {
        Node[] links = new Node[26];
        boolean isEnd;
        int count;

        Node() {
            isEnd = false;
            count = 0;
        }
    }

    static class Trie {
        Node root;

        Trie() {
            root = new Node();
        }

        void insert(String key) {
            Node node = root;
            for (char chr : key.toCharArray()) {
                int index = chr - 'a';
                if (node.links[index] == null) {
                    node.links[index] = new Node(); // If node for current character does not exist then make a new node
                }
                node = node.links[index]; // Move the curr pointer to the newly created node
                node.count++;
            }
            node.isEnd = true;
        }

        int countPrefix(String pref) {
            Node cur = root;
            for (int i = 0; i < pref.length(); ++i) {
                int index = pref.charAt(i) - 'a';
                if (cur.links[index] == null) {
                    return 0;
                }
                cur = cur.links[index];
            }
            return cur.count;
        }
    }
}
