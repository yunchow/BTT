package test;


public class FibMain {

    static int N = 50;
    protected static int times = 1;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int n = fib(1, 1, N);
        System.out.println("尾递归：Cost time => " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        n = fib2(N);
        System.out.println("递归：Cost time => " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        fibIter(N);
        System.out.println("迭代：Cost time => " + (System.currentTimeMillis() - start));

        int[] x = new int[N];
        x[0] = 1;
        x[1] = 1;
        start = System.currentTimeMillis();
        fibPush(x, N);
        System.out.println("递推:Cost time => " + (System.currentTimeMillis() - start));
    }

    public static void main1(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            int n = fib(1, 1, N);
            //System.out.println(i + ": " + n);
        }
        System.out.println("尾递归：Cost time => " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            int n = fib2(N);
            //System.out.println(i + ": " + n);
        }
        System.out.println("递归：Cost time => " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            fibIter(N);
        }
        System.out.println("迭代：Cost time => " + (System.currentTimeMillis() - start));


        int n = N;
        int[] x = new int[n];
        x[0] = 1;
        x[1] = 1;
        start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            fibPush(x, n);
        }
        System.out.println("递推:Cost time => " + (System.currentTimeMillis() - start));
    }

    static void fibPush(int[] x, int n) {
        for (int i = 2; i < n; i++) {
            x[i] = x[i - 1] + x[i - 2];
        }
    }

    static void fibIter(int n) {
        int a = 1, b = 1;
        for (int i = 2; i < n; i++) {
            b = a + b;
            a = b - a;
        }
        //System.out.println("b = " + b);
    }

    static int fib(int a, int b, int n) {
        if (n == 2) {
            return b;
        } else {
            return fib(b, a + b, n - 1);
        }
    }

    static int fib2(int n) {
        return n <= 2 ? 1 : fib2(n - 1) + fib2(n - 2);
    }

    static int fib3(int a, int b, int n) {
        return n <= 0 ? 0 : fib3(b, a + b, n - 1);
    }
}
