import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int solve(int n, int r, int c) {
        if (n == 1) {
            return r * 2 + c;
        }

        int half = 1 << (n - 1);
        int quarter = half * half;

        if (r < half && c < half) {
            return solve(n - 1, r, c);
        }
        if (r < half && c >= half) {
            return quarter + solve(n - 1, r, c - half);
        }
        if (r >= half && c < half) {
            return 2 * quarter + solve(n - 1, r - half, c);
        }

        return 3 * quarter + solve(n - 1, r - half, c - half);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(solve(n, r, c));
    }
}