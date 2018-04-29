import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class QualD {
    static double eps = 1e-14;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            System.out.println("Case #" + (i + 1) + ":");
            solve(sc.nextDouble());
        }
    }

    private static void rot(double[][] p, int i, int j, double a) {
        for (int k = 0; k < 3; k++) {
            double x = p[k][i];
            double y = p[k][j];
            p[k][i] = x * Math.cos(a) - y * Math.sin(a);
            p[k][j] = y * Math.cos(a) + x * Math.sin(a);
        }
    }

    private static double[][] pts(double a, double b) {
        double[][] p = new double[][] {
                { 0.5, 0, 0 },
                { 0, 0.5, 0 },
                { 0, 0, 0.5 }
        };
        rot(p, 0, 1, a);
        rot(p, 1, 2, b);
        return p;
    }

    private static final Comparator<double[]> X_COMP = Comparator.comparingDouble(p -> p[0]);

    private static double det(double x11, double x12, double x21, double x22) {
        return x11 * x22 - x12 * x21;
    }

    private static int next(double[][] v, int i, int ccv) {
        int res = -1;
        double rv1 = 0;
        double rv2 = 0;
        for (int j = i + 1; j <= 7; j++) {
            double v1 = v[j][0] - v[i][0];
            double v2 = v[j][2] - v[i][2];
            if (ccv * det(v1, v2, rv1, rv2) >= 0) {
                res = j;
                rv1 = v1;
                rv2 = v2;
            }
        }
        assert res > 0;
        return res;
    }

    private static double compute(double a, double b) {
        double[][] p = pts(a, b);
        double[][] v = new double[8][3];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if ((i & (1 << k)) == 0)
                        v[i][j] += p[k][j];
                    else
                        v[i][j] -= p[k][j];
                }
            }
        Arrays.sort(v, X_COMP);
        double sum = 0;
        int c = 0;
        while (c < 7) {
            int d = next(v, c, -1);
            sum += (v[d][0] - v[c][0]) * (v[d][2] + v[c][2]) / 2;
            c = d;
        }
        c = 0;
        while (c < 7) {
            int d = next(v, c, 1);
            sum -= (v[d][0] - v[c][0]) * (v[d][2] + v[c][2]) / 2;
            c = d;
        }
        return sum;
    }

    private static final double PHI = (Math.sqrt(5) - 1) / 2;
    private static final double MAX_A = Math.PI / 4;
    private static double MAX_B = findMaxB();

    private static double findMaxB() {
        double u = 0;
        double v = MAX_A;
        while (v - u > eps) {
            double p = u * PHI + v * (1 - PHI);
            double q = v * PHI + u * (1 - PHI);
            double cp = compute(MAX_A, p);
            double cq = compute(MAX_A, q);
            if (cp > cq) {
                v = q;
            } else {
                u = p;
            }
        }
        return (u + v) / 2;
    }

    private static void solve(double target) {
        double u = 0;
        double v = MAX_A;
        double cur;
        while (v - u > eps) {
            double m = (u + v) / 2;
            cur = compute(m, 0);
            if (cur < target)
                u = m;
            else
                v = m;
        }
        
        double a = (u + v) / 2;
        cur = compute(a, 0);
        a = cur < target ? v : u;
        double b = 0;
        cur = compute(a, b);
        if (cur < target) {
            u = 0;
            v = MAX_B;
            while (v - u > eps) {
                double m = (u + v) / 2;
                cur = compute(a, m);
                if (cur < target)
                    u = m;
                else
                    v = m;
            }
            b = (u + v) / 2;
            cur = compute(a, b);
            b = cur < target ? v : u;
        }

        double[][] p = pts(a, b);
        for (int i = 0; i < 3; i++) {
            System.out.println(p[i][0] + " " + p[i][1] + " " + p[i][2]);
        }
    }
}
