package ru.mail.polis.ads.part2.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7495909
public final class Task5 {
    static class Robot {
        private int mainNum;
        private int auxiliaryNum;

        Robot(int mainNum, int auxiliaryNum) {
            this.mainNum = mainNum;
            this.auxiliaryNum = auxiliaryNum;
        }

        int getMainNum() {
            return mainNum;
        }

        int getAuxiliaryNum() {
            return auxiliaryNum;
        }

        int compareTo(Robot robot) {
            if (this.mainNum > robot.getMainNum()) {
                return 1;
            } else if (this.mainNum < robot.getMainNum()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        Robot[] robots = new Robot[N];
        for (int i = 0; i < N; i++) {
            robots[i] = new Robot(in.nextInt(), in.nextInt());
        }

        mergeSort(robots);

        for (int i = 0; i < N; i++) {
            out.print(robots[i].getMainNum());
            out.print(" ");
            out.println(robots[i].getAuxiliaryNum());
        }
    }

    private static void mergeSort(Robot[] array) {
        if (array.length == 1) {
            return;
        }
        int middle = array.length / 2;

        Robot[] leftArray = Arrays.copyOfRange(array, 0, middle);
        Robot[] rightArray = Arrays.copyOfRange(array, middle, array.length);

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(array, leftArray, rightArray);
    }

    private static void merge(Robot[] to, Robot[] left, Robot[] right) {
        int posLeft = 0;
        int posRight = 0;

        for (int i = 0; i < to.length; i++) {
            if (posLeft == left.length) {
                to[i] = right[posRight];
                posRight++;
            } else if (posRight == right.length) {
                to[i] = left[posLeft];
                posLeft++;
            } else if ((left[posLeft].compareTo(right[posRight]) == -1) || (left[posLeft].compareTo(right[posRight]) == 0)) {
                to[i] = left[posLeft];
                posLeft++;
            } else if (left[posLeft].compareTo(right[posRight]) == 1) {
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
