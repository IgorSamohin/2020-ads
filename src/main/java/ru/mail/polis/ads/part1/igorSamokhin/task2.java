package ru.mail.polis.ads.part1.igorSamokhin;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7447428
public class task2 {

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int j = Integer.parseInt(in.next());
        for (int i = 0; i < j; i++) {
            String str = in.reader.readLine();
            BinaryTree binaryTree = new BinaryTree();
            binaryTree.parse(str);
            out.println(binaryTree.getPolka());
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

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class BinaryTree {
    public BinaryTree() {
    }

    public String getPolka() {
        if (head == null) {
            return "";
        }

        StringBuilder returnString = new StringBuilder();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(head);

        while (!queue.isEmpty()) {
            Node temp = queue.remove();
            returnString.append(temp.getData());

            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
        }
        return returnString.reverse().toString();
    }

    public void parse(String str) {
        Stack<Node> stack = new Stack<Node>();
        for (int k = 0; k < str.length(); k++) {
            char letter = str.charAt(k);
            if (letter >= 'a' && letter <= 'z') {
                stack.push(new Node(letter));
            } else {
                Node left = (stack.isEmpty() ? null : stack.pop());
                Node right = (stack.isEmpty() ? null : stack.pop());
                stack.push(new Node(letter, left, right));
            }
        }
        head = stack.pop();
    }

    private Node head = null;
}

class Node {
    public Node(char ch) {
        data = ch;
    }

    public Node(char ch, Node left, Node right) {
        data = ch;
        this.left = left;
        this.right = right;
    }

    char getData() {
        return data;
    }

    Node getLeft() {
        return left;
    }

    Node getRight() {
        return right;
    }

    private char data;
    private Node left = null;
    private Node right = null;
}