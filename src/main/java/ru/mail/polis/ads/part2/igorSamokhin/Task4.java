package ru.mail.polis.ads.part2.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7498775
public final class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        String numbers = scanner.nextLine();

        String[] arr = numbers.split(" ");
        BigInteger[] numbersArr = new BigInteger[arr.length];
        for (int i = 0; i < arr.length; i++) {
            numbersArr[i] = new BigInteger(arr[i]);
        }
        k = numbersArr.length - k;

        out.println(findKStatistic(numbersArr, k));
    }

    static BigInteger findKStatistic(BigInteger[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (true) {
            int mid = partition_HAAR(arr, left, right);
            if (mid == k) {
                return arr[mid];
            }else if (k < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    static int partition_HAAR(BigInteger[] arr, int l, int r) {
        int left = l;
        int right = r;
        BigInteger x = arr[(left + right) / 2];
        while (true) {
            while (arr[left].compareTo(x) < 0) {
                ++left;
            }

            while (arr[right].compareTo(x) > 0) {
                --right;
            }

            if (left >= right) {
                return right;
            }

            BigInteger temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
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
