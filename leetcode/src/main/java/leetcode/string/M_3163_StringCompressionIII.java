package leetcode.string;

public class M_3163_StringCompressionIII {
    public static void main(String[] args) {
        System.out.println(compressedString("aba"));
        System.out.println(compressedString("aabb"));
        System.out.println(compressedString("aaaaaaaaaaaaaabb"));
    }

    public static String compressedString(String word) {
        StringBuilder res = new StringBuilder();

        int i = 0;
        while (i < word.length()) {
            char cur = word.charAt(i);
            int count = 0;
            while (i < word.length() && word.charAt(i) == cur) {
                i++;
                count++;
            }

            if (count > 9) {
                while (count > 9) {
                    res.append(9).append(cur);
                    count -= 9;
                }
            }
            res.append(count).append(cur);
        }
        return res.toString();
    }
}
