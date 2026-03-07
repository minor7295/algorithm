import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static String next() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(next());
        long[] h = new long[n + 1];
        for (int i = 0; i < n; i++) {
            h[i] = Long.parseLong(next());
        }
        h[n] = 0; // Sentinel to flush remaining bars.

        int[] stack = new int[n + 1];
        int top = -1;
        long answer = 0;

        for (int i = 0; i <= n; i++) {
            long cur = h[i];

            while (top >= 0 && h[stack[top]] > cur) {
                int mid = stack[top--];
                long height = h[mid];

                int left = (top < 0) ? 0 : stack[top] + 1;
                int right = i - 1;
                long width = right - left + 1L;

                long side = Math.min(height, width);
                if (side > answer) {
                    answer = side;
                }
            }

            stack[++top] = i;
        }

        System.out.println(answer);
    }
}
