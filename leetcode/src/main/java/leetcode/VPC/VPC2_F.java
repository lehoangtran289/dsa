package leetcode.VPC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class VPC2_F {

    private static final boolean IS_LOCAL = true;
    private static final String INPUT_FILE = "src/main/java/leetcode/VPC/input/02.inp";
    private static final String OUTPUT_FILE = "src/main/java/leetcode/VPC/output/02.out";
    private final static FastReader reader;
    private final static String YES = "YES";
    private final static String NO = "NO";
    private final static long mod = (long) 1e9 + 7;

    // ----------------------------------------------------------------

    static {
        InputStream is;
        try {
            is = IS_LOCAL ? Files.newInputStream(new File(INPUT_FILE).toPath()) : System.in;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reader = new FastReader(is);
    }

    public static void main(String[] args) {
        try {
            PrintStream out = System.out;

            // INPUT -----------------------------------------------
            int m = readInt();
            int n = readInt();
            int k = readInt();

            Point start = null;
            int inpFlag = 0;
            char[][] graph = new char[n][m];
            for (int i = 0; i < n; ++i) {
                String s = readStr();
                if (inpFlag == 0 && s.indexOf('O') != -1) {
                    start = new Point(i, s.indexOf('O'));
                    inpFlag = 1;
                }
                graph[i] = s.toCharArray();
                out.println(Arrays.toString(graph[i]));
            }

            // SOLUTION --------------------------------------------

            String res = sol(n, m, k, graph, start);
            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            writer.write(res);

            writer.close();

            // ----------------------------------------------
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sol(int n, int m, int k, char[][] graph, Point start) {
        StringBuilder res = new StringBuilder();
        while (k-- > 0) {
            int countUp = 0;
            int curUp = start.x;
            for (int i = start.x - 1; i >= 0; i--) {
                if (graph[i][start.y] == '#') {
                    curUp = i + 1;
                    break;
                }
                if (graph[i][start.y] == '.')
                    countUp++;
            }

            int countDown = 0;
            int curDown = start.x;
            for (int i = start.x + 1; i < n; i++) {
                if (graph[i][start.y] == '#') {
                    curDown = i - 1;
                    break;
                }
                if (graph[i][start.y] == '.')
                    countDown++;
            }

            int countLeft = 0;
            int curLeft = start.y;
            for (int i = start.y - 1; i >= 0; i--) {
                if (graph[start.x][i] == '#') {
                    curLeft = i + 1;
                    break;
                }
                if (graph[start.x][i] == '.') {
                    countLeft++;
                }
            }

            int countRight = 0;
            int curRight = start.y;
            for (int i = start.y + 1; i < m; i++) {
                if (graph[start.x][i] == '#') {
                    curRight = i - 1;
                    break;
                }
                if (graph[start.x][i] == '.') {
                    countRight++;
                }
            }

            int max = max(countUp, countDown, countLeft, countRight);
            if (max == countUp) {
                res.append("U");
                for (int i = curUp; i <= start.x; i++) {
                    graph[i][start.y] = '+';
                }
                start.x = curUp;
            } else if (max == countDown) {
                res.append("D");
                for (int i = start.x; i <= curDown; i++) {
                    graph[i][start.y] = '+';
                }
                start.x = curDown;
            } else if (max == countLeft) {
                res.append("L");
                for (int i = curLeft; i <= start.y; i++) {
                    graph[start.x][i] = '+';
                }
                start.y = curLeft;
            } else {
                res.append("R");
                for (int i = start.y; i <= curRight; i++) {
                    graph[start.x][i] = '+';
                }
                start.y = curRight;
            }
            graph[start.x][start.y] = 'O';

            for (int i = 0; i < n; ++i) {
                System.out.println(Arrays.toString(graph[i]));
            }
            System.out.println("\nmax: " + max + " (" + start.x + " " + start.y + ") " + res);
        }

        return res.toString();
    }

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

    // ======================================================================================

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

    private static void addToMap(int val, Map<Integer, Integer> map) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private static void removeFromMap(int val, Map<Integer, Integer> map) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    private static void addToMap(long val, Map<Long, Integer> map) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private static void removeFromMap(long val, Map<Long, Integer> map) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    private static void addToMap(char val, Map<Character, Integer> map) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private static void removeFromMap(char val, Map<Character, Integer> map) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    private static void addToMap(String val, Map<String, Integer> map) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private static void removeFromMap(String val, Map<String, Integer> map) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    private static int max(int... arr) {
        return Arrays.stream(arr).max().getAsInt();
    }

    private static int min(int... arr) {
        return Arrays.stream(arr).min().getAsInt();
    }

    private static long min(long... arr) {
        return Arrays.stream(arr).min().getAsLong();
    }

    private static long max(long... arr) {
        return Arrays.stream(arr).max().getAsLong();
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

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
