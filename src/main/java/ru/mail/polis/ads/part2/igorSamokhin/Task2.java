package ru.mail.polis.ads.part2.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7495531
public final class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = in.nextInt();
        }

        for (int i = 0; i < N; i++) {
            boolean wereChanges = false;
            for (int j = 0; j < N - i - 1; j++) {
                if (((array[j] % 10) > (array[j + 1] % 10)) || ((array[j] % 10) == (array[j + 1] % 10)) && (array[j] > array[j + 1])) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    wereChanges = true;
                }
            }
            if (!wereChanges) {
                break;
            }
        }

        for (int i = 0; i < N; i++) {
            out.print(array[i]);
            out.print(" ");
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