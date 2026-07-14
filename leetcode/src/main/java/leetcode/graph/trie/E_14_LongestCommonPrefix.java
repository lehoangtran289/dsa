package leetcode.graph.trie;

public class E_14_LongestCommonPrefix {

    static void main() {
        System.out.println(longestCommonPrefix(new String[]{"a", "ab"})); // a
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"})); // fl
    }

    /**
     * Trie
     * ---
     * TC: O(n * m) ~ n = strs.length, m = (str with min len)
     * SC: O(n * m)
     */
    public static String longestCommonPrefix(String[] strs) {
        Trie trie = new Trie();
        for (String str : strs) {
            trie.insert(str);
        }
        return trie.commonPrefix();
    }

    static class Node {
        Node[] nodes = new Node[26];
        boolean isWord;
    }

    static class Trie {
        private final Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node cur = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';

                if (cur.nodes[idx] == null) {
                    cur.nodes[idx] = new Node();
                }
                cur = cur.nodes[idx];
            }
            cur.isWord = true;
        }

        public String commonPrefix() {
            Node cur = root;
            StringBuilder sb = new StringBuilder();

            while (!cur.isWord) {
                // find the only child
                int idx = -1;
                for (int i = 0; i < 26; ++i) {
                    if (cur.nodes[i] == null) continue;

                    if (idx != -1) { // second child -> stop
                        idx = -1;
                        break;
                    }
                    idx = i;
                }
                if (idx == -1) break; // no child or second child -> stop

                // if only one child, append the character and move to the child
                sb.append((char) (idx + 'a'));
                cur = cur.nodes[idx];
            }

            return sb.toString();
        }
    }
}
