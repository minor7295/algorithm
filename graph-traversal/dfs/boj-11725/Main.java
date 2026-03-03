import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int n = nextInt();

        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int u = nextInt();
            int v = nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] parent = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        bfsParent(1, graph, parent, visited);

        StringBuilder sb = new StringBuilder();
        for (int node = 2; node <= n; node++) {
            sb.append(parent[node]).append('\n');
        }

        System.out.print(sb);
    }

    private static void bfsParent(int root, List<List<Integer>> graph, int[] parent, boolean[] visited) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        visited[root] = true;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                parent[next] = cur;
                queue.offer(next);
            }
        }
    }

    private static int nextInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return Integer.parseInt(st.nextToken());
    }
}