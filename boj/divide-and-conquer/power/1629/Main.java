import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        
        // 분할정복을 이용한 거듭제곱
        long result = power(A, B, C);
        
        System.out.println(result);
    }
    
    /**
     * 분할정복을 이용한 거듭제곱 계산
     * A^B mod C를 O(log B) 시간에 계산
     * 
     * @param base 밑 (A)
     * @param exponent 지수 (B)
     * @param mod 나누는 수 (C)
     * @return base^exponent mod mod
     */
    static long power(long base, long exponent, long mod) {
        // Base Case: 더 이상 나눌 수 없는 경우
        if (exponent == 0) {
            return 1 % mod;
        }
        if (exponent == 1) {
            return base % mod;
        }
        
        // Divide: 지수를 반으로 나눔
        long half = power(base, exponent / 2, mod);
        
        // Conquer: 절반의 거듭제곱 계산
        // half * half = (A^(B/2))^2 = A^B (B가 짝수인 경우)
        long result = (half * half) % mod;
        
        // Combine: 홀수인 경우 추가 곱셈
        // A^B = A^(B/2) * A^(B/2) * A (B가 홀수인 경우)
        if (exponent % 2 == 1) {
            result = (result * base) % mod;
        }
        
        return result;
    }
}

