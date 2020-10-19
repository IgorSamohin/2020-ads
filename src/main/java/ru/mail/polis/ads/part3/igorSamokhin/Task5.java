package ru.mail.polis.ads.part3.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7543045
public final class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] stallCords = new int[n];

        for (int i = 0; i < n; i++) {
            stallCords[i] = in.nextInt();
        }

        int left = 0;
        int right = stallCords[stallCords.length - 1] - stallCords[0] + 1;
        while (right > left + 1) {
            int middle = (left + right) / 2;
            if (check(stallCords, middle, k)) {
                left = middle;
            } else {
                right = middle;
            }
        }
        System.out.println(left);
    }

    static boolean check(int[] arr, int x, int k) {
        int cows = 1;
        int lastCord = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] - lastCord >= x) {
                cows++;
                lastCord = arr[i];
            }
        }
        return cows >= k;
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
