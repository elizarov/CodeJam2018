import java.util.Scanner;

public class QualA {
    static int d;
    static char[] prg;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            d = sc.nextInt();
            prg = sc.next().toCharArray();
            int res = solve();
            System.out.println("Case #" + (i + 1) + ": " + (res == -1 ? "IMPOSSIBLE" : "" + res));
        }
    }

    private static int solve() {
        int n = prg.length;
        long[] pw = new long[n];
        long cur = 1;
        long sum = 0;
        for (int i = 0; i < n; i++) {
           pw[i] = cur;
           switch (prg[i]) {
               case 'C': cur *= 2; break;
               case 'S': sum += cur; break;
               default: throw new AssertionError("fail");
           }
        }
        int hacks = 0;
        loop: while (sum > d) {
            for (int i = n - 1; i > 0; i--) {
                if (prg[i] == 'S' && prg[i - 1] == 'C') {
                    cur = pw[i - 1];
                    sum -= cur;
                    prg[i - 1] = 'S';
                    prg[i] = 'C';
                    pw[i] = cur;
                    hacks++;
                    continue loop;
                }
            }
            return -1;
        }
        return hacks;
    }
}
