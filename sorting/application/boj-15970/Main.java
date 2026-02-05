import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 색깔별로 위치를 저장할 배열 (색깔은 1~N)
        List<Integer>[] colorLists = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            colorLists[i] = new ArrayList<>();
        }
        
        // 입력 받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 위치
            int y = Integer.parseInt(st.nextToken()); // 색깔
            colorLists[y].add(x);
        }
        
        // 각 색깔별로 정렬하고 거리 계산
        long totalSum = 0;
        for (int color = 1; color <= N; color++) {
            List<Integer> list = colorLists[color];
            
            // 위치 기준으로 정렬
            Collections.sort(list);
            
            // 각 점에 대해 왼쪽과 오른쪽 인접 항목 중 더 가까운 것 선택
            for (int i = 0; i < list.size(); i++) {
                int minDist = Integer.MAX_VALUE;
                
                // 왼쪽 항목 확인
                if (i > 0) {
                    minDist = Math.min(minDist, list.get(i) - list.get(i-1));
                }
                
                // 오른쪽 항목 확인
                if (i < list.size() - 1) {
                    minDist = Math.min(minDist, list.get(i+1) - list.get(i));
                }
                
                totalSum += minDist;
            }
        }
        
        System.out.println(totalSum);
    }
}

