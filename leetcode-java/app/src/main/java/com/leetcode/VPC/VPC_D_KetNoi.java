package com.leetcode.VPC;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class VPC_D_KetNoi {
    static boolean IS_LOCAL = System.getenv("LOCAL_JUDGE") != null;
    static boolean DEBUG = false;
    static String INPUT_FILE = "input/D.inp";

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public double dist(Point other) {
            return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v;
        double d;

        public Edge(int u, int v, double d) {
            // Ensure u < v
            if (u > v) {
                int tmp = v;
                v = u;
                u = tmp;
            }

            this.u = u;
            this.v = v;
            this.d = d;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(d, o.d);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", v=" + v +
                    ", d=" + d +
                    '}';
        }
    }

    static int[] parent, size;

    static void makeSet(int v) {
        parent[v] = v;
        size[v] = 1;
    }

    static int findParent(int v) {
        return parent[v] == v ? v : (parent[v] = findParent(parent[v]));
    }

    static void union(int u, int v) {
        u = findParent(u);
        v = findParent(v);
        if (u != v) {
            if (size[u] < size[v]) {
                int tmp = v;
                v = u;
                u = tmp;
            }

            parent[v] = u;
            size[u] += size[v];
        }
    }

    public static void main(String[] args) {
        try {
            InputStream is = IS_LOCAL ? Files.newInputStream(new File(INPUT_FILE).toPath()) : System.in;
            OutputStream os = System.out;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            InputReader ir = new InputReader(is);

            // Process here----------------------------------------------------------------------
            int totalStation = ir.nextInt();
            List<Point> points = new ArrayList<>();
            for (int i = 0; i < totalStation; i++) {
                points.add(new Point(ir.nextDouble(), ir.nextDouble()));
            }

            // wifi, cable
            double wifiCost = ir.nextDouble(), cableCost = ir.nextDouble();

            double[][] d = new double[totalStation][totalStation];
            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < totalStation; i++) {
                for (int j = i + 1; j < totalStation; j++) {
                    d[i][j] = d[j][i] = points.get(i).dist(points.get(j));
                    edges.add(new Edge(i, j, d[i][j]));
                }
            }
            // ------------------------------------------
            Deque<Edge> edgesInMST = new ArrayDeque<>();
            int totalEdge = 0;
            double totalCost = 0.0;

            Collections.sort(edges);

            // Init dsu
            parent = new int[totalStation];
            size = new int[totalStation];
            for (int i = 0; i < totalStation; i++) {
                makeSet(i);
            }

            for (int i = 0; i < edges.size() && totalEdge < totalStation - 1; i++) {
                Edge cur = edges.get(i);
                int pu = findParent(cur.u);
                int pv = findParent(cur.v);

                if (pu != pv) {
                    totalEdge++;
                    edgesInMST.add(cur);
                    union(cur.u, cur.v);
                    totalCost += cableCost * cur.d;
                }
            }

            System.out.println(totalCost);
            debug(edgesInMST);

            // pop last element in edgesInMST
            Edge lastEdge = edgesInMST.pollLast();
            if (lastEdge.d * cableCost > wifiCost * 2) {
                totalCost = totalCost - lastEdge.d * cableCost + 2 * wifiCost;
            }

            while (!edgesInMST.isEmpty()) {
                Edge cur = edgesInMST.pollLast();
                if (cur.d * cableCost > wifiCost) {
                    totalCost -= cur.d * cableCost + wifiCost;
                }
            }

            debug("Total cost without wifi", totalCost);

            // BYPASS TEST CASE 35, JUST TO CHECK IF CURRENT ALGORITHM FAIL ONLY THIS TEST!!!!
            bw.write(String.format("%.9f\n", totalCost));

            // Display tree
            bw.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    static void debug(Object... args) {
        if (DEBUG) {
            for (int i = 0; i < args.length; ++i) {
                System.out.print(args[i] + " ");
            }
            System.out.println();
        }
    }
}
