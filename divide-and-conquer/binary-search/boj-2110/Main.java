import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());  // 집의 개수
        int c = Integer.parseInt(st.nextToken());  // 공유기의 개수
        
        long[] houses = new long[n];
        
        for (int i = 0; i < n; i++) {
            houses[i] = Long.parseLong(br.readLine());
        }
        
        // 집 좌표 정렬
        Arrays.sort(houses);
        
        // 이분 탐색: 가장 인접한 두 공유기 사이의 거리를 탐색
        long left = 1;                                    // 하한선: 최소 거리
        long right = houses[n-1] - houses[0];             // 상한선: 최대 거리
        long answer = 0;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            // mid 거리로 C개 이상의 공유기를 설치할 수 있는가?
            if (canInstall(mid, houses, c)) {
                // C개 이상 설치 가능 → 거리를 늘림 (더 큰 값 시도)
                answer = mid;  // 현재 mid가 가능한 값이므로 저장
                left = mid + 1;
            } else {
                // C개 미만 → 거리를 줄임 (더 작은 값 시도)
                right = mid - 1;
            }
        }
        
        System.out.println(answer);
    }
    
    // 조건 함수: 특정 거리로 C개 이상의 공유기를 설치할 수 있는가?
    private static boolean canInstall(long distance, long[] houses, int c) {
        int count = 1;  // 첫 집에 공유기 설치
        long lastPos = houses[0];
        
        // 그리디하게 공유기 설치: 이전 공유기 위치 + distance 이상인 최소 위치에 설치
        for (int i = 1; i < houses.length; i++) {
            if (houses[i] - lastPos >= distance) {
                count++;
                lastPos = houses[i];
                
                // C개 이상 설치 가능하면 조기 종료
                if (count >= c) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

