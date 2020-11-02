package ru.mail.polis.ads.part5.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7622352
public final class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = Double.parseDouble(in.next());

        double l = 0;
        double r = c;

        while (r - l > 0.0000001) {
            double m = (l + r) / 2.0;

            if (f(m) > c) {
                r = m;
            } else {
                l = m;
            }
        }

        System.out.println(l);

    }

    static double f(double x) {
        return Math.pow(x, 2) + Math.pow(x, 0.5);
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
