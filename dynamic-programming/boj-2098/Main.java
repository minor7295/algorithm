import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int INF = 16 * 1000000 + 1;  // 최대 비용보다 큰 값
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        W = new int[N][N];
        // dp[현재도시][방문한도시집합] = 최소 비용
        // 비트마스킹 사용: 1 << N = 2^N (모든 방문 조합 개수)
        dp = new int[N][1 << N];
        
        // 비용 행렬 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // DP 배열 초기화 (미계산 상태)
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // 0번 도시에서 시작, 0번만 방문한 상태 (비트마스킹: 1 << 0 = 1)
        int answer = solve(0, 1 << 0);
        
        System.out.println(answer);
    }
    
    // current: 현재 도시
    // visited: 방문한 도시 집합 (비트마스킹)
    // 반환: 현재 상태에서 나머지 도시를 모두 방문하고 시작 도시로 돌아오는 최소 비용
    static int solve(int current, int visited) {
        // 모든 도시를 방문한 경우 (비트마스킹: (1 << N) - 1 = 모든 비트가 1)
        if (visited == (1 << N) - 1) {
            // 시작 도시(0)로 돌아오는 비용
            if (W[current][0] == 0) {
                return INF;  // 갈 수 없는 경로
            }
            return W[current][0];
        }
        
        // 이미 계산된 경우
        if (dp[current][visited] != -1) {
            return dp[current][visited];
        }
        
        dp[current][visited] = INF;
        
        // 모든 미방문 도시로 이동
        for (int next = 0; next < N; next++) {
            // 이미 방문한 도시는 건너뛰기 (비트마스킹: visited & (1 << next) != 0)
            if ((visited & (1 << next)) != 0) {
                continue;
            }
            
            // 갈 수 없는 경로는 건너뛰기
            if (W[current][next] == 0) {
                continue;
            }
            
            // 다음 상태로 이동 (비트마스킹: visited | (1 << next))
            int nextVisited = visited | (1 << next);
            int cost = W[current][next] + solve(next, nextVisited);
            
            dp[current][visited] = Math.min(dp[current][visited], cost);
        }
        
        return dp[current][visited];
    }
}

