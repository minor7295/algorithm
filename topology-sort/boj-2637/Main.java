import java.io.*;
import java.util.*;

public class Main {
    private static class Edge {
        int to;
        int count;

        Edge(int to, int count) {
            this.to = to;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // x를 만들기 위해 y가 k개 필요하므로 간선은 y -> x
            graph[y].add(new Edge(x, k));
            indegree[x]++;
        }

        int[][] need = calculateNeedTable(n, graph, indegree);
        System.out.print(buildOutput(n, need));
    }

    private static int[][] calculateNeedTable(int n, List<Edge>[] graph, int[] indegree) {
        int[][] need = new int[n + 1][n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        // 위상정렬 과정에서 indegree 값을 직접 감소시키며 소모한다.

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                need[i][i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Edge e : graph[cur]) {
                int next = e.to;
                int k = e.count;

                for (int b = 1; b <= n; b++) {
                    need[next][b] += need[cur][b] * k;
                }

                if (--indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        return need;
    }

    private static String buildOutput(int n, int[][] need) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (need[n][i] > 0) {
                sb.append(i).append(' ').append(need[n][i]).append('\n');
            }
        }
        return sb.toString();
    }
}
