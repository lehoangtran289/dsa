package leetcode.design;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class M_271_EncodeAndDecodeStrings {
    public static void main(String[] args) {
        System.out.println(decode(encode(List.of("Hello", "Wol,rd", "Leet,,code", ""))));
    }

    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.size(); ++i) {
            sb.append(strs.get(i));
            if (i != strs.size() - 1) {
                sb.append('π');
            }
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> res = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == 'π') {
                res.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(s.charAt(i));
            }
        }
        res.add(sb.toString());

        return res;
    }
}
