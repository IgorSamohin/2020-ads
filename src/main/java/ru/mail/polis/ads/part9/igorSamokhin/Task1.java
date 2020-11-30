package ru.mail.polis.ads.part9.igorSamokhin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public final class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>(2 * n);
        for (int i = 1; i <= n; ++i) {
            graph.put(i, null);

        }

        for (int i = 0; i < m; ++i) {
            int node = in.nextInt();
            int edge = in.nextInt();
            ArrayList<Integer> edges = new ArrayList<>();
            if (graph.get(node) != null) {
                edges = graph.get(node);
            }
            edges.add(edge);
            graph.put(node, edges);
        }

        if (hasCycle(graph, new boolean[n], new boolean[n])) {
            out.println("-1");
            return;
        }
        String s = dfs(graph, n).substring(1);
        out.println(s);

    }

    /**
     * @param map     - graph
     * @param visited - array of visited nodes
     * @param colour  - array of colours. false = white, true = black
     */
    public static boolean hasCycle(HashMap<Integer, ArrayList<Integer>> map, boolean[] visited, boolean[] colour) {
        for (int i = 1; i <= map.size(); ++i) {
            if (map.get(i) != null) {
                visited[i - 1] = true;
                if (hasCycleHelp(map, visited, colour, i)) {
                    return true;
                }
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
            if (colour[j - 1] == true) {
                return true;
            }else if (visited[j - 1] == false) {
                if (hasCycleHelp(map, visited, colour, j)) {
                    return true;
                }
                visited[j - 1] = true;
            }
        }
        colour[i - 1] = false;
        return false;
    }

    public static String dfs(HashMap<Integer, ArrayList<Integer>> map, int n) {
        boolean[] visited = new boolean[n], colour = new boolean[n];
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= map.size(); ++i) {
            if (!visited[i - 1]) {
                stringBuilder.append(dfsHelp(map, visited, i));
            }
        }
        return stringBuilder.reverse().toString();
    }

    private static String dfsHelp(HashMap<Integer, ArrayList<Integer>> map, boolean[] visited, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        visited[i - 1] = true;

        if (map.get(i) == null) {
            stringBuilder.append(i).append(" ");
            return stringBuilder.toString();
        }

        for (int j : map.get(i)) {
            if (visited[j - 1] == false) {
                stringBuilder.append(dfsHelp(map, visited, j));
            }
        }
        stringBuilder.append(i).append(" ");
        return stringBuilder.toString();
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
