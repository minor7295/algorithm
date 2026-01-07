import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 투 포인터 알고리즘: 연속된 자연수의 합으로 N을 나타내는 방법의 개수
        int start = 1;
        int end = 1;
        int sum = 0;
        int count = 0;
        
        while (start <= N) {
            if (sum < N) {
                // 구간 확장: end 포인터 이동
                sum += end;
                end++;
            } else if (sum > N) {
                // 구간 축소: start 포인터 이동
                sum -= start;
                start++;
            } else {
                // 조건 만족: 해를 찾음
                count++;
                sum -= start;
                start++;
            }
        }
        
        System.out.println(count);
    }
}

