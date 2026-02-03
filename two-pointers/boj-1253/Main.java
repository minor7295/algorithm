import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        long value;
        int originalIdx;
        
        Pair(long value, int originalIdx) {
            this.value = value;
            this.originalIdx = originalIdx;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        long[] arr = new long[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(input[i]);
        }
        
        // (값, 원래 인덱스) 쌍을 저장하고 값 기준으로 정렬
        Pair[] pairs = new Pair[N];
        for (int i = 0; i < N; i++) {
            pairs[i] = new Pair(arr[i], i);
        }
        Arrays.sort(pairs, (a, b) -> Long.compare(a.value, b.value));
        
        int count = 0;
        // 각 수에 대해 투포인터로 확인
        for (int i = 0; i < N; i++) {
            long target = pairs[i].value;
            int targetIdx = pairs[i].originalIdx;
            boolean isGood = false;
            
            int left = 0, right = N - 1;
            while (left < right) {
                // 자기 자신 제외
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }
                
                long sum = pairs[left].value + pairs[right].value;
                if (sum == target) {
                    // 원래 인덱스가 자기 자신이 아닌지 확인
                    if (pairs[left].originalIdx != targetIdx && 
                        pairs[right].originalIdx != targetIdx) {
                        isGood = true;
                        break;
                    }
                    // 자기 자신을 포함하는 경우 다른 쌍 찾기
                    left++;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
            
            if (isGood) count++;
        }
        
        System.out.println(count);
    }
}

