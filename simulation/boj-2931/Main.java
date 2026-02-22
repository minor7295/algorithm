import java.io.*;
import java.util.*;

class Main {
    static int R, C;

    // up, down, left, right
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static char[][] parseInput(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }

        return map;
    }

    static int getMask(char block) {
        if (block == '|') return 3;
        if (block == '-') return 12;
        if (block == '+') return 15;
        if (block == '1') return 10;
        if (block == '2') return 9;
        if (block == '3') return 5;
        if (block == '4') return 6;
        if (block == 'M' || block == 'Z') return 15;
        return 0;
    }

    static char getBlock(int mask) {
        if (mask == 3) return '|';
        if (mask == 12) return '-';
        if (mask == 15) return '+';
        if (mask == 10) return '1';
        if (mask == 9) return '2';
        if (mask == 5) return '3';
        if (mask == 6) return '4';
        return '?';
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = parseInput(br);

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] != '.') continue;

                int mask = 0;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;

                    char neighbor = map[nr][nc];
                    if (neighbor == '.') continue;

                    int neighborMask = getMask(neighbor);
                    int oppositeDir = d ^ 1;

                    if ((neighborMask & (1 << oppositeDir)) != 0) {
                        mask |= (1 << d);
                    }
                }

                if (mask != 0) {
                    char block = getBlock(mask);
                    if (block == '?') continue;
                    System.out.println((r + 1) + " " + (c + 1) + " " + block);
                    return;
                }
            }
        }
    }
}
