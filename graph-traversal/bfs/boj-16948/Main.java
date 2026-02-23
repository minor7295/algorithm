import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DR = {-2, -2, 0, 0, 2, 2};
    private static final int[] DC = {-1, 1, -2, 2, -1, 1};

    private static int bfs(int n, int sr, int sc, int tr, int tc) {
        int[][] dist = new int[n][n];

        // 방문 여부 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = -1;
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        // 시작점
        queue.offer(new int[]{sr, sc});
        dist[sr][sc] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            // 도착하면 종료
            if (r == tr && c == tc) {
                return dist[r][c];
            }

            for (int i = 0; i < 6; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];

                // 경계 조건
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                    continue;
                }

                // 방문한 점은 처리 생략
                if (dist[nr][nc] != -1) {
                    continue;
                }

                // 이동 횟수 기록
                dist[nr][nc] = dist[r][c] + 1;

                queue.offer(new int[]{nr, nc});
            }
        }

        // 전체 탐색 마친 후에도 도착하지 못하면 -1 반환
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        System.out.println(bfs(n, r1, c1, r2, c2));
    }

}
