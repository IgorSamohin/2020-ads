package ru.mail.polis.ads.part5.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7619912
public final class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        BigInteger w = BigInteger.valueOf(in.nextInt());
        BigInteger h = BigInteger.valueOf(in.nextInt());
        BigInteger n = BigInteger.valueOf(in.nextInt());

        if (n.equals(BigInteger.valueOf(0))) {
            System.out.println(0);
            return;
        }

        BigInteger l = w.max(h);
        BigInteger r = w.max(h).multiply(n);

        while (l.compareTo(r) < 0) {
            BigInteger m = (l.add(r)).divide(BigInteger.valueOf(2));

            BigInteger count;
            if (h.equals(BigInteger.valueOf(0))) {
                count = m.divide(w);
            } else if (w.equals(BigInteger.valueOf(0))) {
                count = m.divide(h);
            } else {
                count = m.divide(h).multiply(m.divide(w));
            }

            if (n.compareTo(count) <= 0) {
                r = m;
            } else {
                l = m.add(BigInteger.valueOf(1));
            }
        }

        System.out.println(l);
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