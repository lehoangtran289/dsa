package codility;

public class _1_E_BinaryGap {

    public static void main(String[] args) {
        System.out.println(solution(9)); // 2
        System.out.println(solution(529)); // 4
        System.out.println(solution(20)); // 1
        System.out.println(solution(15)); // 0
        System.out.println(solution(32)); // 0
    }

    public static int solution(int N) {
        String binString = Integer.toBinaryString(N);
        int res = 0;
        int curCount = 0;

        for (int i = 0; i < binString.length(); ++i) {
            if (binString.charAt(i) == '0') {
                curCount++;
            } else {
                res = Math.max(res, curCount);
                curCount = 0;
            }
        }

        return res;
    }
}
