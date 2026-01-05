import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] permutation = new int[N + 1];
            boolean[] visited = new boolean[N + 1];
            
            // Read permutation
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                permutation[i] = Integer.parseInt(st.nextToken());
            }
            
            // Count cycles using DFS
            int cycleCount = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    dfs(i, permutation, visited);
                    cycleCount++;
                }
            }
            
            sb.append(cycleCount).append("\n");
        }
        
        System.out.print(sb);
    }
    
    // DFS function: find cycle starting from current vertex
    static void dfs(int current, int[] permutation, boolean[] visited) {
        visited[current] = true;
        int next = permutation[current];
        
        // Continue DFS only if next vertex is not visited
        if (!visited[next]) {
            dfs(next, permutation, visited);
        }
    }
}

