package leetcode.VPC2024;

import java.util.ArrayList;
import java.util.List;

public class VPC_06 {
    private static final boolean IS_LOCAL = true;
    private static final String INPUT_FILE = "src/main/java/leetcode/VPC2024/input/06.inp";

    // ----------------------------------------------------------------

    private final static FastReader reader;
    private final static String YES = "Y";
    private final static String NO = "N";
    private final static long mod = (long) 1e9 + 7;

    // ----------------------------------------------------------------

    static {
        java.io.InputStream is;
        try {
            is = IS_LOCAL ? java.nio.file.Files.newInputStream(new java.io.File(INPUT_FILE).toPath()) : System.in;
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
        reader = new FastReader(is);
    }

    public static void main(String[] args) {
        try {
            java.io.PrintStream out = System.out;

            // INPUT -----------------------------------------------
            int m = readInt();
            List<Rule> rules = new ArrayList<>();

            long min = -1;
            long max = 1L << 33;

            for (int i = 0; i < m; ++i) {
                String commandStr = readStr();
                String ipStr = readStr();

                String[] temp = ipStr.split("/");
                String ip = temp[0];
                int n = temp.length == 1 ? 32 : Integer.parseInt(temp[1]);
                temp = ip.split("\\.");

                long x1 = Integer.parseInt(temp[0]);
                long x2 = Integer.parseInt(temp[1]);
                long x3 = Integer.parseInt(temp[2]);
                long x4 = Integer.parseInt(temp[3]);

                long start = (x1 << 24) | (x2 << 16) | (x3 << 8) | x4;
                long end;
                if (n == 32) {
                    end = start;
                } else {
                    end = (start + (1L << (32 - n)));
                    end -= 1;
                }

                switch (commandStr) {
                    case "A":
//                        System.out.println("A: " + start + " " + end);
                        rules.add(new Rule(1, start, end));
                        min = Math.min(start, min);
                        max = Math.max(end, max);
                        break;
                    case "D":
//                        System.out.println("D: " + start + " " + end);
                        rules.add(new Rule(0, start, end));
                        min = Math.min(start, min);
                        max = Math.max(end, max);
                        break;
                    case "?":
//                        System.out.print("Check: " + start + " => ");
                        if (start < min || start > max) System.out.println(NO);
                        else {
                            System.out.println(query(new Rule(-1, start, start), rules));
                        }
                        break;
                }
//                System.out.println("COMMAND:" + " " + type + " " + ip + " " + start + " " + end + " " + n);
            }
//            System.out.println(rules);

            // SOLUTION --------------------------------------------

            // ----------------------------------------------
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String query(Rule check, List<Rule> rules) {
        long ip = check.start;
        for (Rule rule : rules) {
            if (ip < rule.start || ip > rule.end) continue;
            if (rule.type == 1) return YES;
            else return NO;
        }
        return YES;
    }

    static class Rule {
        int type; // 1: A, 0: D
        long start;
        long end;

        Rule(int type, long start, long end) {
            this.type = type;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return type + " " + start + " " + end;
        }
    }

    // ======================================================================================

    private static String[] stringArray(int n, boolean oneIndexed) {
        int i = 0;
        String s[] = new String[n];
        if (oneIndexed) {
            i = 1;
            s = new String[n + 1];
            n++;
        }

        for (; i < n; i++) {
            s[i] = reader.next();
        }
        return s;
    }

    private static long readLong() {
        return reader.nextLong();
    }

    private static int[] intArray(int n, boolean oneIndexed) {
        int i = 0;
        int arr[] = new int[n];
        if (oneIndexed) {
            i = 1;
            arr = new int[n + 1];
            n++;
        }
        for (; i < n; i++) {
            arr[i] = reader.nextInt();
        }
        return arr;
    }

    private static long[] longArray(int n, boolean oneIndexed) {
        long arr[] = new long[n];
        int i = 0;
        if (oneIndexed) {
            i = 1;
            arr = new long[n + 1];
            n++;
        }
        for (; i < n; i++) {
            arr[i] = reader.nextLong();
        }
        return arr;
    }

    private static char[] charArray() {
        return readStr().toCharArray();
    }

    private static String readStr() {
        return reader.next();
    }

    private static int readInt() {
        return reader.nextInt();
    }

    private static void addToMap(int val, java.util.Map<Integer, Integer> map) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private static void removeFromMap(int val, java.util.Map<Integer, Integer> map) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    private static void addToMap(long val, java.util.Map<Long, Integer> map) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private static void removeFromMap(long val, java.util.Map<Long, Integer> map) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    private static void addToMap(char val, java.util.Map<Character, Integer> map) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private static void removeFromMap(char val, java.util.Map<Character, Integer> map) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    private static void addToMap(String val, java.util.Map<String, Integer> map) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private static void removeFromMap(String val, java.util.Map<String, Integer> map) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    private static int max(int... arr) {
        return java.util.Arrays.stream(arr).max().getAsInt();
    }

    private static int min(int... arr) {
        return java.util.Arrays.stream(arr).min().getAsInt();
    }

    private static long min(long... arr) {
        return java.util.Arrays.stream(arr).min().getAsLong();
    }

    private static long max(long... arr) {
        return java.util.Arrays.stream(arr).max().getAsLong();
    }

    private static long gcd(long a, long b) {
        if (a == 0) return b;

        return gcd(b % a, a);
    }

    private static int gcd(int a, int b) {
        if (a == 0) return b;

        return gcd(b % a, a);
    }

    private long multiplyMod(long a, long b) {
        return (a * b) % mod;
    }

    private long addMod(long a, long b) {
        return (a + b) % mod;
    }

    static class FastReader {
        java.io.BufferedReader br;
        java.util.StringTokenizer st;

        public FastReader() {
            br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        }

        public FastReader(java.io.InputStream is) {
            br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new java.util.StringTokenizer(br.readLine());
                } catch (java.io.IOException e) {
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

        int[] readIntArray(int arr[], int n) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        long[] readLongArray(long arr[], int n) {
            arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
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
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
