import java.io.*;
import java.util.*;

public class Main {
    static int A;
    static int B;
    static int C;
    static int[] limit;
    static boolean[][] visited;
    static boolean[] possible;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        limit = new int[]{A, B, C};
        visited = new boolean[A + 1][B + 1];
        possible = new boolean[C + 1];

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= C; i++) {
            if (possible[i]) {
                if (sb.length() > 0) sb.append(' ');
                sb.append(i);
            }
        }

        System.out.println(sb);
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int a = cur[0];
            int b = cur[1];
            int c = C - a - b;

            if (a == 0) {
                possible[c] = true;
            }

            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    if (from == to) continue;

                    int[] next = pour(a, b, c, from, to);
                    int na = next[0];
                    int nb = next[1];

                    if (!visited[na][nb]) {
                        visited[na][nb] = true;
                        q.offer(new int[]{na, nb});
                    }
                }
            }
        }
    }

    static int[] pour(int a, int b, int c, int from, int to) {
        int[] amount = new int[]{a, b, c};

        int move = Math.min(amount[from], limit[to] - amount[to]);
        amount[from] -= move;
        amount[to] += move;

        return new int[]{amount[0], amount[1]};
    }
}
