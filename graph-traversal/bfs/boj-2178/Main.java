import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 미로 입력
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        
        // BFS를 위한 자료구조
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int[][] distance = new int[N][M];
        
        // 4방향 이동 (상하좌우)
        // 배열 관례: map[y][x]에서 y는 행(위→아래), x는 열(왼쪽→오른쪽)
        // dx[i]: i번째 방향의 x(열, 가로) 변화량
        // dy[i]: i번째 방향의 y(행, 세로) 변화량
        // 인덱스 0: 상 (dy=-1), 1: 하 (dy=1), 2: 좌 (dx=-1), 3: 우 (dx=1)
        // 주의: 배열에서 y(행)가 증가하면 아래로, x(열)가 증가하면 오른쪽으로
        int[] dx = {0, 0, -1, 1};  // 상, 하, 좌, 우의 x(열, 가로) 변화량
        int[] dy = {-1, 1, 0, 0};  // 상, 하, 좌, 우의 y(행, 세로) 변화량
        
        // 시작점 처리
        // 배열은 map[y][x] 형태로 사용 (y가 행, x가 열)
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        distance[0][0] = 1;  // 시작 위치 포함
        
        // BFS 탐색
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];  // y는 행 (위→아래)
            int x = current[1];  // x는 열 (왼쪽→오른쪽)
            
            // 도착점 확인
            if (y == N - 1 && x == M - 1) {
                System.out.println(distance[y][x]);
                return;
            }
            
            // 4방향 탐색
            // i=0: 상, i=1: 하, i=2: 좌, i=3: 우
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];  // y(행) 변화
                int nx = x + dx[i];  // x(열) 변화
                
                // 범위 체크, 방문 체크, 이동 가능 체크
                // 배열 인덱스는 [y][x] 순서 (y가 행, x가 열)
                if (ny >= 0 && ny < N && nx >= 0 && nx < M 
                    && !visited[ny][nx] && map[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    distance[ny][nx] = distance[y][x] + 1;
                    queue.offer(new int[]{ny, nx});
                }
            }
        }
    }
}

