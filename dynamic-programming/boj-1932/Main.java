import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        // 삼각형 입력
        int[][] triangle = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // dp[i][j]: 위치 (i, j)에 도달할 때까지의 최대 합
        int[][] dp = new int[n][n];
        
        // 초기값: 맨 위층
        dp[0][0] = triangle[0][0];
        
        // 위에서 아래로 계산
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 왼쪽 끝: 오른쪽 위에서만 올 수 있음
                    dp[i][j] = triangle[i][j] + dp[i-1][j];
                } else if (j == i) {
                    // 오른쪽 끝: 왼쪽 위에서만 올 수 있음
                    dp[i][j] = triangle[i][j] + dp[i-1][j-1];
                } else {
                    // 중간: 양쪽에서 올 수 있음
                    dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
                }
            }
        }
        
        // 마지막 층의 최대값이 답
        int answer = 0;
        for (int j = 0; j < n; j++) {
            answer = Math.max(answer, dp[n-1][j]);
        }
        
        System.out.println(answer);
    }
}

