package leetcode.string;

public class E_2490_CircularSentence {
    public static void main(String[] args) {
        System.out.println(isCircularSentence("leetcode exercises sound delightful")); // true
        System.out.println(isCircularSentence("Leetcode is cool")); // false
    }

    public static boolean isCircularSentence(String sentence) {
        if (sentence.length() == 1) return true;
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) return false;

        for (int i = 1; i < sentence.length() - 1; ++i) {
            if (sentence.charAt(i) == ' ') {
                if (sentence.charAt(i - 1) != sentence.charAt(i + 1)) return false;
            }
        }
        return true;
    }
}
