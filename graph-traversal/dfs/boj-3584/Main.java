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
        int t = nextInt();
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < t; tc++) {
            int n = nextInt();

            int[] parent = new int[n + 1];
            boolean[] hasParent = new boolean[n + 1];
            List<List<Integer>> children = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                children.add(new ArrayList<>());
            }

            for (int i = 0; i < n - 1; i++) {
                int a = nextInt();
                int b = nextInt();
                parent[b] = a;
                hasParent[b] = true;
                children.get(a).add(b);
            }

            int u = nextInt();
            int v = nextInt();

            int root = 1;
            for (int i = 1; i <= n; i++) {
                if (!hasParent[i]) {
                    root = i;
                    break;
                }
            }

            int[] depth = new int[n + 1];
            buildDepth(root, children, depth);

            int lca = findLca(u, v, parent, depth);
            sb.append(lca).append('\n');
        }

        System.out.print(sb);
    }

    private static void buildDepth(int root, List<List<Integer>> children, int[] depth) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : children.get(cur)) {
                depth[next] = depth[cur] + 1;
                queue.offer(next);
            }
        }
    }

    private static int findLca(int u, int v, int[] parent, int[] depth) {
        while (depth[u] > depth[v]) {
            u = parent[u];
        }
        while (depth[v] > depth[u]) {
            v = parent[v];
        }

        while (u != v) {
            u = parent[u];
            v = parent[v];
        }

        return u;
    }

    private static int nextInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return Integer.parseInt(st.nextToken());
    }
}
