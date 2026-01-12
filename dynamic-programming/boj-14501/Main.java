import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] T, P;
    static int[] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        T = new int[N + 1];
        P = new int[N + 1];
        dp = new int[N + 2];  // dp[i]: i일부터 시작하여 얻을 수 있는 최대 이익
        
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        
        // 역순으로 계산
        // dp[N+1] = 0 (퇴사일 이후에는 이익 없음)
        for (int i = N; i >= 1; i--) {
            // i일의 상담을 하지 않는 경우
            dp[i] = dp[i + 1];
            
            // i일의 상담을 하는 경우
            // i일의 상담은 i일부터 (i + T[i] - 1)일까지 진행
            // 다음 상담은 (i + T[i])일부터 가능
            if (i + T[i] - 1 <= N) {  // N일 이내에 끝나는지 확인
                dp[i] = Math.max(dp[i], P[i] + dp[i + T[i]]);
            }
        }
        
        System.out.println(dp[1]);  // 1일부터 시작하여 얻을 수 있는 최대 이익
    }
}

