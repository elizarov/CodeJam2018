import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class QualB {
    static int n;
    static int[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(in.readLine());
            v = Arrays.stream(in.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int res = solve();
            System.out.println("Case #" + (i + 1) + ": " + (res == -1 ? "OK" : "" + res));
        }
    }

    private static int solve() {
        int[] e = new int[(n + 1) / 2];
        int[] o = new int[n / 2];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) e[i / 2] = v[i]; else o[i / 2] = v[i];
        }
        Arrays.sort(e);
        Arrays.sort(o);
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) v[i] = e[i / 2]; else v[i] = o[i / 2];
        }
        for (int i = 0; i < n - 1; i++) {
            if (v[i] > v[i + 1]) return i;
        }
        return -1;
    }
}
