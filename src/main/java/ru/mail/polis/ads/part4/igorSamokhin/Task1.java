package ru.mail.polis.ads.part4.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class Task1 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        StringBuilder str = new StringBuilder();
        str.append(in.next());

        int n = str.length();
        int[][] d = new int[n][n];

        StringBuilder[][] stringBuilders = new StringBuilder[n][n];

        for (int i = 0; i < n; i++) {
            d[i][i] = 1;
            stringBuilders[i][i] = new StringBuilder()
                    .append(getPairedBrackets(str.charAt(i)));
        }

        for (int j = n - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
//                d[i][i + n - j];
                if ((str.charAt(i) == '[' && str.charAt(i + n - j) == ']')
                        || (str.charAt(i) == '(' && str.charAt(i + n - j) == ')')) {
                    d[i][i + n - j] = d[i + 1][i + n - j];
                    stringBuilders[i][i + n - j] = new StringBuilder()
                            .append(str.charAt(i))
                            .append(stringBuilders[i + 1][i + n - j -1])
                            .append(str.charAt(i + n - j));
                } else {
                    int min = d[i][i] + d[i + 1][i+n-j];
                    StringBuilder subString = new StringBuilder()
                            .append(stringBuilders[i][i])
                            .append(stringBuilders[i + 1][i + n -j]);

                    for (int k = i; k < i + n - j; k++) {
                        if ((d[i][k] + d[k + 1][i + n - j]) < min) {
                            min = d[i][k] + d[k + 1][i + n - j];
                            subString = new StringBuilder()
                                    .append(stringBuilders[i][k])
                                    .append(stringBuilders[k + 1][n - 1]);
                        }
                    }
                    d[i][i + n - j] = min;
                    stringBuilders[i][i + n - j] = new StringBuilder()
                            .append(subString);
                }

            }

        }
        System.out.println(stringBuilders[0][n - 1]);
    }


    static String getPairedBrackets(char br) {
        if ((br == '(') || (br == ')')) {
            return "()";
        } else if ((br == '[') || (br == ']')) {
            return "[]";
        }
        return "";
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
