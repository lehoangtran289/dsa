package leetcode.string;

public class E_1455_CheckIfAWordOccursAsAPrefixOfAnyWordInASentence {
    public static void main(String[] args) {
        System.out.println(isPrefixOfWord("love errichto jonathan dumb", "dumb"));
        System.out.println(isPrefixOfWord("this problem is an easy problem", "pro"));
    }

    public static int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) return i + 1;
        }
        return -1;
    }

    public static int isPrefixOfWordNoBuiltIn(String sentence, String searchWord) {
        // Check for invalid input
        if (sentence == null || searchWord == null || searchWord.length() > sentence.length()) {
            return -1;
        }

        int wordNo = -1;

        int startWord = 0;
        for (int i = 0; i < sentence.length(); ++i) {
            if (sentence.charAt(i) == ' ' || i == sentence.length() - 1) {
                wordNo++;
                if (isPrefix(sentence.substring(startWord, i + 1), searchWord)) {
                    return wordNo + 1;
                } else {
                    startWord = i + 1;
                }
            }
        }

        return -1;
    }

    private static boolean isPrefix(String str, String searchWord) {
        int p1 = 0, p2 = 0;
        while (
                p1 < str.length() &&
                p2 < searchWord.length() &&
                str.charAt(p1) == searchWord.charAt(p2)
        ) {
            p1++;
            p2++;
        }

        return p2 == searchWord.length();
    }
}
