package ru.mail.polis.ads.part3.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7542874
public final class Task3{
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Comparator<BigInteger> greater = Comparator.naturalOrder();
        Comparator<BigInteger> less = Comparator.reverseOrder();
        Heap max = new Heap(greater);
        Heap min = new Heap(less);
        BigInteger median = BigInteger.valueOf(0);

        for (int i = 0;; i++) {
            String str = in.reader.readLine();
            if(str == null)
                break;
            BigInteger num = BigInteger.valueOf(Long.parseLong(str));
            if (i % 2 == 0) {
                if (num.compareTo(max.peekHead().add(min.peekHead()).divide(BigInteger.valueOf(2))) < 0) {
                    max.insert(num);
                    median = max.extract();
                } else {
                    min.insert(num);
                    median = min.extract();
                }
                out.println(median);
            } else {
                if (num.compareTo(median) < 0) {
                    max.insert(num);
                    min.insert(median);
                } else {
                    min.insert(num);
                    max.insert(median);
                }
                out.println(max.peekHead().add(min.peekHead()).divide(BigInteger.valueOf(2)));
            }
        }

    }

    static class Heap {
        private ArrayList<BigInteger> heap = new ArrayList<>();
        private final Comparator<BigInteger> comparator;

        public Heap(Comparator<BigInteger> comparator) {
            heap.add(BigInteger.valueOf(1));
            this.comparator = comparator;
        }

        public int size() {
            return heap.size() - 1;
        }

        private void sink(int k) {
            while (2 * k <= size()) {
                int j = 2 * k;
                if (j < size() && (comparator.compare(heap.get(j), heap.get(j + 1)) < 0))
                    j++;
                if (comparator.compare(heap.get(k), heap.get(j)) >= 0)
                    break;
                BigInteger temp = heap.get(k);
                heap.set(k, heap.get(j));
                heap.set(j, temp);
                k = j;
            }
        }

        private void swim(int k) {
            while ((k > 1) && (comparator.compare(heap.get(k), heap.get(k / 2)) > 0)) {
                BigInteger temp = heap.get(k);
                heap.set(k, heap.get(k / 2));
                heap.set(k / 2, temp);
                k /= 2;
            }
        }

        public void insert(BigInteger num) {
            heap.add(num);
            swim(size());
        }

        public BigInteger extract() {
            BigInteger max = heap.get(1);

            heap.set(1, heap.get(size()));
            heap.set(size(), max);
            heap.remove(size());

            sink(1);
            return max;
        }

        public BigInteger peekHead() {
            return (size() > 0) ? heap.get(1) : BigInteger.valueOf(0);
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}