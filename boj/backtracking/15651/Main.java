import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] result;
    static StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        result = new int[M];
        
        backtrack(0);
        
        System.out.print(answer.toString());
    }
    
    static void backtrack(int depth) {
        // 종료 조건: M개의 자리를 모두 채웠을 때
        if (depth == M) {
            // 결과 출력
            for (int i = 0; i < M; i++) {
                answer.append(result[i]);
                if (i < M - 1) {
                    answer.append(' ');
                }
            }
            answer.append('\n');
            return;
        }
        
        // 각 자리마다 1부터 N까지 모든 수를 선택
        for (int i = 1; i <= N; i++) {
            result[depth] = i;           // 선택
            backtrack(depth + 1);        // 다음 자리로 재귀 호출
            // 중복이 허용되므로 선택 취소 불필요
        }
    }
}
