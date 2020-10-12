package ru.mail.polis.ads.part2.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7495753
public final class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = in.nextInt();
        }

        mergeSort(array);

        for (int i = 0; i < N; i++) {
            out.print(array[i]);
            out.print(" ");
        }
    }

    private static void mergeSort(int[] array) {
        if (array.length == 1) {
            return;
        }
        int middle = array.length / 2;

        int[] leftArray = Arrays.copyOfRange(array, 0, middle);
        int[] rightArray = Arrays.copyOfRange(array, middle, array.length);

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] to, int[] left, int[] right) {
        int posLeft = 0;
        int posRight = 0;

        for (int i = 0; i < to.length; i++) {
            if (posLeft == left.length) {
                to[i] = right[posRight];
                posRight++;
            } else if (posRight == right.length) {
                to[i] = left[posLeft];
                posLeft++;
            } else if (left[posLeft] <= right[posRight]) {
                to[i] = left[posLeft];
                posLeft++;
            } else if (left[posLeft] > right[posRight]) {
                to[i] = right[posRight];
                posRight++;
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
