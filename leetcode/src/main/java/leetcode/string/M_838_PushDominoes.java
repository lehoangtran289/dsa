package leetcode.string;

public class M_838_PushDominoes {
    public static void main(String[] args) {
        System.out.println(pushDominoes("RR.L")); // "RR.L"
        System.out.println(pushDominoes(".L.R...LR..L..")); // "LL.RR.LLRRLL.."
        System.out.println(pushDominoes("R...L")); // "RRLL"
    }

    /**
     * Two Passes
     */
    public static String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] res = dominoes.toCharArray();
        int[] forces = new int[n];

        // update R force left -> right
        int curForce = 0;
        for (int i = 0; i < n; ++i) {
            if (dominoes.charAt(i) == 'R') {
                curForce = n;
            } else if (dominoes.charAt(i) == 'L') {
                curForce = 0;
            } else {
                forces[i] = curForce;
                if (curForce != 0) curForce--;
            }
        }

        // update L force right -> left
        curForce = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (dominoes.charAt(i) == 'L') {
                curForce = n;
            } else if (dominoes.charAt(i) == 'R') {
                curForce = 0;
            } else {
                forces[i] -= curForce;
                if (curForce != 0) curForce--;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (forces[i] > 0) res[i] = 'R';
            else if (forces[i] < 0) res[i] = 'L';
        }

        return String.valueOf(res);
    }
}
