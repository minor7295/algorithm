import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 최대 힙: Comparator.reverseOrder()를 사용하여 최소 힙을 최대 힙으로 변환
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        
        StringBuilder answer = new StringBuilder();
        
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            
            if (x > 0) {
                // 자연수 입력: 힙에 추가
                maxHeap.offer(x);
            } else {
                // 0 입력: 최댓값 출력 및 제거
                if (maxHeap.isEmpty()) {
                    // 빈 힙일 경우 0 출력
                    answer.append(0).append('\n');
                } else {
                    // 최댓값(루트 노드) 출력 및 제거
                    answer.append(maxHeap.poll()).append('\n');
                }
            }
        }
        
        System.out.print(answer.toString());
    }
}

