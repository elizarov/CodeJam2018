import java.util.Scanner;

public class R1A {
    static int r, c, v, h;
    static char[][] g;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test = 0; test < t; test++) {
            r = sc.nextInt();
            c = sc.nextInt();
            h = sc.nextInt();
            v = sc.nextInt();
            g = new char[r][];
            for (int i = 0; i < r; i++) {
                g[i] = sc.next().toCharArray();
            }
            boolean res = solve();
            System.out.println("Case #" + (test + 1) + ": " + (res ? "POSSIBLE" : "IMPOSSIBLE"));
        }
    }

    private static boolean solve() {
        int cnt = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                switch (g[i][j]) {
                    case '@': cnt++; break;
                    case '.': break;
                    default: throw new AssertionError();
                }
            }
        }
        if (cnt == 0) return true;
        int tot = (v + 1) * (h + 1);
        if (cnt % tot != 0) return false;
        int piece = cnt / tot;
        int deltaH = piece * (v + 1);
        int nextH = deltaH;
        int prev = 0;
        cnt = 0;
        boolean firstH = true;
        int[] knum = new int[c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (g[i][j] == '@') cnt++;
            }
            if (cnt < nextH) continue;
            if (cnt > nextH)
                return false;
            nextH += deltaH;
            int nextV = piece;
            int cV = 0;
            boolean same = false;
            int nCut = 0;
            for (int j = 0; j < c; j++) {
                for (int k = prev; k <= i; k++) {
                    if (g[k][j] == '@') {
                        cV++;
                        same = false;
                    }
                }
                if (firstH) {
                    if (same || cV == nextV) {
                        if (cV == nextV) {
                            nextV += piece;
                            nCut++;
                        }
                        same = true;
                        knum[j] = nCut;
                        continue;
                    }
                } else {
                    if (cV == nextV) {
                        if (knum[j] == nCut + 1) {
                            nextV += piece;
                            nCut++;
                            same = true;
                            continue;
                        } else
                            knum[j] = 0;
                    } else if (same) {
                        if (knum[j] != nCut) knum[j] = 0;
                    } else {
                        knum[j] = 0;
                    }
                }
                if (cV <= nextV) continue;
                return false;
            }
            prev = i + 1;
            firstH = false;
        }
        return true;
    }
}

/*
1
2 4 1 1
@.@.
.@.@
 */