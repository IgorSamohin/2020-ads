package ru.mail.polis.ads.part4.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7583369
public final class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] arr = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        Node[] d = new Node[n + 1];

        for (int i = 0; i <= n; i++) {
            d[i] = new Node();
        }

        for (int i = m; i > 0; i--) {
            if (i != m) {
                d[1].addForwardStep(d[1]);
            }
            d[1].incrValue(arr[i][1]);
            for (int j = 2; j <= n; j++) {
                d[j].setMax(d[j - 1], d[j]);
                d[j].incrValue(arr[i][j]);
            }
        }
        System.out.println(d[n].path);
    }


    static class Node {
        private long value = 0;
        private StringBuilder path = new StringBuilder();

        public void incrValue(long value) {
            this.value += value;
        }

        public void addRightStep(Node left) {
            path = new StringBuilder(left.path);
            path.append("R");
        }

        public void addForwardStep(Node down) {
            path = new StringBuilder(down.path);
            path.append("F");
        }

        public void setMax(Node left, Node down) {
            if (left.value >= down.value) {
                value = left.value;
                addRightStep(left);
            } else {
                value = down.value;
                addForwardStep(down);
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
