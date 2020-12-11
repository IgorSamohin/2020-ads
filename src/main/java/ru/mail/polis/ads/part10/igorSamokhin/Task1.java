package ru.mail.polis.ads.part10.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/8021074
public final class Task1 {
    private static int N = 1;

    static class Node {
        int n;
        int to;

        Node(int to) {
            this.to = to;
            this.n = N;
        }
    }

    private static int timer = 0;
    private static int[] tin;
    private static int[] fup;
    private static ArrayList<Node>[] graph;
    private static boolean[] used;

    private static HashSet<Integer> foundedBridges = new HashSet<>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList[n + 1];
        tin = new int[n + 1];
        fup = new int[n + 1];
        used = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < m; i++) {
            int beg = in.nextInt();
            int end = in.nextInt();

            graph[beg].add(new Node(end));
            graph[end].add(new Node(beg));
            N++;
        }

        findBridge();

        ArrayList<Integer> outList = new ArrayList<>(foundedBridges);
        Collections.sort(outList);
        out.println(outList.size());
        for (int el : outList) {
            out.print(el + " ");
        }
    }

    private static void dfs(int v, int p) {
        used[v] = true;
        tin[v] = fup[v] = timer++;
        for (int i = 0; i < graph[v].size(); ++i) {
            int to = graph[v].get(i).to;
            if (to == p) {
                continue;
            }
            if (used[to]) {
                fup[v] = Math.min(fup[v], tin[to]);
            } else {
                dfs(to, v);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    int edge = graph[v].get(i).n;
                    foundedBridges.add(edge);
                }
            }
        }
    }

    public static void findBridge() {
        timer = 0;
        for (int i = 1; i < graph.length; ++i) {
            if (!used[i]) {
                dfs(i, -1);
            }
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

