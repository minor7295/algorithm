import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        // a[i]: i자리 이친수 중 0으로 끝나는 것의 개수
        // b[i]: i자리 이친수 중 1로 끝나는 것의 개수
        long[] a = new long[91];
        long[] b = new long[91];
        
        // 초기값: 1자리 이친수는 "1"만 가능
        a[1] = 0;  // 1자리 중 0으로 끝나는 것: 없음
        b[1] = 1;  // 1자리 중 1로 끝나는 것: 1
        
        // 점화식: 마지막 자리 숫자로 분류하여 계산
        for (int i = 2; i <= n; i++) {
            // i자리 이친수 중 0으로 끝나는 것
            // (i-1)자리 이친수 모두에 0을 붙일 수 있음
            a[i] = a[i-1] + b[i-1];
            
            // i자리 이친수 중 1로 끝나는 것
            // (i-1)자리 이친수 중 0으로 끝나는 것에만 1을 붙일 수 있음 (11 방지)
            b[i] = a[i-1];
        }
        
        // 답: n자리 이친수의 총 개수
        System.out.println(a[n] + b[n]);
    }
}

