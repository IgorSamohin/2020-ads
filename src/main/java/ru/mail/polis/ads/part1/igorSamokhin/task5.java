package ru.mail.polis.ads.part1.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7448393
public class task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        boolean flag = true;
        while (flag) {
            String str = in.next();
            switch (str) {
                case "push":
                    queue.push(in.nextInt());
                    System.out.println("ok");
                    break;
                case "pop":
                    try {
                        System.out.println(queue.pop());
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("error");
                    }
                    break;
                case "front":
                    try {
                        System.out.println(queue.front());
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("error");
                    }
                    break;
                case "clear":
                    queue.clear();
                    System.out.println("ok");
                    break;
                case "size":
                    System.out.println(queue.size());
                    break;
                case "exit":
                    System.out.println("bye");
                    flag = false;
                    break;
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

class Queue {
    public void push(Integer num) {
        list.addLast(num);
    }

    public Integer pop() {
        return list.pollFirst();
    }

    public Integer front() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return list.peekFirst();
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    private LinkedList<Integer> list = new LinkedList<Integer>();
}