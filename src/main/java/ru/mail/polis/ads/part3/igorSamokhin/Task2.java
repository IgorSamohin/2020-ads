package ru.mail.polis.ads.part3.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7539796
public final class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();

        Heap heap = new Heap();
        for (int i = 0; i < N; i++) {
            if (in.nextInt() == 0) {
                heap.insert(in.nextInt());
            } else {
                out.println(heap.extract());
            }
        }
        return;
    }

    static class Heap {
        private ArrayList<Integer> heap = new ArrayList<>();

        public Heap() {
            heap.add(1);
        }

        public int size() {
            return heap.size() - 1;
        }

        private void sink(int k) {
            while (2 * k <= size()) {
                int j = 2 * k;
                if (j < size() && heap.get(j) < heap.get(j + 1))
                    j++;
                if (heap.get(k) >= heap.get(j))
                    break;
                int temp = heap.get(k);
                heap.set(k, heap.get(j));
                heap.set(j, temp);
                k = j;
            }
        }

        private void swim(int k) {
            while ((k > 1) && (heap.get(k) > heap.get(k / 2))) {
                int temp = heap.get(k);
                heap.set(k, heap.get(k / 2));
                heap.set(k / 2, temp);
                k /= 2;
            }
        }

        public void insert(int num) {
            heap.add(num);
            swim(size());
        }

        public int extract() {
            int max = heap.get(1);

            heap.set(1, heap.get(size()));
            heap.set(size(), max);
            heap.remove(size());

            sink(1);
            return max;
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
