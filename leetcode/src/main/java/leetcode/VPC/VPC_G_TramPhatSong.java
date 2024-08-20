package leetcode.VPC;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class VPC_G_TramPhatSong {
    private static final boolean IS_LOCAL = true;
    private static final String INPUT_FILE = "src/main/java/leetcode/VPC/input/D.inp";
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
            int n = readInt();
            int x = readInt();
            int y = readInt();

            int[][] stations = new int[n][3];
            for (int i = 0; i < n; i++) {
                int[] arr = intArray(3, false);
                stations[i] = arr;
            }

            // SOLUTION --------------------------------------------
            out.println(sol(n, x, y, stations));

            // ----------------------------------------------
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static long sol(int n, int x, int y, int[][] stations) {
        List<Interval> xIntervals = new ArrayList<>();
        List<Interval> yIntervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int xi = stations[i][0];
            int yi = stations[i][1];
            int wi = stations[i][2];

            xIntervals.add(new Interval(Math.max(1, xi - wi), Math.min(x, xi + wi)));
            yIntervals.add(new Interval(Math.max(1, yi - wi), Math.min(y, yi + wi)));
        }

        long totalX = 0;
        long totalY = 0;
        List<Interval> mergedX = mergeIntervals(xIntervals);
        List<Interval> mergedY = mergeIntervals(yIntervals);

        for (Interval X : mergedX) {
            totalX += X.end - X.start + 1;
        }

        for (Interval Y : mergedY) {
            totalY += Y.end - Y.start + 1;
        }

        return totalX * y + totalY * x - totalX * totalY;
    }

    private static List<Interval> mergeIntervals(List<Interval> intervals) {
        intervals.sort((a, b) -> a.start - b.start); // ascending
        List<Interval> result = new ArrayList<>();

        result.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); ++i) {
            Interval cur = result.get(result.size() - 1);
            Interval interval = intervals.get(i);
            if (cur.end >= interval.start || cur.end + 1 == interval.start) {
                cur.end = Math.max(cur.end, interval.end);
            } else {
                result.add(interval);
            }
        }
        return result;
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
