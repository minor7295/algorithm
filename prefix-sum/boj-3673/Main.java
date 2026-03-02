import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        int c = nextInt();

        for (int tc = 0; tc < c; tc++) {
            int d = nextInt();
            int n = nextInt();

            long[] cnt = new long[d];
            cnt[0] = 1L; // prefix[0] = 0

            int rem = 0;

            for (int i = 0; i < n; i++) {
                int value = nextInt();
                rem = (int) ((rem + (long) value) % d);
                cnt[rem]++;
            }

            long answer = 0L;
            for (int r = 0; r < d; r++) {
                long k = cnt[r];
                answer += k * (k - 1L) / 2L;
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    private static int nextInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return Integer.parseInt(st.nextToken());
    }
}
