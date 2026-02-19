import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static long n;
    static long p;
    static long q;
    static long x;
    static long y;

    static final HashMap<Long, Long> memo = new HashMap<>();

    static long solve(long i) {
        if (i <= 0) {
            return 1L;
        }

        Long cached = memo.get(i);
        if (cached != null) {
            return cached;
        }

        long value = solve(i / p - x) + solve(i / q - y);
        memo.put(i, value);
        return value;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        p = Long.parseLong(st.nextToken());
        q = Long.parseLong(st.nextToken());
        x = Long.parseLong(st.nextToken());
        y = Long.parseLong(st.nextToken());

        System.out.println(solve(n));
    }
}