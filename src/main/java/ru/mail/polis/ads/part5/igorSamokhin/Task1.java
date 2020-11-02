package ru.mail.polis.ads.part5.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7619084
public final class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();

        StringBuilder arrq = new StringBuilder();
        ArrayList<Integer> arr = new ArrayList<Integer>(N);

        for (int i = 1; i <= N; i++) {
            arr.add(i - 1, i);
        }

        while (true) {
            if (N == 1) {
                System.out.println(arr.get(0));
                break;
            }
            for (int i = 0; i < N; i++) {
                out.print(arr.get(i));
                out.print(" ");
            }
            out.println("");

            int index = N - 1;
            for (int i = N - 2; (i >= 0 )&& (index != 0); --i) {
                if (arr.get(i) > arr.get(index)) {
                    --index;
                } else {
                    break;
                }
            }

            if(index == 0){
                break;
            }

            int min = arr.get(N - 1);
            int minIndex = N - 1;
            for (int i = N - 1; i >= index; i--) {
                if (arr.get(i) > arr.get(index - 1)) {
                    min = arr.get(i);
                    minIndex = i;
                    break;
                }
            }

            int temp = arr.get(index - 1);
            arr.set(index - 1, min);
            arr.set(minIndex, temp);

            int k = N;
            for (int i = index; i < k; i++, k--) {
                Collections.swap(arr, i, k - 1);
            }

            --index;
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
