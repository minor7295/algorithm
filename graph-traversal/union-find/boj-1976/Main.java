import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        // Initialize Union-Find
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        
        // Read adjacency matrix and perform Union operations
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 1) {
                    union(i, j, parent);
                }
            }
        }
        
        // Read travel plan
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] plan = new int[M];
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
        
        // Check if travel plan is possible
        boolean possible = true;
        for (int i = 0; i < M - 1; i++) {
            if (find(plan[i], parent) != find(plan[i + 1], parent)) {
                possible = false;
                break;
            }
        }
        
        System.out.println(possible ? "YES" : "NO");
    }
    
    // Find function with path compression
    static int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x], parent);
    }
    
    // Union function
    static void union(int x, int y, int[] parent) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);
        
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}

