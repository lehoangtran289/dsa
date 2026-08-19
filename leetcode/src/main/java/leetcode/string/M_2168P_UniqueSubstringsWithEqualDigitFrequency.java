package leetcode.string;

import java.util.HashSet;
import java.util.Set;

public class M_2168P_UniqueSubstringsWithEqualDigitFrequency {
    static void main() {
        System.out.println(equalDigitFrequency3("1102021222")); // 16
    }

    /**
     * 3. Brute force + Trie
     * ---
     * TC: O(n^2)
     * SC: O(n^2) - trie to store unique substrings
     */
    public static int equalDigitFrequency3(String s) {
        int res = 0;
        Trie trie = new Trie();

        for (int i = 0; i < s.length(); ++i) {
            Node curNode = trie.root;
            int[] frequencies = new int[10];

            for (int j = i; j < s.length(); ++j) {
                // update frequencies and check if substring is valid
                int idx = s.charAt(j) - '0';
                frequencies[idx]++;

                boolean isValidSubstring = true;
                int curFrequency = frequencies[s.charAt(j) - '0'];

                for (int f : frequencies) {
                    if (f != 0 && f != curFrequency) {
                        isValidSubstring = false;
                        break;
                    }
                }

                // update trie
                if (curNode.links[idx] == null) {
                    curNode.links[idx] = new Node();
                }
                curNode = curNode.links[idx];

                // check if substring is valid and not registered to trie
                if (isValidSubstring && !curNode.isWord) {
                    curNode.isWord = true;
                    res++;
                }
            }
        }

        return res;
    }

    /**
     * 2. Brute force + rolling hash
     * ---
     * TC: O(n^2)
     * SC: O(n^2) - hash set to store unique substrings
     */
    public static int equalDigitFrequency2(String s) {
        int n = s.length();
        Set<Long> res = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            long hash = 0;
            int[] frequencies = new int[10];

            for (int j = i; j < n; ++j) {
                // process current char and check if the substring is valid
                int idx = s.charAt(j) - '0';
                frequencies[idx]++;

                boolean isValidSubstring = true;
                int curFrequency = frequencies[idx];

                for (int f : frequencies) {
                    if (f != 0 && f != curFrequency) {
                        isValidSubstring = false;
                        break;
                    }
                }

                // process with rolling hash to avoid creating substring
                hash = (hash * 127 + s.charAt(j)) ^ 0xC0FFEE;
                if (isValidSubstring) res.add(hash);
            }
        }

        return res.size();
    }

    /**
     * 1. Intuitive brute force
     * ---
     * TC: O(n^3)
     */
    public static int equalDigitFrequency1(String s) {
        int n = s.length();
        Set<String> res = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            int[] frequencies = new int[10];

            for (int j = i; j < n; ++j) {
                // process current char and check if the substring is valid
                int idx = s.charAt(j) - '0';
                frequencies[idx]++;

                boolean isValidSubstring = true;
                int curFrequency = frequencies[idx];

                for (int f : frequencies) {
                    if (f != 0 && f != curFrequency) {
                        isValidSubstring = false;
                        break;
                    }
                }

                // O(n) to create substring
                if (isValidSubstring)
                    res.add(s.substring(i, j + 1));
            }
        }

        return res.size();
    }

    static class Node {
        Node[] links = new Node[10];
        boolean isWord;
    }

    static class Trie {
        Node root = new Node();
    }
}
