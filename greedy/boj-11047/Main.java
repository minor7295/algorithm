import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 동전 가치 배열 (오름차순으로 입력됨)
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        
        // 그리디 알고리즘: 큰 동전부터 최대한 많이 사용
        int count = 0;
        int remaining = K;
        
        // 큰 동전부터 역순으로 순회
        for (int i = N - 1; i >= 0; i--) {
            if (remaining <= 0) break;
            
            // 현재 동전으로 만들 수 있는 최대 개수
            int coinCount = remaining / coins[i];
            count += coinCount;
            remaining %= coins[i];
        }
        
        System.out.println(count);
    }
}

