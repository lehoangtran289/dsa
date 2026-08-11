package leetcode.bit;

public class M_393_UTF8Validation {
    static void main() {
        System.out.println(
                validUtf8(new int[] { 197, 130, 1 })); // Output: true

        System.out.println(
                validUtf8(new int[] { 235, 140, 4 })); // Output: false);
    }

    /**
     * Simulation
     * ---
     * TC: O(n)
     */
    public static boolean validUtf8(int[] data) {
        for (int i = 0; i < data.length; ++i) {
            int byteSize = getByteSize(data[i]);

            // check first byte
            if (byteSize == 1 || byteSize > 4)
                return false;

            // check following bytes
            while (byteSize-- > 1) {
                i++;
                if (i >= data.length || !isFollowByte(data[i]))
                    return false;
            }
        }

        return true;
    }

    private static int getByteSize(int num) {
        int res = 0;
        for (int i = 7; i >= 0; --i) {
            if (((num >> i) & 1) == 0) {
                break;
            }
            res++;
        }
        return res;
    }

    private static boolean isFollowByte(int num) {
        int firstMSB = (num >> 7) & 1;
        int secondMSB = (num >> 6) & 1;

        return firstMSB != 0 && secondMSB == 0;
    }
}
