package ru.mail.polis.ads.part5.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7620200
public final class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int a[] = new int[N];

        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }

        int d[] = new int[N];

        for (int i = 0; i < d.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if ((a[j] != 0) && (a[i] % a[j] == 0) && (d[j] > max)) {
                    max = d[j];
                }
            }
            d[i] = max + 1;
        }

        int max = 1;
        for (int i = 0; i < d.length; i++) {
            if (d[i] > max) {
                max = d[i];
            }
        }
        System.out.println(max);

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