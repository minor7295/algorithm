import java.io.*;
import java.util.*;

/**
 * BOJ 1158 - 요세푸스 문제
 * 
 * 시뮬레이션 알고리즘을 사용하여 해결
 * 
 * 시간 복잡도: O(N²)
 * 공간 복잡도: O(N)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 살아있는 사람들의 리스트 초기화
        List<Integer> people = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            people.add(i);
        }
        
        // 제거 순서를 저장할 리스트
        List<Integer> result = new ArrayList<>();
        int currentIndex = 0; // 현재 위치
        
        // 모든 사람이 제거될 때까지 반복
        while (!people.isEmpty()) {
            // 제거할 인덱스 계산: (현재 위치 + K - 1) % 현재 리스트 크기
            // K번째 사람을 찾기 위해 (K-1)만큼 이동
            // 모듈로 연산으로 원형 구조 표현
            currentIndex = (currentIndex + K - 1) % people.size();
            
            // 해당 인덱스의 사람을 제거하고 결과에 추가
            result.add(people.remove(currentIndex));
            
            // currentIndex는 이미 조정됨 (제거 후 자동으로 다음 위치)
            // 리스트에서 요소를 제거하면 뒤의 요소들이 앞으로 이동하므로
            // 제거된 위치가 다음 시작 위치가 됨
        }
        
        // 결과 출력: <3, 6, 2, 7, 5, 1, 4> 형식
        System.out.print("<");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(">");
    }
}

