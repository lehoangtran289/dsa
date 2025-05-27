package leetcode.string;

public class E_557_ReverseWordsInAStringIII {
    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest")); // Output: "s'teL ekat edoCteeL tsetnoc"
    }

    public static String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        StringBuilder curStr = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                res.append(curStr.reverse()).append(' ');
                curStr = new StringBuilder();
            } else {
                curStr.append(c);
            }
        }
        res.append(curStr.reverse());

        return res.toString();
    }
}
