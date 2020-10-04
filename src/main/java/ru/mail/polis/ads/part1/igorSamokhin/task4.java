package ru.mail.polis.ads.part1.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7447499
public class task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        boolean flag = true;
        while (flag) {
            String str = in.next();
            switch (str) {
                case "push":
                    stack.push(in.nextInt());
                    System.out.println("ok");
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("error");
                    }
                    break;
                case "back":
                    try {
                        System.out.println(stack.back());
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("error");
                    }
                    break;
                case "clear":
                    stack.clear();
                    System.out.println("ok");
                    break;
                case "size":
                    System.out.println(stack.size());
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

class Stack {
    public void push(Integer num) {
        list.add(num);
        i++;
    }

    public Integer pop() {
        if (i == 0) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        Integer num = list.get(i - 1);
        list.remove(--i);
        return num;
    }

    public Integer back() {
        if (i == 0) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return list.get(i - 1);
    }

    public int size() {
        return i;
    }

    public void clear() {
        list.clear();
        i = 0;
    }

    private ArrayList<Integer> list = new ArrayList<Integer>();
    private int i = 0;
}