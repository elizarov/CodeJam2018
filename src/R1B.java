import java.util.Arrays;
import java.util.Scanner;

public class R1B {
    static int r, b, c;
    static D[] d;

    class D {
        int m;
        long s;
        long p;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test = 0; test < t; test++) {
            r = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            d = new D[c];
            for (int i = 0; i < c; i++) {
                d[i].m = sc.nextInt();
                d[i].s = sc.nextInt();
                d[i].p = sc.nextInt();
            }
            long res = solve();
            System.out.println("Case #" + (test + 1) + ": " + res);
        }
    }

    private static long solve() {
        long answer = Long.MAX_VALUE;
        int[] u = new int[c];
        long[] t = new long[c];
        for (int cc = 1; cc <= r; cc++) {
            Arrays.sort(d, (d1, d2) -> -Integer.compare(d1.m, d2.m));
            Arrays.fill(u, 0);
            Arrays.fill(t, 0);
            int rem = b;
            for (int i = 0; i < cc; i++) {
                int n = Math.min(d[i].m, b);
                u[i] = n;
                t[i] = n == 0 ? 0 : d[i].s * n + d[i].p;
                rem -= n;
            }
            if (rem > 0) continue;
            boolean changes = true;
            long worst = 0;
            while (changes) {
                changes = false;
                worst = 0;
                int wi = -1;
                for (int i = 0; i < cc; i++) {
                    if (t[i] > worst) {
                        worst = t[i];
                        wi = i;
                    }
                }
                for (int i = 0; i < cc; i++) {
                    if (i != wi && u[i] < d[i].m) {
                        int can = d[i].m - u[i];
                        
                    }
                }
            }
            if (worst < answer) answer = worst;
        }
        return answer;
    }
}

/*
1
2 4 1 1
@.@.
.@.@
 */