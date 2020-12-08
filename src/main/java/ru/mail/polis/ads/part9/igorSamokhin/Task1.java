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

//https://www.e-olymp.com/ru/submissions/7982191  - 45%
public final class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>(n);

        int max = 0;
        for (int i = 0; i < m; ++i) {
            int node = in.nextInt();
            int edge = in.nextInt();
            ArrayList<Integer> edges = new ArrayList<>();
            if (graph.get(node) != null) {
                edges = graph.get(node);
            }
            edges.add(edge);
            graph.put(node, edges);
            max = Math.max(Math.max(node, edge), max);
        }

        if (hasCycle(graph, new boolean[max], new boolean[max])) {
            out.println("-1");
            return;
        }
        Stack<Integer> stack = new Stack<>();
        dfs(graph, max, stack);

        while(!stack.isEmpty()){
            out.print(stack.pop() + " ");
        }
    }
//    14 23
//    1 6 1 5 1 12 1 4
//    2 5 2 9 2 3
//    3 6 3 7 3 10
//    4 3 4 7 4 14
//    5 8
//    6 9 6 13
//    7 6 7 1
//    9 8
//    10 12 10 11
//    11 14
//    13 10

    /**
     * @param map     - graph
     * @param visited - array of visited nodes
     * @param colour  - array of colours. false = white, true = black
     */
    public static boolean hasCycle(HashMap<Integer, ArrayList<Integer>> map, boolean[] visited, boolean[] colour) {
        for (Map.Entry el : map.entrySet()) {
            int i = (int) el.getKey();
            visited[i - 1] = true;
            if (hasCycleHelp(map, visited, colour, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCycleHelp(HashMap<Integer, ArrayList<Integer>> map, boolean[] visited, boolean[] colour, int i) {
        colour[i - 1] = true;

        if (map.get(i) == null) {
            colour[i - 1] = false;
            return false;
        }
        for (int j : map.get(i)) {
            if (colour[j - 1]) {
                return true;
            } else {
                if (hasCycleHelp(map, visited, colour, j)) {
                    return true;
                }
                visited[j - 1] = true;
            }
        }
        colour[i - 1] = false;
        return false;
    }

    public static void dfs(HashMap<Integer, ArrayList<Integer>> map, int n, Stack<Integer> stack) {
        boolean[] visited = new boolean[n];

        for (Map.Entry el : map.entrySet()) {
            int i = (int) el.getKey();
            if (!visited[i - 1]) {
                dfsHelp(map, visited, i, stack);
            }
        }
    }

    private static void dfsHelp(HashMap<Integer, ArrayList<Integer>> map, boolean[] visited, int i, Stack<Integer> stack) {
        visited[i - 1] = true;

        if (map.get(i) == null) {
            stack.push(i);
            return;
        }

        for (int j : map.get(i)) {
            if (!visited[j - 1]) {
                dfsHelp(map, visited, j, stack);
            }
        }
        stack.push(i);
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

