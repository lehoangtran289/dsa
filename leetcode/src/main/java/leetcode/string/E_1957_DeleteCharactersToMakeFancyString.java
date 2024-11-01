package leetcode.string;

public class E_1957_DeleteCharactersToMakeFancyString {
    public static void main(String[] args) {
        System.out.println(new E_1957_DeleteCharactersToMakeFancyString().makeFancyString("leeetcode"));
    }

    public String makeFancyString(String s) {
        if (s.length() < 3) return s;
        StringBuilder result = new StringBuilder();
        result.append(s.charAt(0)).append(s.charAt(1));

        for (int i = 2; i < s.length(); ++i) {
            if (s.charAt(i) != result.charAt(result.length() - 1) ||
                    s.charAt(i) != result.charAt(result.length() - 2)
            ) {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }
}
