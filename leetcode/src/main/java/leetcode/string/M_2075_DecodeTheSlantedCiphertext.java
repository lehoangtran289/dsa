package leetcode.string;

public class M_2075_DecodeTheSlantedCiphertext {
    public static void main(String[] args) {
        System.out.println(decodeCiphertext("ch   ie   pr", 3)); // "cipher"
    }

    /**
     * Simulation
     * ----
     * TC: O(n)
     * SC: O(n)
     */
    public static String decodeCiphertext(String encodedText, int rows) {
        StringBuilder res = new StringBuilder();
        int cols = encodedText.length() / rows;

        for (int j = 0; j < cols; ++j) {
            res.append(encodedText.charAt(j));

            int base = j + 1;
            for (int i = 1; i < rows; ++i) {
                int index = i * cols + base;
                if (index >= encodedText.length()) break;
                res.append(encodedText.charAt(index));
                base++;
            }
        }

        return res.toString().stripTrailing();
    }
}
