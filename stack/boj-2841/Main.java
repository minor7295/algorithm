import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st.nextToken(); // P is not required for simulation logic.

        @SuppressWarnings("unchecked")
        ArrayDeque<Integer>[] stacks = new ArrayDeque[7];
        for (int i = 1; i <= 6; i++) {
            stacks[i] = new ArrayDeque<>();
        }

        long moves = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken());
            int fret = Integer.parseInt(st.nextToken());

            ArrayDeque<Integer> stack = stacks[line];

            while (!stack.isEmpty() && stack.peekLast() > fret) {
                stack.pollLast();
                moves++;
            }

            if (!stack.isEmpty() && stack.peekLast() == fret) {
                continue;
            }

            stack.addLast(fret);
            moves++;
        }

        System.out.println(moves);
    }
}
