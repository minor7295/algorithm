import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 점의 개수 입력
        int n = Integer.parseInt(br.readLine());
        
        // 점들의 좌표 입력
        long[] x = new long[n];
        long[] y = new long[n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }
        
        // 신발끈 공식을 이용한 다각형 면적 계산
        double area = polygonArea(x, y, n);
        
        // 소수점 둘째 자리에서 반올림하여 첫째 자리까지 출력
        System.out.printf("%.1f\n", area);
    }
    
    /**
     * 신발끈 공식(Shoelace Formula)을 이용한 다각형 면적 계산
     * 
     * @param x 점들의 x 좌표 배열
     * @param y 점들의 y 좌표 배열
     * @param n 점의 개수
     * @return 다각형의 면적
     */
    static double polygonArea(long[] x, long[] y, int n) {
        long area = 0;
        
        // 각 변에 대해 계산
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;  // 다음 점 (마지막 점은 첫 번째 점과 연결)
            // 신발끈 공식: x[i] * y[j] - x[j] * y[i]
            area += x[i] * y[j] - x[j] * y[i];
        }
        
        // 절댓값을 구하고 2로 나눔
        // (면적은 항상 양수여야 하므로 절댓값 사용)
        return Math.abs(area) / 2.0;
    }
}

