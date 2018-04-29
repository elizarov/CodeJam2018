import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QualC {
    static int a;
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            a = Integer.parseInt(in.readLine());
            solve(in);
        }
    }

    private static void solve(BufferedReader in) throws IOException {
        int n = 3;
        while (n * n < a) n++;
        int m = 3;
        while (n * m < a) m++;
        boolean[][] d = new boolean[n + 1][m + 1];
        int[][] r = new int[n + 1][m + 1];
        for (int i = 2; i < n; i++)
            for (int j = 2; j < m; j++)
                r[i][j] = 9;
        while (true) {
            int maxr = 0;
            int maxi = -1;
            int maxj = -1;
            for (int i = 2; i < n; i++) {
                for (int j = 2; j < m; j++) {
                    if (r[i][j] > maxr) {
                        maxr = r[i][j];
                        maxi = i;
                        maxj = j;
                    }
                }
            }
            if (maxr == 0) throw new AssertionError("fail");
            System.out.println(maxi + " " + maxj);
            String[] s = in.readLine().split("\\s+");
            int di = Integer.parseInt(s[0]);
            int dj = Integer.parseInt(s[1]);
            if (di == 0 && dj == 0) break;
            if (d[di][dj]) continue;
            d[di][dj] = true;
            for (int i = -1; i <= 1; i++)
                for (int j = -1; j <= 1; j++) {
                    int ri = di + i;
                    int rj = dj + j;
                    if (ri >= 2 && ri < n && rj >= 2 && rj < m)
                        r[ri][rj]--;
                }
        }
    }
}
