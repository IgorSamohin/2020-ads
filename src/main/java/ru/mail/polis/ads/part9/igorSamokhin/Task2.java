package ru.mail.polis.ads.part9.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7973348 41 %
public final class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int max = -1;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            Integer node = in.nextInt();
            Integer edge = in.nextInt();
            ArrayList<Integer> edges = new ArrayList<>();
            if (map.containsKey(node)) {
                edges = map.get(node);
            }
            edges.add(edge);
            map.put(node, edges);

            if (map.containsKey(edge)) {
                edges = map.get(edge);
            } else {
                edges = new ArrayList<>();
            }
            edges.add(node);
            map.put(edge, edges);

            max = Math.max(Math.max(node, edge), max);
        }

        Stack<Integer> stack = new Stack<>();
        int min = hasCycle(map, max, stack);
        if (min != Integer.MAX_VALUE) {
            out.println("Yes");
            out.println(min);
            return;
        }
        out.println("No");
    }

    public static int hasCycle(HashMap<Integer, ArrayList<Integer>> map, int n, Stack<Integer> stack) {
        boolean[] visited = new boolean[n];
        boolean[] colour = new boolean[n];
        int min = Integer.MAX_VALUE;
        for (Map.Entry el : map.entrySet()) {
            int i = (int) el.getKey();
            visited[i - 1] = true;
            stack.push(i);
            if (hasCycleHelp(map, visited, colour, i, 0, stack)) {
                int s = stack.pop();
                while (!stack.isEmpty() && s != stack.peek()) {
                    min = Math.min(min, stack.pop());
                }
            }
            stack.clear();
        }
        return min;
    }

    /**
     * @param map     - graph
     * @param visited - array of visited nodes
     * @param colour  - array of colours. false = white, true = black
     */
    private static boolean hasCycleHelp(HashMap<Integer, ArrayList<Integer>> map, boolean[] visited, boolean[] colour,
                                        int child, int parent, Stack<Integer> stack) {
        colour[child - 1] = true;

        if (map.get(child) == null) {
            colour[child - 1] = false;
            return false;
        }
        for (int j : map.get(child)) {
            if (j != parent) {
                stack.push(j);
                if (colour[j - 1]) {
                    return true;
                } else if (!visited[j - 1]) {
                    if (hasCycleHelp(map, visited, colour, j, child, stack)) {
                        return true;
                    }
                    visited[j - 1] = true;
                    stack.pop();
                }
            }
        }
        colour[child - 1] = false;
        return false;
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
