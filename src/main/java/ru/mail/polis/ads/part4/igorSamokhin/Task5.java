package ru.mail.polis.ads.part4.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7586330
public final class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(countInv(arr, 0, arr.length));
    }

    private static int merge(int[] to, int l, int r) {
        int middle = (l + r) / 2;

        int[] left = Arrays.copyOfRange(to, l, middle);
        int[] right = Arrays.copyOfRange(to, middle, r);

        int posLeft = 0;
        int posRight = 0;

        int invCount = 0;
        for (int i = 0; i < r - l; i++) {
            if (posLeft == left.length) {
                to[i + l] = right[posRight];
                posRight++;
            } else if (posRight == right.length) {
                to[i + l] = left[posLeft];
                posLeft++;
            } else if (left[posLeft] <= right[posRight]) {
                to[i + l] = left[posLeft];
                posLeft++;
            } else if (left[posLeft] > right[posRight]) {
                to[i + l] = right[posRight];
                posRight++;
                invCount += left.length - posLeft;
            }
        }
        return invCount;
    }

    private static int countInv(int[] arr, int i, int j) {
        if (j - i <= 1) {
            return 0;
        }

        int mid = (i + j) / 2;
        return countInv(arr, i, mid) + countInv(arr, mid, j) + merge(arr, i, j);
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

