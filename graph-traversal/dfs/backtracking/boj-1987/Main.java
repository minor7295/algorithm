import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] board;
    static boolean[] visited;  // 방문한 알파벳 체크 (A-Z: 0-25)
    static int maxCount = 0;
    
    // 상하좌우 이동 방향
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[26];  // 알파벳은 26개
        
        // 보드 입력
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        
        // 백트래킹 시작: 좌측 상단 (0, 0)에서 시작
        backtrack(0, 0, 1);
        
        System.out.println(maxCount);
    }
    
    /**
     * 백트래킹 함수: 2차원 보드에서 같은 알파벳을 두 번 지나지 않고 최대한 많은 칸을 지나는 경로 찾기
     * 
     * @param r 현재 행 위치
     * @param c 현재 열 위치
     * @param count 현재까지 지나온 칸의 개수
     */
    static void backtrack(int r, int c, int count) {
        char current = board[r][c];
        int idx = current - 'A';  // 알파벳을 인덱스로 변환 (A=0, B=1, ..., Z=25)
        
        // 이미 방문한 알파벳이면 종료 (가지치기)
        if (visited[idx]) {
            return;
        }
        
        // 상태 변경: 방문한 알파벳 체크
        visited[idx] = true;
        
        // 최대 경로 길이 갱신
        maxCount = Math.max(maxCount, count);
        
        // 상하좌우 네 방향으로 이동
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            // 보드 범위 체크
            if (isValid(nr, nc)) {
                backtrack(nr, nc, count + 1);
            }
        }
        
        // ✅ 상태 되돌리기: 방문한 알파벳 체크 해제하여 다른 경로 탐색 가능하도록 함
        // 백트래킹의 핵심!
        visited[idx] = false;
    }
    
    /**
     * 보드 범위 체크
     * 
     * @param r 행 위치
     * @param c 열 위치
     * @return 보드 범위 내에 있으면 true, 아니면 false
     */
    static boolean isValid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}

