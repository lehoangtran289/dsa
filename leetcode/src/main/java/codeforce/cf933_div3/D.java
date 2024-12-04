package codeforce.cf933_div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class D {
    public static void main(String[] args) {
        MyScanner sc = new MyScanner();

        // Start writing your solution here. -------------------------------------
        int tests = sc.nextInt(); // number of test cases
        while (tests-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int x = sc.nextInt();
            Set<Integer> curPos = new HashSet<>();
            curPos.add(x);
            while (m-- > 0) {
                int dist = sc.nextInt();
                char dir = sc.nextChar();

                Set<Integer> newPos = new HashSet<>();
                for (int cur : curPos) {
                    if (dir == '0') {
                        newPos.add(newPos0(n, cur, dist));
                    } else if (dir == '1') {
                        newPos.add(newPos1(n, cur, dist));
                    } else if (dir == '?') {
                        newPos.add(newPos0(n, cur, dist));
                        newPos.add(newPos1(n, cur, dist));
                    }
                }
                curPos = newPos;
            }

            List<Integer> res = new ArrayList<>(curPos);
            Collections.sort(res);
            System.out.println(res.size());
            for (int num : res) {
                System.out.print(num + " ");
            }
        }

        // Stop writing your solution here. -------------------------------------
    }

    private static int newPos0(int n, int cur, int dist) {
        int res = (cur + dist) % n;
        return res == 0 ? n : res;
    }

    private static int newPos1(int n, int cur, int dist) {
        int res = (cur + n - dist) % n;
        return res == 0 ? n : res;
    }

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

        char nextChar() {
            return next().charAt(0);
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

}
