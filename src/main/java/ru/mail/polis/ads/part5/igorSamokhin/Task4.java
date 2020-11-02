package ru.mail.polis.ads.part5.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7622760
public final class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String p = in.next();
        String s = in.next();

        boolean d[][] = new boolean[p.length() + 1][s.length() + 1];

        d[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if ((p.charAt(i - 1) == '?') || (s.charAt(j - 1) == p.charAt(i - 1))|| (s.charAt(j - 1) == '?')) {
                    d[i][j] = d[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*'|| s.charAt(j - 1) == '*') {
                    d[i][j] = d[i - 1][j] || d[i][j - 1] || d[i - 1][j - 1];
                } else {
                    d[i][j] = false;
                }
            }
        }
        System.out.println(d[p.length()][s.length()] ? "YES" : "NO");
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

