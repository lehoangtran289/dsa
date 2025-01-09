package leetcode.string.trie;

// TODO
public class H_3045_CountPrefixAndSuffixPairII {
    public static void main(String[] args) {
        System.out.println(countPrefixSuffixPairs(new String[]{"pa", "papa", "ma", "mama"})); // 2
    }

    static Trie prefixTrie = new Trie();
    static Trie postfixTrie = new Trie();

    public static long countPrefixSuffixPairs(String[] words) {
        long res = 0;
        for (String word : words) {
            prefixTrie.insert(word);
            postfixTrie.insert(reverse(word));
        }

        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            if (prefixTrie.startsWith(word) && postfixTrie.startsWith(reverse(word))) res++;
        }

        return res;
    }

    private static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; --i) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isLeaf;
        long count = 0;

        TrieNode() {
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

        boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char chr : prefix.toCharArray()) {
                int index = chr - 'a';
                if (node.child[index] == null) {
                    return false;
                }
                node = node.child[index];
            }
            return node != null || node.isLeaf;
        }
    }
}
