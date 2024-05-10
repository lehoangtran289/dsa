package codeforce.cf934_div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class B {
    static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();

        // Start writing your solution here. -------------------------------------
        int tests = sc.nextInt(); // number of test cases
        while (tests-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            List<Integer> a1 = new ArrayList<>();
            List<Integer> a2 = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                a1.add(sc.nextInt());
            }
            for (int i = 0; i < n; ++i) {
                a2.add(sc.nextInt());
            }
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (List<Integer> lTemp : findSubsets(a1, 2 * k)) {
                int lxor = xor(lTemp);
                map.put(lxor, lTemp);
            }
            printCombination(a2, n, 2 * k, map);
        }

        // Stop writing your solution here. -------------------------------------
    }

    public static void printCombination(List<Integer> arr, int n, int r, Map<Integer, List<Integer>> map) {
        int totalSubsets = 1 << n;  // Total number of subsets is 2^n
        for (int bitmask = 0; bitmask < totalSubsets; bitmask++) {
            // Count the number of set bits (1s) in the bitmask
            int count = 0;
            int temp = bitmask;
            while (temp > 0) {
                count += temp & 1;  // Check the least significant bit
                temp >>= 1;        // Right shift the bitmask
            }

            if (count == r) {
                int[] subset = new int[r];
                int index = 0;
                for (int i = 0; i < n; i++) {
                    if ((bitmask & (1 << i)) != 0)  // Check if the i-th bit is set
                        subset[index++] = arr.get(i);
                }
                if (map.containsKey(xor(subset))) {
                    List<Integer> l = map.get(xor(subset));
                    for (Integer integer : l) {
                        System.out.print(integer + " ");
                    }
                    System.out.println();
                    for (Integer integer : subset) {
                        System.out.print(integer + " ");
                    }
                    System.out.println();
                }
                return;
            }
        }
    }

    public static int xor(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static int xor(List<Integer> nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static Set<List<Integer>> findSubsets(List<Integer> nums, int k) {
        Set<List<Integer>> subsets = new HashSet<>();
        backtrack(nums, subsets, new ArrayList<>(), 0, k);
        return subsets;
    }

    private static void backtrack(List<Integer> nums, Set<List<Integer>> subsets, List<Integer> temp, int start, int k) {
        if (temp.size() == k) {
            subsets.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < nums.size(); i++) {
            temp.add(nums.get(i));
            backtrack(nums, subsets, temp, i + 1, k);
            temp.remove(temp.size() - 1);
        }
    }

}
