import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        // 1단계: 모든 두 수의 합을 미리 계산하여 HashSet에 저장
        // d = a + b + c를 d - a = b + c로 변환하여 효율적으로 확인
        Set<Long> twoSumSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                twoSumSet.add(arr[i] + arr[j]);
            }
        }
        
        // 2단계: 배열을 내림차순으로 정렬
        // 내림차순으로 확인하면 첫 번째로 찾은 값이 최대값
        Arrays.sort(arr);
        // 오름차순 정렬 후 역순으로 확인하거나, 직접 내림차순 정렬
        // 여기서는 오름차순 정렬 후 역순으로 확인
        
        // 3단계: 각 d에 대해 확인 (내림차순으로)
        // 각 a에 대해 d - a가 두 수의 합 집합에 있는지 확인
        for (int i = N - 1; i >= 0; i--) {
            long d = arr[i];
            boolean found = false;
            
            for (int j = 0; j < N; j++) {
                long a = arr[j];
                long remainder = d - a;
                
                // d - a가 두 수의 합 집합에 있는지 확인
                if (twoSumSet.contains(remainder)) {
                    // d = a + b + c를 만족하는 경우 발견
                    found = true;
                    break;
                }
            }
            
            if (found) {
                System.out.println(d);
                return;
            }
        }
    }
}

