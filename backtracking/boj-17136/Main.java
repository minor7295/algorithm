import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[10][10];
    static int[] papers = {0, 5, 5, 5, 5, 5};  // 크기별 색종이 개수 (인덱스 1~5 사용)
    static int minCount = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 보드 입력
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 백트래킹 시작
        backtrack(0);
        
        // 결과 출력
        if (minCount == Integer.MAX_VALUE) {
            System.out.println(-1);  // 모든 1을 덮는 것이 불가능한 경우
        } else {
            System.out.println(minCount);
        }
    }
    
    /**
     * 백트래킹 함수: 모든 1을 덮는데 필요한 색종이의 최소 개수 찾기
     * 
     * @param count 현재까지 사용한 색종이 개수
     */
    static void backtrack(int count) {
        // 가지치기: 현재 개수가 최소값보다 크면 종료
        if (count >= minCount) {
            return;
        }
        
        // 첫 번째 덮이지 않은 1 찾기
        int[] pos = findFirstOne();
        if (pos == null) {
            // 모든 1이 덮임
            minCount = Math.min(minCount, count);
            return;
        }
        
        int r = pos[0];
        int c = pos[1];
        
        // 큰 종이부터 작은 종이까지 순서대로 시도
        for (int size = 5; size >= 1; size--) {
            // 해당 크기의 종이가 남아있고, (r, c) 위치에 놓을 수 있으면
            if (papers[size] > 0 && canPlace(r, c, size)) {
                // 상태 변경: 종이 놓기
                placePaper(r, c, size);
                papers[size]--;
                
                // 재귀 호출
                backtrack(count + 1);
                
                // ✅ 상태 되돌리기: 종이 제거 (백트래킹의 핵심!)
                removePaper(r, c, size);
                papers[size]++;
            }
        }
    }
    
    /**
     * 왼쪽 위부터 오른쪽 아래로 순회하여 첫 번째 덮이지 않은 1 찾기
     * 
     * @return 첫 번째 1의 위치 [r, c], 모든 1이 덮였으면 null
     */
    static int[] findFirstOne() {
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (board[r][c] == 1) {
                    return new int[]{r, c};
                }
            }
        }
        return null;  // 모든 1이 덮임
    }
    
    /**
     * (r, c) 위치에 size×size 크기의 종이를 놓을 수 있는지 확인
     * 
     * @param r 시작 행 위치
     * @param c 시작 열 위치
     * @param size 종이 크기 (1~5)
     * @return 놓을 수 있으면 true, 아니면 false
     */
    static boolean canPlace(int r, int c, int size) {
        // 범위 체크: 종이가 보드를 벗어나면 안 됨
        if (r + size > 10 || c + size > 10) {
            return false;
        }
        
        // 해당 영역이 모두 1인지 확인 (0이 있으면 안 됨)
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (board[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * (r, c) 위치에 size×size 크기의 종이를 놓기
     * 덮인 칸은 0으로 변경하여 덮였다는 것을 표시
     * 
     * @param r 시작 행 위치
     * @param c 시작 열 위치
     * @param size 종이 크기 (1~5)
     */
    static void placePaper(int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                board[i][j] = 0;  // 덮인 칸은 0으로 변경
            }
        }
    }
    
    /**
     * (r, c) 위치에 놓인 size×size 크기의 종이를 제거
     * 원래대로 복원하여 다른 경우를 탐색할 수 있도록 함
     * 
     * @param r 시작 행 위치
     * @param c 시작 열 위치
     * @param size 종이 크기 (1~5)
     */
    static void removePaper(int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                board[i][j] = 1;  // 원래대로 복원
            }
        }
    }
}

