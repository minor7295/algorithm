import java.io.*;
import java.util.*;

class Edge {
    int to;      // 도착 도시
    int weight;  // 버스 비용
    
    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());  // 도시의 개수
        int M = Integer.parseInt(br.readLine());  // 버스의 개수
        
        // 그래프 초기화: 인접 리스트로 표현
        // graph[i]: 도시 i에서 출발하는 모든 버스들의 리스트
        List<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 버스 정보 입력 (방향 그래프)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());  // 출발 도시
            int to = Integer.parseInt(st.nextToken());    // 도착 도시
            int cost = Integer.parseInt(st.nextToken()); // 버스 비용
            
            // 방향 그래프이므로 graph[from]에만 간선 추가
            graph[from].add(new Edge(to, cost));
        }
        
        // 시작점과 목표점 입력
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());  // 시작 도시
        int end = Integer.parseInt(st.nextToken());    // 목표 도시
        
        // 다익스트라 알고리즘으로 최소 비용 계산
        int result = dijkstra(start, end, graph, N);
        
        System.out.println(result);
    }
    
    /**
     * 다익스트라 알고리즘으로 시작 도시에서 목표 도시까지의 최소 비용을 구함
     * 
     * @param start 시작 도시
     * @param end 목표 도시
     * @param graph 그래프 (인접 리스트)
     * @param N 도시의 개수
     * @return 시작 도시에서 목표 도시까지의 최소 비용
     */
    static int dijkstra(int start, int end, List<Edge>[] graph, int N) {
        // 거리 배열 초기화
        // dist[i]: 시작 도시에서 도시 i까지의 최소 비용
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;  // 시작 도시의 거리는 0
        
        // 우선순위 큐: (거리, 도시) 쌍을 거리 순으로 정렬
        // 거리가 가장 작은 도시부터 처리하기 위해 사용
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, start});
        
        // 방문 체크 배열
        // visited[i]: 도시 i를 이미 처리했는지 여부
        boolean[] visited = new boolean[N + 1];
        
        while (!pq.isEmpty()) {
            // 큐에서 거리가 가장 작은 도시 선택
            int[] current = pq.poll();
            int distance = current[0];  // 현재 도시까지의 거리
            int city = current[1];       // 현재 도시 번호
            
            // 목표 도시에 도달하면 즉시 반환 (최단 경로 보장)
            if (city == end) {
                return distance;
            }
            
            // 이미 처리된 도시는 건너뛰기
            // 같은 도시가 여러 번 큐에 들어갈 수 있으므로 방문 체크 필수
            if (visited[city]) continue;
            visited[city] = true;
            
            // 현재 도시에서 출발하는 모든 버스 확인
            for (Edge edge : graph[city]) {
                int next = edge.to;      // 다음 도시
                int cost = edge.weight;  // 버스 비용
                
                // 새로운 거리가 기존 거리보다 작으면 업데이트
                // dist[next] > distance + cost: 더 짧은 경로 발견
                if (dist[next] > distance + cost) {
                    dist[next] = distance + cost;
                    // 업데이트된 도시를 큐에 추가
                    pq.offer(new int[]{dist[next], next});
                }
            }
        }
        
        // 목표 도시에 도달할 수 없는 경우 (문제 조건상 발생하지 않음)
        return dist[end];
    }
}

