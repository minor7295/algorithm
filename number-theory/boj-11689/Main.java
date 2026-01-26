import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        
        // Euler's totient function φ(n) calculation
        // φ(n) = n × ∏(1 - 1/p) for all distinct prime factors p of n
        // Or: φ(n) = n × ∏((p-1)/p) for all distinct prime factors p of n
        
        long result = n;
        long temp = n;
        
        // Find prime factors from 2 to √temp
        for (long i = 2; i * i <= temp; i++) {
            if (temp % i == 0) {
                // i is a prime factor
                // result = result × (1 - 1/i) = result × (i-1)/i
                // Calculate as result / i * (i - 1) for integer arithmetic
                result = result / i * (i - 1);
                
                // Remove all factors of i from temp (prevent duplicate prime factor processing)
                while (temp % i == 0) {
                    temp /= i;
                }
            }
        }
        
        // If temp > 1, temp itself is a prime (prime factor larger than √n)
        if (temp > 1) {
            result = result / temp * (temp - 1);
        }
        
        System.out.println(result);
    }
}

