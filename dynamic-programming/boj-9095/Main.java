import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        // dp[i]: i를 1, 2, 3의 합으로 나타내는 방법의 수
        int[] dp = new int[11];
        
        // 초기값 설정
        dp[1] = 1;  // 1 = 1
        dp[2] = 2;  // 2 = 1+1, 2
        dp[3] = 4;  // 3 = 1+1+1, 1+2, 2+1, 3
        
        // 점화식: dp[i] = dp[i-3] + dp[i-2] + dp[i-1]
        // i를 만드는 방법 = (i-3을 만드는 방법에 3을 더함) + (i-2를 만드는 방법에 2를 더함) + (i-1을 만드는 방법에 1을 더함)
        for (int i = 4; i <= 10; i++) {
            dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
        }
        
        // 각 테스트 케이스마다 결과 출력
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}

