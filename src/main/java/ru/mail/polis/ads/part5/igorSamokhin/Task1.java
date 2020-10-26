package ru.mail.polis.ads.part5.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public final class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();

        StringBuilder arr = new StringBuilder();
        ArrayList<Integer> arr = new ArrayList<Integer>(N);

        for (int i = 1; i <= N; i++) {
            arr.set(i - 1, i);
        }

        while (true) {

            if (N == 1) {
                System.out.println(arr.get(0));
                break;
            }

            int min = arr.get(N - 1);
            int index = N - 1;
            int minIndex = arr.get(N - 1);
            for (int i = N - 2; i > 0; i--) {
                if (arr.get(i) < min) {
                    min = arr.get(i);
                    minIndex = i;
                }

                if (arr.get(i + 1) > arr.get(i)) {
                    index = i;
                    break;
                }
            }

            int temp = arr.get(index);
            arr.set(index, min);
            arr.set(minIndex, temp);

            for(int i = index; i < N; i++)
            {
                Collections.swap(arr, i, N-i);
                //do reverse from index to end
            }



            for (int i = 0; i < arr.length(); i++) {
                out.print(arr.charAt(i));
                out.print(" ");
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
