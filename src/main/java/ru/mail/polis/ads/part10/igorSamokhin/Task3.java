package ru.mail.polis.ads.part10.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/325
public class Task3 {
    static ArrayList<Integer> p;
    static ArrayList<Integer> rank;
    static ArrayList<Node> graph;

    static class Node {
        Integer weight;
        int a;
        int b;

        Node(int weight, int a, int b) {
            this.weight = weight;
            this.a = a;
            this.b = b;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        p = new ArrayList<>();
        rank = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            p.add(i, i);
            rank.add(0);
        }

        graph = new ArrayList<>();
        graph.add(new Node(-1, -1, -1));

        for (int i = 1; i <= m; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            int w = in.nextInt();
            graph.add(new Node(w, a, b));
        }

        graph.sort(Comparator.comparing(a -> a.weight));

        int max = -1;
        for (int i = 1; i <= m; ++i) {
            Node temp = graph.get(i);
            if (find_set(temp.a) != find_set(temp.b)) {
                if (temp.weight > max) {
                    max = temp.weight;
                    if ((temp.b == n) || (temp.a == n)) {
                        break;
                    }
                }
                union_sets(temp.a, temp.b);
            }
        }

        System.out.println(max);
    }

    static int find_set(int v) {
        if (v == p.get(v)) {
            return v;
        }
        p.set(v, find_set(p.get(v)));
        return p.get(v);
    }

    static void union_sets(int a, int b) {
        a = find_set(a);
        b = find_set(b);
        if (a != b) {
            if (rank.get(a) < rank.get(b)) {
                int temp = b;
                b = a;
                a = temp;
            }
            p.set(b, a);
            if (rank.get(a) == rank.get(b)) {
                rank.set(a, (rank.get(a) + 1));
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