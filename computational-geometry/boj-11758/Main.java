import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 세 점의 좌표 입력
        st = new StringTokenizer(br.readLine());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        
        // CCW 알고리즘으로 방향 판단
        int result = ccw(x1, y1, x2, y2, x3, y3);
        
        System.out.println(result);
    }
    
    /**
     * CCW (Counter Clockwise) 알고리즘
     * 세 점 P1, P2, P3의 방향을 판단
     * 
     * @param x1, y1 점 P1의 좌표
     * @param x2, y2 점 P2의 좌표
     * @param x3, y3 점 P3의 좌표
     * @return 1: 반시계 방향, -1: 시계 방향, 0: 일직선
     */
    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        // 벡터 P1P2: (x2-x1, y2-y1)
        // 벡터 P1P3: (x3-x1, y3-y1)
        // 외적 = (x2-x1)*(y3-y1) - (y2-y1)*(x3-x1)
        long cross = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        
        if (cross > 0) {
            return 1;      // 반시계 방향
        } else if (cross < 0) {
            return -1;     // 시계 방향
        } else {
            return 0;      // 일직선
        }
    }
}

