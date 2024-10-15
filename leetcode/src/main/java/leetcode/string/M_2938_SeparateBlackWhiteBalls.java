package leetcode.string;

public class M_2938_SeparateBlackWhiteBalls {
    public static void main(String[] args) {
        System.out.println(new M_2938_SeparateBlackWhiteBalls().minimumSteps("101"));
        System.out.println(new M_2938_SeparateBlackWhiteBalls().minimumSteps("100"));
    }

    public long minimumSteps(String s) {
        int count = 0;
        int blackCount = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                count += blackCount;
            } else {
                blackCount++;
            }
        }
        return count;
    }
}
