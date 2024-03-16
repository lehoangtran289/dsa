package com.codeforce.cf933_div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C {
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
            int n = sc.nextInt(); // read input as integer
            String s = sc.nextLine(); // read input as String
            solution(n, s);
        }

        // Stop writing your solution here. -------------------------------------
    }

    private static void solution(int n, String str) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (str.startsWith("mapie", i)) {
                count++;
                i += 4;
            } else if (str.startsWith("map", i)) {
                count++;
                i += 2;
            } else if (str.startsWith("pie", i)) {
                count++;
                i += 2;
            }
        }
        System.out.println(count);
    }

}
