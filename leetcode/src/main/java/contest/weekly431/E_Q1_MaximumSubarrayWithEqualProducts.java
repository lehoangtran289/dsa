package contest.weekly431;

public class E_Q1_MaximumSubarrayWithEqualProducts {
    public static void main(String[] args) {
        System.out.println(maxLength(new int[]{1, 2, 1, 2, 1, 1, 1}));
        System.out.println(maxLength(new int[]{2, 3, 4, 5, 6}));
        System.out.println(maxLength(new int[]{1, 2, 3, 1, 4, 5, 1}));
    }

    public static int maxLength(int[] nums) {
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            int gcd = nums[i];
            int lcm = nums[i];

            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                gcd = gcd(gcd, nums[j]);
                lcm = lcm(lcm, nums[j]);

                if (product == gcd * lcm) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}
