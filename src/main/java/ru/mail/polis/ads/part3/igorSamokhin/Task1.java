package ru.mail.polis.ads.part3.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7539366
public final class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] array = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            array[i] = in.nextInt();
        }

        for (int i = 1; i <= N / 2; i++) {
            int c1 = 2 * i;
            int c2 = 2 * i + 1;

            if (2 * i + 1 <= N) {
                if ((array[i] > array[2 * i]) || (array[i] > array[2 * i + 1])) {
                    System.out.println("NO");
                    return;
                }
            } else {
                if (array[i] > array[2 * i]) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
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
