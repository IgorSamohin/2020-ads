package ru.mail.polis.ads.part3.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7542977
public final class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for(int i = 0; i < q; i++)
        {
           isPresent(arr, in.nextInt());
        }

    }

    static void isPresent(int[] arr, int x) {
        int left = 0;
        int right = arr.length;
        int mid = (left + right) / 2;

        while (right >= left) {
            mid = (right + left) / 2;
            if (mid >= arr.length) {
                break;
            }
            if (arr[mid] == x) {
                System.out.println("YES");
                return;
            }
            if (x > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("NO");
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
