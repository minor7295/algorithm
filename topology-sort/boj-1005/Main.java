import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            // 건물 건설 시간
            int[] time = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }
            
            // 그래프 초기화
            List<Integer>[] graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            
            // 진입 차수 배열
            int[] indegree = new int[N + 1];
            
            // 건설 순서 입력
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                
                graph[X].add(Y);   // X → Y
                indegree[Y]++;     // Y의 선행 조건 증가
            }
            
            // 목표 건물
            int W = Integer.parseInt(br.readLine());
            
            // 위상 정렬 + DP
            int[] dp = new int[N + 1];  // dp[i] = 건물 i의 완료 시간
            Queue<Integer> queue = new ArrayDeque<>();
            
            // 1. 선행 조건이 없는 건물부터 시작
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    dp[i] = time[i];  // 선행 조건 없으면 자신의 건설 시간만
                    queue.offer(i);
                }
            }
            
            // 2. 위상 정렬 순서대로 처리하면서 DP 갱신
            while (!queue.isEmpty()) {
                int current = queue.poll();
                
                // 목표 건물에 도달하면 종료 (최적화)
                if (current == W) {
                    break;
                }
                
                for (int next : graph[current]) {
                    // next의 완료 시간 = max(모든 선행 건물의 완료 시간) + next의 건설 시간
                    dp[next] = Math.max(dp[next], dp[current] + time[next]);
                    
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
            
            sb.append(dp[W]).append('\n');
        }
        
        System.out.print(sb);
    }
}

