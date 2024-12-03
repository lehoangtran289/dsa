package leetcode.string;

public class M_2109_AddingSpacesToAString {
    public static void main(String[] args) {
        System.out.println(addSpaces("LeetcodeHelpsMeLearn", new int[]{8, 13, 15}));
    }

    public static String addSpaces(String s, int[] spaces) {
        StringBuilder res = new StringBuilder();

        int spaceIndex = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (spaceIndex < spaces.length && i == spaces[spaceIndex]) {
                res.append(' ');
                spaceIndex++;
            }
            res.append(s.charAt(i));
        }

        return res.toString();
    }
}
