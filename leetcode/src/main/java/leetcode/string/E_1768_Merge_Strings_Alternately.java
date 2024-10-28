package leetcode.string;

public class E_1768_Merge_Strings_Alternately {
    public static void main(String[] args) {
        System.out.println(new E_1768_Merge_Strings_Alternately().mergeAlternately("abc", "pqr"));
    }

    public String mergeAlternately(String word1, String word2) {
        int p1 = 0, p2 = 0;

        StringBuilder str = new StringBuilder();
        boolean isP1 = true;
        while (p1 < word1.length() && p2 < word2.length()) {
            if (isP1) {
                str.append(word1.charAt(p1));
                p1++;
                isP1 = false;
            } else {
                str.append(word2.charAt(p2));
                p2++;
                isP1 = true;
            }
        }

        while (p1 < word1.length()) {
            str.append(word1.charAt(p1));
            p1++;
        }

        while (p2 < word2.length()) {
            str.append(word2.charAt(p2));
            p2++;
        }

        return str.toString();
    }
}
