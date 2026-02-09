import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        // dp[i]: 2×i 크기의 직사각형을 채우는 방법의 수
        int[] dp = new int[n + 1];
        
        // 초기값 설정
        dp[1] = 1;  // 2×1: 세로 타일 1개 (1가지)
        if (n >= 2) {
            dp[2] = 2;  // 2×2: 세로 타일 2개 또는 가로 타일 2개 (2가지)
        }
        
        // 점화식: dp[i] = dp[i-1] + dp[i-2]
        // i를 만드는 방법 = (i-1에서 세로 타일 1개 추가) + (i-2에서 가로 타일 2개 추가)
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }
        
        System.out.println(dp[n]);
    }
}

