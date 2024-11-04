package leetcode.string;

public class M_3163_StringCompressionIII {
    public static void main(String[] args) {
        System.out.println(compressedString("aba"));
        System.out.println(compressedString("aabb"));
    }

    public static String compressedString(String word) {
        StringBuilder res = new StringBuilder();

        int i = 0;
        while (i < word.length()) {
            int count = 0;
            char cur = word.charAt(i);
            while (i < word.length() && word.charAt(i) == cur && count < 9) {
                i++;
                count++;
            }
            res.append(count).append(cur);
        }

        return res.toString();
    }
}
