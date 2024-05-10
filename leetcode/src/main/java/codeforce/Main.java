package codeforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
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

        int n = sc.nextInt();        // read input as integer
        long k = sc.nextLong();       // read input as long
        double d = sc.nextDouble();     // read input as double
        String str = sc.next();           // read input as String
        String s = sc.nextLine();       // read whole line as String

        int result = 3 * n;
        System.out.println(result);

        int n1 = sc.nextInt();
        int k1 = sc.nextInt();
        int count = 0;
        while (n1-- > 0) {
            int x = sc.nextInt();
            if (x % k1 == 0)
                count++;
        }
        System.out.println(count);

        // Stop writing your solution here. -------------------------------------
    }
}
