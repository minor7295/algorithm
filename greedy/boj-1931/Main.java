import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[][] meetings = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }
        
        // 끝나는 시간 기준 오름차순 정렬 (끝나는 시간이 같으면 시작 시간 기준)
        Arrays.sort(meetings, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        
        // 그리디 선택: 끝나는 시간이 빠른 회의부터 선택
        int count = 0;
        int lastEnd = 0;
        
        for (int[] meeting : meetings) {
            // 현재 회의의 시작 시간이 마지막 선택한 회의의 끝 시간 이상이면 선택 가능
            if (meeting[0] >= lastEnd) {
                count++;
                lastEnd = meeting[1];
            }
        }
        
        System.out.println(count);
    }
}

