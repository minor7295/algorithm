import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        map = new int[n][n];
        visited = new boolean[n][n];
        
        // 지도 입력 받기
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        
        List<Integer> complexes = new ArrayList<>();
        
        // 모든 셀을 순회하며 1을 발견하면 DFS 시작
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    int houseCount = dfs(i, j);
                    complexes.add(houseCount);
                }
            }
        }
        
        // 단지 수 출력
        System.out.println(complexes.size());
        
        // 각 단지의 집 수를 오름차순으로 정렬하여 출력
        Collections.sort(complexes);
        for (int count : complexes) {
            System.out.println(count);
        }
    }
    
    // DFS로 연결된 집들을 탐색하며 집의 수를 카운트
    static int dfs(int x, int y) {
        visited[x][y] = true;
        int count = 1; // 현재 집 포함
        
        // 상하좌우 네 방향 확인
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // 범위 체크 및 방문 여부, 집 여부 확인
            if (nx >= 0 && nx < n && ny >= 0 && ny < n 
                && map[nx][ny] == 1 && !visited[nx][ny]) {
                count += dfs(nx, ny);
            }
        }
        
        return count;
    }
}

