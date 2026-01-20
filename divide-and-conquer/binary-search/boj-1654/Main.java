import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int K = Integer.parseInt(st.nextToken());  // 가지고 있는 랜선의 개수
        long N = Long.parseLong(st.nextToken());   // 필요한 랜선의 개수
        
        long[] lengths = new long[K];
        long maxLength = 0;
        
        for (int i = 0; i < K; i++) {
            lengths[i] = Long.parseLong(br.readLine());
            maxLength = Math.max(maxLength, lengths[i]);
        }
        
        // 이분 탐색: 랜선의 길이를 탐색
        long left = 1;                    // 하한선: 가장 작은 길이
        long right = maxLength;            // 상한선: 가장 긴 랜선의 길이
        long answer = 0;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            // mid 길이로 만들 수 있는 랜선의 개수 계산
            long count = 0;
            for (int i = 0; i < K; i++) {
                count += lengths[i] / mid;
            }
            
            if (count >= N) {
                // N개 이상 만들 수 있음 → 길이를 늘림 (더 큰 값 시도)
                answer = mid;  // 현재 mid가 가능한 값이므로 저장
                left = mid + 1;
            } else {
                // N개 미만 → 길이를 줄임 (더 작은 값 시도)
                right = mid - 1;
            }
        }
        
        System.out.println(answer);
    }
}

