import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 진입 차수 배열
        int[] indegree = new int[N + 1];

        // 입력 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);   // A → B
            indegree[B]++;     // B의 선행 조건 증가
        }

        // Kahn 알고리즘 시작
        Queue<Integer> queue = new ArrayDeque<>();
        // offer: 큐에 데이터 추가, poll: 큐에서 데이터 꺼내기

        // 1. 선행 조건이 없는 정점부터 시작
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        StringBuilder result = new StringBuilder();

        // 2. 정점 배치 → 제약 해제 반복
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.append(current).append(' ');

            // current에서 나가는 모든 간선 제거
            for (int next : graph[current]) {
                indegree[next]--;              // 제약 하나 제거
                if (indegree[next] == 0) {     // 모든 제약 해제
                    queue.offer(next);
                }
            }
        }

        System.out.println(result.toString().trim());
    }
}
