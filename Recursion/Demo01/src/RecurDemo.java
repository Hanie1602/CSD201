
public class RecurDemo {

    ///Demo01
    public static double factorial(int n) {
        if (n < 2) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    ///// Demo02
    public static int fibo(int n) {
        if (n < 2) {
            return 1;
        }
        return fibo(n - 2) + fibo(n - 1);
    }

    public static boolean testFibo(int x) {
        if (x < 1) {
            return false;
        }
        int aFibo = 1;
        int n = 2;
        while (aFibo < x) {
            aFibo = fibo(n++);
        }
        return x == aFibo;
    }

    ///Demo03
    public static double ap(int n, double a, double d) {
        if (n <= 1) {
            return a;
        }
        return ap(n - 1, a, d) + d;
    }

    ///Demo04
    public static double gp(int n, double a, double q) {
        if (n <= 1) {
            return a;
        }
        return gp(n - 1, a, q) * q;
    }

    ///Demo05
    public static double sum(double[] a, int n) {
        if (n == 0) {
            return 0;
        }
        return sum(a, n - 1) + a[n - 1];
    }

    ///Demo06
    public static double max(double[] a, int n) {
        if (n == 1) {
            return a[0];
        }
        double m = max(a, n - 1);
        return m > a[n - 1] ? m : a[n - 1];
    }

    ///Demo07
    public static double min(int[] a, int n) {
        if (n == 0) {
            return a[0];
        }
        double mi = min(a, n - 1);
        return mi < a[n - 1] ? mi : a[n - 1];
    }

    public static void main(String[] args) {
        ///Demo01
        System.out.println("Demo01: ");
        System.out.println(factorial(5));
        ///Demo02
        System.out.println("Demo02: ");
        System.out.println(testFibo(55));
        System.out.println(testFibo(144));
        System.out.println(testFibo(120));
        ///Demo03
        System.out.println("Demo03: ");
        System.out.println(ap(6, 1.5, 2));
        ///Demo04
        System.out.println("Demo04: ");
        System.out.println(gp(6, 1.5, 2));
        ///Demo05
        System.out.println("Demo05: ");
        double a[] = {1.5, 2, 4, 5, 2, 6.5};
        System.out.println(sum(a, 6));
        ///Demo06
        System.out.println("Demo06: ");

        double b[] = {1, 5, 9, 7, 2, 19, 10};
        System.out.println(max(b, 7));
        System.out.println("Demo07: ");
        int c[] = {1, 5, 9, 7, 2, 19, 10};
        System.out.println(min(c, 7));

    }
}
